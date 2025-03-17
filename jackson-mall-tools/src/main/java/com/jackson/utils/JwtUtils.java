package com.jackson.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

public class JwtUtils {

    private static final String SecretKey = "jackson";

    public static String genJwt(Map<String, Object> claims) {
        long ttlMiss = 864000000L;
        return Jwts.builder().addClaims(claims)
                .signWith(SignatureAlgorithm.HS256, SecretKey.getBytes(StandardCharsets.UTF_8))
                .setExpiration(new Date(System.currentTimeMillis() + ttlMiss))
                .compact();
    }

    public static Claims parseJwt(String token) {
        return Jwts.parser().setSigningKey(SecretKey.getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(token)
                .getBody();
    }
}
