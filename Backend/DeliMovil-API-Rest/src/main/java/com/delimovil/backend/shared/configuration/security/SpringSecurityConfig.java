package com.delimovil.backend.shared.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.delimovil.backend.shared.configuration.security.filter.JwtAuthenticationFilter;
import com.delimovil.backend.shared.configuration.security.filter.JwtValidationFilter;
import java.util.Arrays;

@Configuration
@EnableMethodSecurity(prePostEnabled=true)
public class SpringSecurityConfig {

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Bean
    AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests((authz) -> authz
                        // Categories
                        .requestMatchers(HttpMethod.GET, "/api/categories").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/categories").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/api/categories/*").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/categories/*").hasRole("ADMIN")

                        // Products
                        .requestMatchers(HttpMethod.GET, "/api/products").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/products").hasRole("LOCAL")
                        .requestMatchers(HttpMethod.PATCH, "/api/products/*").hasRole("LOCAL")
                        .requestMatchers(HttpMethod.DELETE, "/api/products/*").hasRole("LOCAL")

                        // Client
                        .requestMatchers(HttpMethod.GET, "/api/client").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/client").permitAll()
                        .requestMatchers(HttpMethod.PATCH, "/api/client").hasRole("CLIENT")
                        .requestMatchers(HttpMethod.DELETE, "/api/client").hasAnyRole("CLIENT", "ADMIN")

                        // Delivery
                        .requestMatchers(HttpMethod.GET, "/api/delivery").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/delivery").permitAll()
                        .requestMatchers(HttpMethod.PATCH, "/api/delivery/*").hasRole("DELIVERY")
                        .requestMatchers(HttpMethod.DELETE, "/api/delivery/*").hasAnyRole("DELIVERY", "ADMIN")

                        // Restaurant
                        .requestMatchers(HttpMethod.GET, "/api/restaurant").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/restaurant").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/api/restaurant/*").hasRole("RESTAURANT")
                        .requestMatchers(HttpMethod.DELETE, "/api/restaurant/*").hasRole("ADMIN")

                        // Local
                        .requestMatchers(HttpMethod.GET, "/api/local").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/local").hasRole("RESTAURANT")
                        .requestMatchers(HttpMethod.PATCH, "/api/local").hasRole("RESTAURANT")
                        .requestMatchers(HttpMethod.DELETE, "/api/local").hasRole("RESTAURANT")

                        // Order
                        .requestMatchers(HttpMethod.GET, "/api/order").hasRole("CLIENT")
                        .requestMatchers(HttpMethod.POST, "/api/order").hasRole("CLIENT")
                        .requestMatchers(HttpMethod.PATCH, "/api/order").hasRole("CLIENT")
                        .requestMatchers(HttpMethod.DELETE, "/api/order/*").hasRole("ADMIN")

                        // Role
                        .requestMatchers(HttpMethod.GET, "/api/roles").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/roles").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/roles").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/roles").hasRole("ADMIN")

                        // User
                        .requestMatchers(HttpMethod.GET, "/api/users").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/users").hasRole("ADMIN")

                        .anyRequest().authenticated())
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .addFilter(new JwtValidationFilter(authenticationManager()))
                .csrf(config -> config.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOriginPatterns(Arrays.asList("*"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PUT"));
        config.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    FilterRegistrationBean<CorsFilter> corsFilter() {
        FilterRegistrationBean<CorsFilter> corsBean = new FilterRegistrationBean<>(
                new CorsFilter(corsConfigurationSource()));
        corsBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return corsBean;
    }
}
