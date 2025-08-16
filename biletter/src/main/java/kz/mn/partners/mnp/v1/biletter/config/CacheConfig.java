package kz.mn.partners.mnp.v1.biletter.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfig {

    // Локальный кеш для часто используемых данных
    // Оптимизирован для высокой нагрузки: 100,000 билетов за час
    @Bean
    @Primary
    public CacheManager caffeineCacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCaffeine(Caffeine.newBuilder()
                .maximumSize(100_000) // Увеличиваем до 100,000 записей для высокой нагрузки
                .expireAfterWrite(5, TimeUnit.MINUTES) // TTL 5 минут для быстрого обновления
                .expireAfterAccess(3, TimeUnit.MINUTES) // TTL 3 минуты после последнего доступа
                .recordStats() // Статистика для мониторинга
                .softValues() // Используем soft references для экономии памяти
                .weakKeys()); // Используем weak keys для автоматической очистки
        
        // Предварительно создаем кэши для лучшей производительности
        cacheManager.setCacheNames(java.util.Arrays.asList(
            "currentUsers", "currentUserIds", "userDetails", // Пользователи
            "users", "userExists", "activeUsers", "recentUsers", "activeUsersByDays", // Списки пользователей
            "userLists", "userBatches" // Общие списки и батчи
        ));
        
        return cacheManager;
    }

    // Redis кеш для распределенного кеширования
    // Оптимизирован для высокой нагрузки
    @Bean
    public CacheManager redisCacheManager(RedisConnectionFactory connectionFactory) {
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(10)) // TTL 10 минут для быстрого обновления
                .serializeKeysWith(RedisSerializationContext.SerializationPair
                        .fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair
                        .fromSerializer(new GenericJackson2JsonRedisSerializer()))
                .disableCachingNullValues(); // Не кешируем null значения

        return RedisCacheManager.builder(connectionFactory)
                .cacheDefaults(config)
                .withCacheConfiguration("users", 
                    RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(15))) // Пользователи
                .withCacheConfiguration("recentUsers", 
                    RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(5))) // Недавние пользователи
                .withCacheConfiguration("activeUsers", 
                    RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(20))) // Активные пользователи
                .withCacheConfiguration("userBatches", 
                    RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(3))) // Батчи пользователей
                .transactionAware()
                .build();
    }
}
