package klu.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTManager {

    private static final String SEC_KEY = "qwertyuiopasdfghjklzxcvbnm7896541230";
    private static final SecretKey key = Keys.hmacShaKeyFor(SEC_KEY.getBytes());

    public String generateToken(String email) {
        Map<String, String> data = new HashMap<>();
        data.put("email", email);

        return Jwts.builder()
                .setClaims(data)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 24 hrs
                .signWith(key)
                .compact();
    }

    public String validateToken(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            Date expiry = claims.getExpiration();
            if (expiry == null || expiry.before(new Date())) {
                return "401";
            }
            return claims.get("email", String.class);
        } catch (Exception e) {
            return "401";
        }
    }
}
