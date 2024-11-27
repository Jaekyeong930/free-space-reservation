package jkkim.space_reservation.security;

import io.jsonwebtoken.*;
import jkkim.space_reservation.controller.LoginForm;
import org.springframework.stereotype.Component;

import java.util.Date;

// JWT 토근을 생성하고 검증하는 유틸리티 클래스
@Component
public class JwtUtil {

    // JWT는 서명 기반 토큰으로, 발급된 토큰의 서명(signature)을 통해 데이터의 무결성을 보장
    // JWT의 목적은 '데이터가 변조되지 않았음을 증명'하는 것이므로, 하나의 키로도 충분함
    // 단, 비밀 키는 극비로 유지해야 함.
    private final String SECRET_KEY = "mySecretKey"; // 비밀 키
    private final long EXPIRATION_TIME = 1000 * 60 * 60; // 토큰 만료 시간 (1초 * 60 * 60)

    // JWT 생성
    public String generateToken(LoginForm loginForm) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);

        String jwt = Jwts.builder()
                .setSubject(loginForm.getMemberName()) // 주체
                .setIssuedAt(new Date()) // 생성시간
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // 만료시간
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY) // 알고리즘과 비밀키 지정
                .compact();
        return jwt;
    }

    // JWT 유효성 검증
    public String validateToken(String jwt) {

        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(jwt).getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return claims.getSubject();
    }


    // JWT에서 사용자 아이디 추출
    public String getUsernameFromToken(String jwt) {
        // Claims는 JWT의 Payload에서 정보를 읽도 다룰 때 사용하는 객체
        // JWT의 Payload는 JSON 형식으로 사용자 데이터
        // Clamis는 사용자 데이터(sub, name, exp 등)를 담고 있으며, sub
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(jwt)
                .getBody();
        return claims.getSubject();
    }

}
