package com.ilyamorozov.bootpark.security;

import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

public class SecurityConstants {
    public static final long JWT_EXPIRATION = 86400;
    public static final String JWT_SECRET = "secret";
    public static final SecretKey jwtSecretKey = Keys.hmacShaKeyFor(SecurityConstants.JWT_SECRET.getBytes());

}
