package kz.mn.partners.mnp.v1.biletter.config;

import kz.mn.partners.mnp.v1.biletter.dal.entity.UserEntity;
import kz.mn.partners.mnp.v1.biletter.dal.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Cacheable(value = "userDetails", key = "#username", unless = "#result == null")
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            UserEntity user = userRepository.findByEmail(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

            if (!user.getIsActive()) {
                log.warn("Inactive user attempt to login: {}", username);
                throw new UsernameNotFoundException("User is inactive: " + username);
            }

            log.debug("User authenticated successfully: {}", username);
            
            return User.builder()
                    .username(user.getEmail())
                    .password(user.getPasswordHash())
                    .authorities(Collections.singletonList(new SimpleGrantedAuthority("USER")))
                    .build();
                    
        } catch (Exception e) {
            log.error("Error during user authentication for: {}", username, e);
            throw e;
        }
    }
}
