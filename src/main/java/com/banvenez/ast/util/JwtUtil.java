package com.banvenez.ast.util;

import com.banvenez.ast.dto.ValidaUser;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String jwtSecret;
    @Value("${jwt.expiration}")
    private int jwtExpirationMs;
    private SecretKey key;

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

    // Generate JWT token
    public String generateToken(String username, ValidaUser user) {
        Map<String, Object> claims = new HashMap<>();
        //ToDo esto debe mejorar, deberia ver todos los datos basicos en la bd.
        claims.put("username", username);
        claims.put("cod_aplicacion", user.getCod_aplicacion());
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setClaims(claims)
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public Map<String, Object> getAllClaimsFromToken(String token) {
        try {
            String token_format = token.substring(7);
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token_format)
                    .getBody();
        } catch (Exception e) {
            return null;
        }
    }

    // Validate JWT token
    public boolean validateJwtToken(String token) {
        try {
            if (!tokenHeaderExist(token)) throw new JwtException("token no existe");
            token = token.substring(7);
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException e) {
            throw new JwtException("Invalid JWT token");
        } catch (ExpiredJwtException e) {
            throw new JwtException("JWT token is expired");
        } catch (UnsupportedJwtException e) {
            throw new JwtException("token no soportado");
        } catch (IllegalArgumentException e) {
            throw new JwtException("token falso");
        }
    }

    public Boolean tokenHeaderExist(String token) {
        if (token == null || !token.startsWith("Bearer ")) {
            return false;
        } else {
            return true;
        }
    }
}


