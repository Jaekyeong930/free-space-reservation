package jkkim.space_reservation.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import java.util.Base64;
import java.security.Key;
import java.util.*;

@Component
public class JwtUtil {

    private static final String SECRET_KEY = "my-secret-key"; // JWT 서명 키
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

    public static String decodeBase64Url(String base64Url) {
        // Base64 URL-safe 문자 '-' -> '+' , '_' -> '/' 변환
        String base64 = base64Url.replace('-', '+').replace('_', '/');

        // Base64 인코딩된 문자열의 길이가 4의 배수가 되도록 "=" 패딩 추가
        int paddingLength = 4 - (base64.length() % 4);
        if (paddingLength != 4) {
            base64 += "=".repeat(paddingLength); // Java 17에서 지원되는 String.repeat() 메소드 사용
        }

        // Base64 디코딩
        return new String(Base64.getDecoder().decode(base64));
    }

    public static Claims decodeToken(String token) {
        byte[] decodedKey = Base64.getUrlDecoder().decode(SECRET_KEY);
        return Jwts.parserBuilder()
                .setSigningKey(decodedKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

//    // JWT에서 Header와 Payload를 디코딩하여 반환하는 메소드
//    public String decodeJwtPart(String jwt, int partIndex) {
//        String[] jwtParts = jwt.split("\\.");  // JWT는 '.'로 구분된 세 부분으로 나눠짐
//        if (partIndex < 0 || partIndex > 1) {
//            throw new IllegalArgumentException("Invalid part index. Use 0 for header or 1 for payload.");
//        }
//        return decodeBase64Url(jwtParts[partIndex]);
//    }

    // Authentication 객체 생성
//    public static void main(String[] args) {
//        JwtUtil jwtUtil = new JwtUtil();
//
//        String jwt = "your-jwt-token";
//
//        // JWT에서 Header와 Payload를 디코딩
//        String decodedHeader = jwtUtil.decodeJwtPart(jwt, 0);  // 0은 Header
//        String decodedPayload = jwtUtil.decodeJwtPart(jwt, 1);  // 1은 Payload
//
//        System.out.println("Decoded Header: " + decodedHeader);
//        System.out.println("Decoded Payload: " + decodedPayload);
//    }
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
