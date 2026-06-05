package com.maternidade.maternidadeBackendApp.infra.security;

import com.maternidade.maternidadeBackendApp.domain.entity.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    @Value("${jwt.access-token-secret}")
    private String accessTokenSecret;

    @Value("${jwt.refresh-token-secret}")
    private String refreshTokenSecret;

    @Value("${jwt.access-token-expiration}")
    private long accessTokenExpiration;

    @Value("${jwt.refresh-token-expiration}")
    private long refreshTokenExpiration;

    public String generateAccessToken(Usuario usuario) {
        return buildToken(usuario, accessTokenSecret, accessTokenExpiration);
    }

    public String generateRefreshToken(Usuario usuario) {
        return buildToken(usuario, refreshTokenSecret, refreshTokenExpiration);
    }

    public String generateAccessTokenFromClaims(Claims claims) {
        return Jwts.builder()
                .claims(claims)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + accessTokenExpiration))
                .signWith(getKey(accessTokenSecret))
                .compact();
    }

    public Claims validateAccessToken(String token) {
        return parseToken(token, accessTokenSecret);
    }

    public Claims validateRefreshToken(String token) {
        return parseToken(token, refreshTokenSecret);
    }

    private String buildToken(Usuario usuario, String secret, long expiration) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("nome", usuario.getNome());
        claims.put("email", usuario.getEmail());
        claims.put("user_id", usuario.getUserId());
        claims.put("telefone", usuario.getTelefone());

        return Jwts.builder()
                .claims(claims)
                .subject(usuario.getEmail())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getKey(secret))
                .compact();
    }

    private Claims parseToken(String token, String secret) {
        return Jwts.parser()
                .verifyWith(getKey(secret))
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey getKey(String secret) {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }
}
