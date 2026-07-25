package com.gym.gym.Config;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
    @Value("${jwt.secreta}")
    private String Secreta;
    private final long tiempoExpiracionToken = 1000 * 60 * 60 * 8;
    
    private SecretKey getKey(){
        return Keys.hmacShaKeyFor(Secreta.getBytes());
    }

    public String generarToken(String correo){
        return Jwts.builder().subject(correo).issuedAt(new Date()).expiration(new Date(System.currentTimeMillis() + tiempoExpiracionToken))
        .signWith(getKey()).compact();
    }

    public String extraerCorreo(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean validarToken(String token) {
        try {
            extraerCorreo(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

