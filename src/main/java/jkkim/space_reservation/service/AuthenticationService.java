package jkkim.space_reservation.service;

import jkkim.space_reservation.controller.LoginForm;
import jkkim.space_reservation.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    // 로그인 인증 및 JWT 토큰 반환
    public String authenticate(LoginForm loginForm) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginForm.getMemberName(), loginForm.getMemberPassword())
        );

        // 인증 성공 후 JWT 생성
        return jwtUtil.generateToken(loginForm.getMemberName());
    }

}
