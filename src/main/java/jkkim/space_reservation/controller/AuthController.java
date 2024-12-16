package jkkim.space_reservation.controller;

import jkkim.space_reservation.dto.JwtResponseDto;
import jkkim.space_reservation.dto.LoginRequestDto;
import jkkim.space_reservation.util.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public AuthController(JwtUtil jwtUtil, AuthenticationManager authenticationManager) {
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }


    // 홈 페이지
    @GetMapping("/")
    public String home() {
        return "home"; // home.html로 이동
    }

    // 로그인 페이지
    @GetMapping("/login")
    public String loginPage() {
        return "login"; // login.html로 이동
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getLoginName(), request.getLoginName())
        );

        // JWT 토큰 생성
        String token = jwtUtil.generateToken(authentication);

        // JWT 토큰 반환
        return ResponseEntity.ok(new JwtResponseDto(token));
    }

    // 회원가입 페이지
    @GetMapping("/register")
    public String registerPage() {
        return "register"; // register.html로 이동
    }

    // 회원가입 처리
    @PostMapping("/register")
    public String register(@RequestParam String registerName, @RequestParam String registerPassword) {
        // 회원가입 로직 (예: DB에 유저 저장)
        return "redirect:/login"; // 회원가입 후 로그인 페이지로 리다이렉트
    }
}
