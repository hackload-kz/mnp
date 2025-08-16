package kz.mn.partners.mnp.v1.biletter.business.service;

import kz.mn.partners.mnp.v1.biletter.dal.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CurrentUserService {

    private final UserCacheService userCacheService;

    /**
     * Получить email текущего аутентифицированного пользователя
     * @return email пользователя или null если не аутентифицирован
     */
    private String getCurrentUserEmailInternal() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
                return ((UserDetails) authentication.getPrincipal()).getUsername();
            }
            return null;
        } catch (Exception e) {
            log.error("Error getting current user email", e);
            return null;
        }
    }

    /**
     * Проверить, аутентифицирован ли текущий пользователь
     * @return true если пользователь аутентифицирован
     */
    private boolean isAuthenticatedInternal() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            return authentication != null && 
                   authentication.isAuthenticated() && 
                   !(authentication.getPrincipal() instanceof String && 
                     "anonymousUser".equals(authentication.getPrincipal()));
        } catch (Exception e) {
            log.error("Error checking authentication status", e);
            return false;
        }
    }

    /**
     * Получить ID текущего пользователя
     * @return ID пользователя или null если не найден
     */
    @Cacheable(value = "currentUserIds", key = "#root.target.getCurrentUserEmailInternal()", unless = "#result == null")
    public Integer getCurrentUserId() {
        try {
            String email = getCurrentUserEmailInternal();
            if (email != null) {
                UserEntity user = userCacheService.getUserByEmail(email);
                return user != null ? user.getUserId() : null;
            }
            return null;
        } catch (Exception e) {
            log.error("Error getting current user ID", e);
            return null;
        }
    }

    /**
     * Получить полную информацию о текущем пользователе
     * @return UserEntity или null если не найден
     */
    @Cacheable(value = "currentUsers", key = "#root.target.getCurrentUserEmailInternal()", unless = "#result == null")
    public UserEntity getCurrentUser() {
        try {
            String email = getCurrentUserEmailInternal();
            if (email != null) {
                log.debug("Loading current user from cache/DB: {}", email);
                return userCacheService.getUserByEmail(email);
            }
            return null;
        } catch (Exception e) {
            log.error("Error getting current user", e);
            return null;
        }
    }

    /**
     * Получить email текущего пользователя (публичный метод)
     * @return email пользователя или null
     */
    public String getCurrentUserEmail() {
        return getCurrentUserEmailInternal();
    }

    /**
     * Проверить, аутентифицирован ли текущий пользователь (публичный метод)
     * @return true если пользователь аутентифицирован
     */
    public boolean isAuthenticated() {
        return isAuthenticatedInternal();
    }
}
