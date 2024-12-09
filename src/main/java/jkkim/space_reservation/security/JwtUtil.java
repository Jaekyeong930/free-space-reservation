package jkkim.space_reservation.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.ArrayList;
import java.util.Date;

@Component
public class JwtUtil {

    private final String SECRET_KEY = "my-secret-key"; // JWT 서명 키
    private static final long EXPIRATION_TIME = 3600; // 1시간

    // JWT 생성
    public String generateAccessToken(String username, boolean isAdmin) {
        return Jwts.builder()
                .setSubject(username)
                .claim("memberRole", isAdmin? "ADMIN":"USER")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    // JWT 검증
    public Claims validateToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    // 토큰 만료 여부 확인
    public boolean isTokenExpired(Claims claims) {
        // 만료되지 않은 토큰이면 True 반환
        return !claims.getExpiration().before(new Date());
    }

    // 토큰에서 유저 인증 정보 추출
    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();

        String username = claims.getSubject();
        return new UsernamePasswordAuthenticationToken(
                new User(username, "", new ArrayList<>()), null, new ArrayList<>());
    }
}


//    // Access Token을 HTTP Only 쿠키에 설정
//    public void setAccessTokenInCookie(HttpServletResponse response, String jwtToken) {
//        // HTTP Only 쿠키로 Access Token 설정
//        Cookie cookie = new Cookie("accessToken", jwtToken);
//        cookie.setHttpOnly(true);  // 자바스크립트에서 접근 불가
//        cookie.setSecure(true);    // HTTPS 연결에서만 사용
//        cookie.setPath("/");       // 모든 경로에서 쿠키 사용
//        cookie.setMaxAge(3600);    // 1시간 유효
//        cookie.setDomain("yourdomain.com"); // 도메인 설정, 필요 시
//
//        // SameSite 설정 (스프링 3에서 지원)
////        cookie.setAttribute("SameSite", "Strict");  // CSRF 방어용 SameSite 설정
//
//        // SameSite 속성을 직접 Set-Cookie 헤더에 추가
//        response.setHeader("Set-Cookie", "accessToken=" + jwtToken +
//                "; HttpOnly; Secure; SameSite=Strict; Path=/; Max-Age=3600");
//
////        response.addCookie(cookie);
//    }
