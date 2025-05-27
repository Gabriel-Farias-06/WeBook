package com.webook.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
            .headers(headers -> headers
                .contentSecurityPolicy(csp -> csp
                    .policyDirectives(
                        "default-src 'self'; " +
                        "script-src 'self' https://js.stripe.com; " +
                        "style-src 'self' 'unsafe-inline' https://js.stripe.com; " +
                        "font-src 'self' https://js.stripe.com https://fonts.gstatic.com; " +
                        "connect-src 'self' https://api.stripe.com https://api.imgbb.com; " +
                        "img-src 'self' data: https://i.ibb.co;"
                    )
                )
            );

        return http.build();
    }
}
