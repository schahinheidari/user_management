package com.UserManager.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

/**
 * Service class for handling JWT (JSON Web Token) creation, validation, and claim extraction.
 */
@Service
public class JwtService {

    /**
     * The secret key used for signing and verifying JWT tokens.
     * Loaded from application properties: "application.security.jwt.secret-key".
     */
    @Value("${application.security.jwt.secret-key}")
    private String secretKey;

    /**
     * The expiration time (in milliseconds) for access tokens.
     * Loaded from application properties: "application.security.jwt.expiration".
     */
    @Value("${application.security.jwt.expiration}")
    private long jwtExpiration;

    /**
     * The expiration time (in milliseconds) for refresh tokens.
     * Loaded from application properties: "application.security.jwt.refresh-token.expiration".
     */
    @Value("${application.security.jwt.refresh-token.expiration}")
    private long refreshExpiration;

    /**
     * Extracts the username (subject) from the given JWT token.
     *
     * @param token the JWT token.
     * @return the username extracted from the token.
     */
    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extracts a specific claim from the given JWT token.
     *
     * @param <T>            the type of the claim.
     * @param token          the JWT token.
     * @param claimsResolver a function to resolve the claim.
     * @return the extracted claim.
     */
    public <T> T extractClaim(String token, Function<Claims,T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Generates a new JWT access token for the given user details.
     *
     * @param userDetails the user details.
     * @return a new JWT token.
     */
    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);
    }

    /**
     * Generates a new JWT token with additional custom claims.
     *
     * @param extraClams additional claims to include in the token.
     * @param userDetails the user details.
     * @return a new JWT token.
     */
    public String generateToken(
            Map<String , Object> extraClams,
            UserDetails userDetails){
        return buildToken(extraClams, userDetails, jwtExpiration);
    }

    /**
     * Generates a new JWT refresh token for the given user details.
     *
     * @param userDetails the user details.
     * @return a new refresh token.
     */
    public String generateRefreshToken(UserDetails userDetails){
        return buildToken(new HashMap<>(), userDetails, refreshExpiration);
    }

    /**
     * Builds a JWT token with the specified claims, user details, and expiration time.
     *
     * @param extraClams additional claims to include in the token.
     * @param userDetails the user details.
     * @param expiration  the token expiration time in milliseconds.
     * @return a new JWT token.
     */
    private String buildToken(
            Map<String , Object> extraClams,
            UserDetails userDetails,
            long expiration){
        return Jwts.builder()
                .setClaims(extraClams)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Validates a JWT token for the given user details.
     *
     * @param token       the JWT token.
     * @param userDetails the user details.
     * @return true if the token is valid, false otherwise.
     */
    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    /**
     * Checks if a JWT token is expired.
     *
     * @param token the JWT token.
     * @return true if the token is expired, false otherwise.
     */
    public boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    /**
     * Extracts the expiration date from the JWT token.
     *
     * @param token the JWT token.
     * @return the expiration date of the token.
     */
    public Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Extracts all claims from the JWT token.
     *
     * @param token the JWT token.
     * @return all claims contained in the token.
     */
    private Claims extractAllClaims(String token){
        return Jwts.parser()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJwt(token)
                .getBody();
    }

    /**
     * Retrieves the signing key used for signing the JWT tokens.
     *
     * @return the signing key.
     */
    private Key getSignInKey(){
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * Extracts a list of granted authorities (roles) from the JWT token.
     *
     * @param token the JWT token.
     * @return a list of granted authorities extracted from the token.
     */
    public List<GrantedAuthority> extractAuthorities(String token) {
        DecodedJWT decodedJWT = JWT.decode(token);
        List<String> roles = decodedJWT.getClaim("roles").asList(String.class);

        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }

        return authorities;
    }
}
