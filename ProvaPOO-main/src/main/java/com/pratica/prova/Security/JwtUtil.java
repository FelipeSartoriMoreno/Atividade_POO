package com.pratica.prova.Security;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    // Chave secreta de 256 bits (32 caracteres)
    private static final String SECRET_KEY = "234567890123456789012345678901232"; // 32 caracteres = 256 bits
    private static final Key key = new SecretKeySpec(SECRET_KEY.getBytes(), SignatureAlgorithm.HS256.getJcaName());

    // Gerar o token JWT
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hora de expiração
                .signWith(key)  // Passando a chave de forma correta
                .compact();
    }

    // Extrair as informações de um token JWT
    public Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(key)  // Passando a chave de forma correta
                .parseClaimsJws(token)
                .getBody();
    }

    // Extrair o nome de usuário do token JWT
    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    // Verificar se o token expirou
    public boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }

    // Validar o token (verificar expiração)
    public boolean validateToken(String token) {
        return !isTokenExpired(token);
    }
}
