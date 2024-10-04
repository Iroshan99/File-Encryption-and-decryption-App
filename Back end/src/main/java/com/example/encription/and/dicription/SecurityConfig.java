package com.example.encription.and.dicription;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Disable CSRF and allow access to the API endpoints without authentication
        http
                .csrf(AbstractHttpConfigurer::disable) // Updated CSRF configuration
                .authorizeHttpRequests(authorize ->
                        authorize
                                .requestMatchers("/api/encrypt","/api/decrypt").permitAll() // Updated for Spring Security 6.1+
                                .anyRequest().permitAll()
                );

        return http.build();
    }
}
