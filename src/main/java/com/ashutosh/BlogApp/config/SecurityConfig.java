package com.ashutosh.BlogApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // Disable CSRF protection for testing
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll() // Allow signup/login
                        .requestMatchers("/blogs/**").permitAll() // Allow all blog-related endpoints
                        .requestMatchers("/report/**").permitAll() // Allow access to report endpoints
                        .anyRequest().permitAll() // Allow all other requests without authentication
                )
                .formLogin(form -> form
                        .loginPage("/auth/login") // Custom login page
                        .loginProcessingUrl("/auth/login") // POST endpoint to process login form submission
                        .defaultSuccessUrl("/blogs/dashboard", true) // Redirect to dashboard on successful login
                        .failureUrl("/auth/login?error=true") // Redirect back to login on failure
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout") // Custom logout endpoint
                        .logoutSuccessUrl("/auth/login") // Redirect to login page after logout
                        .invalidateHttpSession(true) // Invalidate session on logout
                        .deleteCookies("JSESSIONID") // Delete session cookie
                        .permitAll()
                );

        return http.build();
    }
}