package jkkim.space_reservation.controller;


import jakarta.servlet.http.HttpServletResponse;
import jkkim.space_reservation.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
//@RequestMapping("/api/auth")  <- RESTful API 스타일을 따르기 위한 목적. 로그인, 회원가입, 토큰 발급 등과 같은 인증 관련 API를 모아서 관리하는 목적
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/members/login")
    public String loginForm() {
        return "members/login";  // 로그인 폼을 반환
    }

    // POST /api/auth/login
    @PostMapping("/members/login")
    public String login(@ModelAttribute LoginForm loginForm, HttpServletResponse response, Model model) {
        // 로그인 시 토큰 발급
        JwtResponse jwtResponse = authService.login(loginForm);

        if (jwtResponse != null) {
            // JWT를 클라이언트에게 'Authorization' 헤더에 추가
            response.addHeader("Authorization", "Bearer " + jwtResponse.getToken());
            System.out.println("로그인성공!!!!!!!!!!!");
            return "members/loginSuccess";  // 로그인 성공 후 홈 페이지로 리다이렉트
        } else {
            model.addAttribute("loginErrorMessage", "아이디 또는 비밀번호가 일치하지 않습니다.");
            return "members/login";  // 로그인 실패 시 로그인 페이지로 리턴
        }
    }
}
