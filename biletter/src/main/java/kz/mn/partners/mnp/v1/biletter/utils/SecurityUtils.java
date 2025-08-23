package kz.mn.partners.mnp.v1.biletter.utils;

import kz.mn.partners.mnp.v1.biletter.dal.entity.UserEntity;
import kz.mn.partners.mnp.v1.biletter.dal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecurityUtils {
    private final UserRepository userRepository;

    public UserEntity getCurrentUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        return userRepository.findByEmail(username)
            .orElseThrow(() -> new RuntimeException("User not found: " + username));
    }
}
