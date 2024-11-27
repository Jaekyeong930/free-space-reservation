package jkkim.space_reservation.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtProvider;

    public JwtAuthenticationFilter(JwtUtil jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

    }

    private String parseBearerToekn(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");

        // 인증내용이 있는지 확인 후 결과를 저장할 변수
        boolean hasAuthorization = StringUtils.hasText(authorization);
        // 인증내용이 없으면 null 반환
        if (!hasAuthorization) { return null; }

        boolean isBearer = authorization.startsWith("Bearer ");
        if(!isBearer) { return null; }

        String token = authorization.substring(7); // 7번부터 꺼내면 home부분
        return token;
    }
}
