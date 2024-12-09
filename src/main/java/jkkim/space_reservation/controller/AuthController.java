package jkkim.space_reservation.controller;


import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jkkim.space_reservation.security.JwtUtil;
import jkkim.space_reservation.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.security.authentication.AuthenticationManager;

@Controller
//@RequestMapping("/api/auth")  <- RESTful API 스타일을 따르기 위한 목적. 로그인, 회원가입, 토큰 발급 등과 같은 인증 관련 API를 모아서 관리하는 목적
public class AuthController {

    @Autowired
    private final AuthService authService;
    @Autowired
    private final JwtUtil jwtUtil;


    public AuthController(AuthService authService, JwtUtil jwtUtil) {
        this.authService = authService;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/members/login")
    public String loginForm() {
        return "members/login";  // 로그인 폼을 반환
    }

    // POST /api/auth/login
    @PostMapping("/members/login")

    public ResponseEntity<?> login(@RequestBody LoginForm loginForm,
                                   HttpServletResponse response,
                                   RedirectAttributes redirectAttributes) {
        try {
            // 인증 및 사용자 정보 조회
            var member = authService.authenticate(loginForm);

            // JWT 생성
            String token = jwtUtil.generateAccessToken(member.getMemberName(), member.getMemberRole());

            // JWT 쿠키 저장
            Cookie jwtCookie = new Cookie("accessToken", token);
            jwtCookie.setHttpOnly(true); // JavaScript에서 접근 불가
            jwtCookie.setSecure(false);  // HTTPS에서만 전송
            jwtCookie.setPath("/");     // 모든 경로에 대해 유효
            jwtCookie.setMaxAge(60*60); // 1시간 (초 단위)
            // SameSite 속성 설정
            response.addHeader("Set-Cookie",
                    "accessToken=" + token + "; HttpOnly; Secure; Path=/; Max-Age=3600; SameSite=None");


            response.addCookie(jwtCookie); // 쿠키를 응답에 추가

            // 로그인 성공 후 홈 화면으로 리다이렉트
//            return ResponseEntity.status(HttpStatus.FOUND)  // 302 Found (리다이렉트)
//                    .header("Location", "/")
//                    .build();

            return ResponseEntity.ok("Login Successful");

        } catch (IllegalArgumentException e) {
            // 로그인 실패 시 로그인 페이지로 리다이렉트하면서 에러 메시지 전달
            redirectAttributes.addFlashAttribute("errorMessage", "아이디 또는 비밀번호가 잘못되었습니다.");
            return ResponseEntity.status(HttpStatus.FOUND)
                    .header("Location", "/members/login")  // 로그인 페이지로 리다이렉트
                    .build();
        } catch (Exception e) {
            // 기타 예외 처리
            redirectAttributes.addFlashAttribute("errorMessage", "서버 오류가 발생했습니다. 다시 시도해 주세요.");
            return ResponseEntity.status(HttpStatus.FOUND)
                    .header("Location", "/members/login")  // 로그인 페이지로 리다이렉트
                    .build();
        }
    }

    @PostMapping("/members/logout")
    public ResponseEntity<?> logout(HttpServletResponse response) {
        // JWT 쿠키 제거
        Cookie jwtCookie = new Cookie("accessToken", null); // 쿠키 값 제거
        jwtCookie.setHttpOnly(true);
        jwtCookie.setSecure(true);
        jwtCookie.setPath("/");
        jwtCookie.setMaxAge(0); // 쿠키 즉시 만료
        response.addCookie(jwtCookie);

        // 클라이언트가 메시지를 표시하도록 상태 메시지 포함
        return ResponseEntity.status(HttpStatus.FOUND)  // 302 Found
                .header("Location", "/")  // 홈페이지로 리다이렉트
                .body("로그인이 필요합니다"); // 상태 메시지 전달
    }



}


//    public String login(@ModelAttribute LoginForm loginForm, HttpServletResponse response, Model model) {
//        // 로그인 시 토큰 발급
//        JwtResponse jwtResponse = authService.login(loginForm);
//
//        if (jwtResponse != null) {
//            // JWT를 클라이언트에게 'Authorization' 헤더에 추가
//            response.addHeader("Authorization", "Bearer " + jwtResponse.getToken());
//            System.out.println("로그인성공!!!!!!!!!!!");
//            return "members/loginSuccess";  // 로그인 성공 후 홈 페이지로 리다이렉트
//        } else {
//            model.addAttribute("loginErrorMessage", "아이디 또는 비밀번호가 일치하지 않습니다.");
//            return "members/login";  // 로그인 실패 시 로그인 페이지로 리턴
//        }
//    }
