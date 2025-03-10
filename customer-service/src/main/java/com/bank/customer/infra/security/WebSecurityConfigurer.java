package com.bank.customer.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

/**
 *
 * TODO: using JWT
 */

/**
 * Security configuration using Spring Security.
 * <p>
 * Configures authentication, authorization, and disables CSRF.
 * </p>
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(jsr250Enabled = true)
public class WebSecurityConfigurer {

    /**
     * Configures the security filter chain.
     *
     * @param http The security configuration.
     * @return The security filter chain.
     * @throws Exception If security configuration fails.
     */
    @Bean
    public SecurityFilterChain filterChain(final HttpSecurity http) throws Exception {

        final String[] authWhitelist = {
                // Swagger and H2 Console
                "/swagger-ui/**",
                "/v3/api-docs/**",
                "/db-console/**",

                // Customers
                "/api/customers/**"
        };

        //security
        http
                .cors(withDefaults())
                .csrf(AbstractHttpConfigurer::disable)  // Disable CSRF globally if using POST requests without CSRF token
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin())) // For H2 console
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(authWhitelist).permitAll()
                        .anyRequest().authenticated()
                );

        return http.build();
    }
}
