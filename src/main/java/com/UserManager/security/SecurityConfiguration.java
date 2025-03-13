package com.UserManager.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity

/**
 * SecurityConfiguration is a configuration class that sets up the security filter chain for the application.
 * It customizes security settings, such as disabling CSRF protection and defining public and authenticated endpoints.
 * The class also integrates a custom JWT Authentication filter.
 */
public class SecurityConfiguration {

    /**
     * jwtAuthentificationFilter
     *
     * A custom filter responsible for handling JWT-based authentication.
     * This filter extracts JWT tokens from incoming requests, validates them,
     * and sets the authentication context if the token is valid.
     *
     * Dependency is injected via the constructor.
     */
    private JwtAuthentificationFilter jwtAuthentificationFilter;

    /**
     * Constructs the SecurityConfiguration with the required JwtAuthentificationFilter.
     *
     * @param jwtAuthentificationFilter the custom JWT authentication filter used in the security chain
     */
    public SecurityConfiguration(JwtAuthentificationFilter jwtAuthentificationFilter) {
        this.jwtAuthentificationFilter = jwtAuthentificationFilter;
    }

    /**
     * Configures the security filter chain for the application.
     *
     * @param http the {@link HttpSecurity} object used to configure security settings
     * @return a configured {@link SecurityFilterChain} bean
     * @throws Exception if any configuration error occurs
     *
     * Configuration Details:
     * - Disables CSRF protection using the lambda-style `csrf(csrf -> csrf.disable())`.
     * - Defines public endpoints using `requestMatchers`:
     *   - `/swagger-ui/**`: Swagger UI for API documentation.
     *   - `/v3/api-docs/**`: OpenAPI/Swagger JSON endpoints.
     *   - `/address/v1/`: A sample public API.
     *   - `/user`: Public user-related API.
     * - Requires authentication for all other endpoints.
     * - Adds the `JwtAuthentificationFilter` to the filter chain before `UsernamePasswordAuthenticationFilter`.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable()) // Disable CSRF protection
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/address/v1/",
                                "/user"
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthentificationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
    /**
     * Registers the custom JwtAuthentificationFilter as a Spring Bean.
     *
     * @return the {@link JwtAuthentificationFilter} instance
     *
     * This method allows Spring to manage the filter bean lifecycle and ensures
     * it can be injected into other components if necessary.
     */
    @Bean
    public JwtAuthentificationFilter jwtAuthentificationFilter() {
        return this.jwtAuthentificationFilter;
    }
}

