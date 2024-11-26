package jkkim.space_reservation.security;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    private final String secretKey = "mySecretKey"; // 비밀 키
    private final long validityInMilliseconds = 3600000; // 1시간

    // JWT 생성
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + validityInMilliseconds))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    // JWT에서 사용자 이름 추출
    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    // JWT 검증
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
