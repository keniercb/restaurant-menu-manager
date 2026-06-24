package dev.saetasoft.restaurantmenumanager.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.SecurityFilterChain;

@Slf4j
@Configuration
public class SecurityDebugConfig {
    @Bean
    public CommandLineRunner debugSecurity(@Qualifier("filterChain") SecurityFilterChain securityFilterChain) {
        return args -> {
            log.info("SecurityFilterChain loaded: {}", securityFilterChain.getClass().getName());
        };
    }
}
