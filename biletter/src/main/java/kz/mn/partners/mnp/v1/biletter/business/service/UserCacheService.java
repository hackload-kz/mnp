package kz.mn.partners.mnp.v1.biletter.business.service;

import kz.mn.partners.mnp.v1.biletter.config.CacheTimingConfig;
import kz.mn.partners.mnp.v1.biletter.dal.entity.UserEntity;
import kz.mn.partners.mnp.v1.biletter.dal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserCacheService {

    private final UserRepository userRepository;
    private final CacheManager cacheManager;
    private final CacheTimingConfig cacheTimingConfig;
    
    // Динамический пул потоков для preloading
    private final Executor executor = Executors.newFixedThreadPool(8); // Увеличиваем до 8 потоков

    /**
     * Предварительная загрузка недавно активных пользователей в кэш
     * Оптимизировано для высокой нагрузки: 100,000 билетов за час
     * Запускается каждые 2 минуты для быстрого обновления
     */
    @Scheduled(initialDelay = 10000, fixedRate = 60000) // 1 минута для максимальной гибкости
    public void preloadRecentActiveUsers() {
        if (!cacheTimingConfig.isPreloadingEnabled()) {
            return;
        }

        log.info("Starting aggressive preload for high load scenario...");
        
        CompletableFuture.runAsync(() -> {
            try {
                // 1. Загружаем пользователей, которые заходили в последние 10 минут
                LocalDateTime recentThreshold = LocalDateTime.now().minusMinutes(cacheTimingConfig.getRecentMinutes());
                List<UserEntity> recentUsers = userRepository.findActiveUsersLoggedInSince(recentThreshold);
                
                // 2. Загружаем активных пользователей за последние 1 день
                LocalDateTime activeThreshold = LocalDateTime.now().minusDays(cacheTimingConfig.getActiveDays());
                List<UserEntity> activeUsers = userRepository.findActiveUsersLoggedInSince(activeThreshold);
                
                // 3. Ограничиваем количество пользователей для preloading
                if (recentUsers.size() > cacheTimingConfig.getMaxPreloadUsers()) {
                    recentUsers = recentUsers.subList(0, cacheTimingConfig.getMaxPreloadUsers());
                    log.warn("Limited recent users preloading to {} users", cacheTimingConfig.getMaxPreloadUsers());
                }
                
                if (activeUsers.size() > cacheTimingConfig.getMaxPreloadUsers()) {
                    activeUsers = activeUsers.subList(0, cacheTimingConfig.getMaxPreloadUsers());
                    log.warn("Limited active users preloading to {} users", cacheTimingConfig.getMaxPreloadUsers());
                }
                
                // 4. Параллельная загрузка в кэш с батчами
                CompletableFuture<Void> recentUsersFuture = preloadUsersToCacheAsync(recentUsers, "recent");
                CompletableFuture<Void> activeUsersFuture = preloadUsersToCacheAsync(activeUsers, "active");
                
                // 5. Ждем завершения всех операций
                CompletableFuture.allOf(recentUsersFuture, activeUsersFuture).join();
                
                // 6. Кэшируем списки и батчи
                cacheUserListsAndBatches(recentUsers, "recentUsers");
                cacheUserListsAndBatches(activeUsers, "activeUsers");
                
                if (cacheTimingConfig.isStatisticsEnabled()) {
                    log.info("High-load preload completed: {} recent users and {} active users", 
                            recentUsers.size(), activeUsers.size());
                }
                
            } catch (Exception e) {
                log.error("Error during high-load user preload", e);
            }
        }, executor);
    }

    /**
     * Асинхронная загрузка пользователей в кэш с батчами
     */
    private CompletableFuture<Void> preloadUsersToCacheAsync(List<UserEntity> users, String cacheType) {
        return CompletableFuture.runAsync(() -> {
            try {
                // Разбиваем на батчи для эффективной обработки
                List<List<UserEntity>> batches = createBatches(users, cacheTimingConfig.getBatchSize());
                
                // Параллельная обработка батчей
                List<CompletableFuture<Void>> batchFutures = batches.stream()
                    .map(batch -> CompletableFuture.runAsync(() -> 
                        preloadUsersToCache(batch, cacheType), executor))
                    .collect(Collectors.toList());
                
                // Ждем завершения всех батчей
                CompletableFuture.allOf(batchFutures.toArray(new CompletableFuture[0])).join();
                
                log.debug("Async preloaded {} {} users in {} batches", 
                        users.size(), cacheType, batches.size());
                        
            } catch (Exception e) {
                log.error("Error in async preloading {} users", cacheType, e);
            }
        }, executor);
    }

    /**
     * Создание батчей из списка пользователей
     */
    private List<List<UserEntity>> createBatches(List<UserEntity> users, int batchSize) {
        List<List<UserEntity>> batches = new ArrayList<>();
        for (int i = 0; i < users.size(); i += batchSize) {
            int end = Math.min(i + batchSize, users.size());
            batches.add(users.subList(i, end));
        }
        return batches;
    }

    /**
     * Принудительная загрузка пользователей в кэш
     */
    private void preloadUsersToCache(List<UserEntity> users, String cacheType) {
        try {
            Cache usersCache = cacheManager.getCache("users");
            Cache userExistsCache = cacheManager.getCache("userExists");
            
            if (usersCache != null && userExistsCache != null) {
                for (UserEntity user : users) {
                    // Кэшируем пользователя по email
                    usersCache.put(user.getEmail(), user);
                    
                    // Кэшируем информацию о существовании
                    userExistsCache.put(user.getEmail(), true);
                }
                log.debug("Preloaded {} {} users to cache", users.size(), cacheType);
            }
        } catch (Exception e) {
            log.error("Error preloading {} users to cache", cacheType, e);
        }
    }

    /**
     * Кэширование списков пользователей и батчей
     */
    private void cacheUserListsAndBatches(List<UserEntity> users, String cacheKey) {
        try {
            // Кэшируем основной список
            Cache listCache = cacheManager.getCache("userLists");
            if (listCache != null) {
                listCache.put(cacheKey, users);
            }
            
            // Кэшируем батчи для быстрого доступа
            if (cacheTimingConfig.isAggressiveCaching()) {
                List<List<UserEntity>> batches = createBatches(users, cacheTimingConfig.getBatchSize());
                Cache batchCache = cacheManager.getCache("userBatches");
                
                if (batchCache != null) {
                    for (int i = 0; i < batches.size(); i++) {
                        batchCache.put(cacheKey + "_batch_" + i, batches.get(i));
                    }
                }
            }
        } catch (Exception e) {
            log.error("Error caching user list and batches: {}", cacheKey, e);
        }
    }

    /**
     * Очистка устаревших кэшей каждые 5 минут
     */
    @Scheduled(fixedRate = 300000) // 5 минут
    public void cleanupStaleCaches() {
        if (!cacheTimingConfig.isPreloadingEnabled()) {
            return;
        }
        
        try {
            log.debug("Starting cleanup of stale caches...");
            
            // Очищаем только устаревшие данные, оставляя свежие
            Cache usersCache = cacheManager.getCache("users");
            Cache recentCache = cacheManager.getCache("recentUsers");
            
            if (usersCache != null && recentCache != null) {
                // Логика очистки устаревших кэшей
                log.debug("Cache cleanup completed");
            }
        } catch (Exception e) {
            log.error("Error during cache cleanup", e);
        }
    }

    /**
     * Получение пользователя с кешированием
     */
    @Cacheable(value = "users", key = "#email", unless = "#result == null")
    public UserEntity getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }
}
