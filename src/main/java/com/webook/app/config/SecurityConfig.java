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
                        "style-src 'self' 'unsafe-inline' https://js.stripe.com https://fonts.googleapis.com; " +
                        "font-src 'self' https://js.stripe.com https://fonts.googleapis.com https://fonts.gstatic.com data:; " +
                        "connect-src 'self' https://api.stripe.com https://api.imgbb.com; " +
                        "img-src 'self' data: https://i.ibb.co;"
                    )
                )
            );

        return http.build();
    }
}
