package com.ilyamorozov.bootpark.security;

import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

public class SecurityConstants {
    public static final long JWT_EXPIRATION = 864000000;
    public static final SecretKey JWT_SECRET = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS512);

}
