package jkkim.space_reservation.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;


import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class JwtUtil {


    private String SECRET_KEY = "MySecretKey";  // 비밀 키

    // JWT 토큰을 생성하는 메서드
    public String generateToken(Authentication authentication) {
        // 인증 객체에서 사용자 이름과 권한 가져오기
        String username = authentication.getName(); // 사용자 이름
        List<String> roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()); // 권한 리스트

        //JWT 토큰 생성
        return Jwts.builder()
                .setSubject(username)  // 사용자 이름을 Subject로 설정
                .claim("roles", roles) // 권한 정보 추가
                .setIssuedAt(new Date())  // 발급 일자
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))  // 만료 시간 (1시간 후)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)  // 비밀 키로 서명
                .compact();  // 토큰 생성
    }

    // JWT 토큰에서 사용자 이름을 추출하는 메서드
    public String extractMemberName(String token) {
        return extractClaims(token).getSubject();
    }

    // JWT 토큰에서 Claims (payload) 부분을 추출하는 메서드
    private Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    // JWT 토큰이 유효한지 검증하는 메서드
    public boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }

    // 토큰 검증 메서드
    public boolean validateToken(String token, String username) {
        return (username.equals(extractMemberName(token)) && !isTokenExpired(token));
    }
}
