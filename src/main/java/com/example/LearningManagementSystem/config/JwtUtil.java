package com.example.LearningManagementSystem.config;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component

public class JwtUtil {
    private final String SECRET_KEY = "pP5gZQ6q1bVwU8T7x2L9kQ0r4mN8vY3sD6hJ2lF5tH0="; // 256-bit key
    // move to env var
    private final long EXPIRATION = 1000 * 60 * 60; // 1 hour



    public String extractUserName(String token){
        return extractClaim(token,Claims::getSubject);
    }

    public Date extractExpiration(String token){
        return extractClaim(token,Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims,T> claimsFunction){
        final Claims claims= extractAllClaims(token);
        return claimsFunction.apply(claims);
    }

    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSighInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


    public Boolean isTokenValid(String token, UserDetails userDetails){
        String name= extractUserName(token);
        return (name.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }


    public String generateToken(UserDetails userDetails){
        return createToken(new HashMap<>(),userDetails);
    }


    private String createToken(Map<String,Object> extractClaims,UserDetails userDetails){
        return Jwts.builder()
                .setClaims(extractClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRATION))
                .signWith(getSighInKey(), SignatureAlgorithm.HS256)
                .compact();
    }


    private Key getSighInKey(){
        byte[] keyBytes= Decoders.BASE64URL.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}

