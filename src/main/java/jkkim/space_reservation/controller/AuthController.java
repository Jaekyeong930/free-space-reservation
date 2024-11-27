package jkkim.space_reservation.controller;

import jkkim.space_reservation.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;

public class AuthController {
    private final AuthenticationService authenticationService;

    @Autowired
    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    // 로그인 성공 시 토큰 발급
    public String login(String username) {
        String token = authenticationService.authenticate(username);
        return "Bearer " + token; // 클라이언트에게 JWT 전달
    }
}
