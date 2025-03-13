package com.UserManager.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Component
public class JwtAuthentificationFilter extends OncePerRequestFilter {

    /**
     * Purpose: Logs information about the filter's operations for debugging or monitoring.
     * Details: Uses SLF4J for logging.
     */
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthentificationFilter.class);
    /**
     * Purpose: Specifies the HTTP header key used to pass the JWT token.
     * Value: "Authorization"
     */
    private static final String HEADER_STRING = "Authorization";
    /**
     * Purpose: Provides utility methods to handle JWT tokens, including extracting usernames, authorities, and validating tokens.
     * Details: Injected as a dependency via constructor (@RequiredArgsConstructor).
     */
    private final JwtService jwtService;

    /**
     * Purpose: Core method of the filter that processes each HTTP request and validates the JWT token if present.
     * @param httpRequest The incoming HTTP request object.
     * @param httpResponse The outgoing HTTP response object.
     * @param filterChain The filter chain used to pass control to the next filter in the pipeline.
     * @throws ServletException if errors occur during filtering.
     * @throws IOException if errors occur during filtering.
     */
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest httpRequest,
            @NonNull HttpServletResponse httpResponse,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        System.out.println("Filter...");
        /**
         * Extracts the JWT token from the Authorization header.
         * Checks if the header is null or doesn't start with "Bearer ".
         * If true, skips token processing and proceeds to the next filter.
         * Extracts the JWT token by removing the "Bearer " prefix from the header value.
         * Uses jwtService to extract the username and roles from the token
         */
        final String authHeader = httpRequest.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(httpRequest, httpResponse);
            return;
        }

        String jwtToken = authHeader.substring(7);
        String username = jwtService.extractUsername(jwtToken);
        List<GrantedAuthority> authorities = jwtService.extractAuthorities(jwtToken);
        /**
         * Creates a UserDetails object with the extracted username and roles.
         * Creates a UsernamePasswordAuthenticationToken object for the authenticated user.
         * Sets the authentication details and updates the security context.
         */
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = new User(username, "", authorities);
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities()
            );
            authToken.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails((HttpServletRequest) httpRequest)
            );
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }

        filterChain.doFilter(httpRequest, httpResponse);
    }
}



