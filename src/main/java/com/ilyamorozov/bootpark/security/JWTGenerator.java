package com.ilyamorozov.bootpark.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.ilyamorozov.bootpark.security.SecurityConstants.jwtSecretKey;


@Component
public class JWTGenerator {

    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + SecurityConstants.JWT_EXPIRATION);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(currentDate)
                .setExpiration(expireDate)
                .signWith(jwtSecretKey) // Используем SecretKey вместо строки
                .compact();
    }

    public String getUsernameFromJWT(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(jwtSecretKey) // Используем SecretKey
                .build()
                .parseClaimsJws(token) // Используем parseClaimsJws вместо parseClaimsJwt
                .getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(jwtSecretKey) // Используем SecretKey
                    .build()
                    .parseClaimsJws(token); // Если токен корректен, он успешно парсится
            return true;
        } catch (Exception e) {
            throw new AuthenticationCredentialsNotFoundException("JWT is expired or incorrect", e);
        }
    }
}
