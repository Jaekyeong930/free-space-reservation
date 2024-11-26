package jkkim.space_reservation.service;

import jkkim.space_reservation.repository.MemberRepository;
import jkkim.space_reservation.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;

public class AuthService {
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public AuthService(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public String authenticate(String username) {
        // 토큰을 생성해서 반환
        return jwtTokenProvider.generateToken(username);
    }

    public boolean validateToken(String token) {
        return jwtTokenProvider.validateToken(token);
    }
}
