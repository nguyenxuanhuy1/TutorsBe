package com.example.demo.security;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtil {
    private final String SECRET_KEY = "mysecretkey123456789101112131415161718192021222324"; // key bảo mật
    //    private final long ACCESS_TOKEN_EXPIRATION = 1000 * 60 * 60 ;
    private final long ACCESS_TOKEN_EXPIRATION = 1000 * 60 * 60;
    // refresh token: 5 phút
    private final long REFRESH_TOKEN_EXPIRATION = 1000L * 60 * 60 * 24;

    public String generateToken(Long userId, String email, String role) {
        return Jwts.builder()
                .claim("id", userId)
                .claim("email", email)
                .claim("role", role)
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public String generateRefreshToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRATION))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public String extractEmail(String token) {
        return extractClaim(token, claims -> {
            String email = claims.get("email", String.class);
            return (email != null) ? email : claims.getSubject();
        });
    }

    // Check token hết hạn
    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public boolean validateToken(String token, String email) {
        final String extractedEmail = extractEmail(token);
        return (extractedEmail.equals(email) && !isTokenExpired(token));
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }
}
