package jkkim.space_reservation.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtUtil jwtUtil;  // JwtTokenProvider 주입

    public SecurityConfig(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())  // 새로운 비활성화 방식
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/login").permitAll()  // 로그인 API는 인증 없이 접근 가능
                        .anyRequest().authenticated());  // 나머지 API는 인증 필요
//        CSRF (Cross-Site Request Forgery)는 웹 애플리케이션에서 발생할 수 있는 보안 취약점 중 하나로,
//        사용자가 인증된 상태에서 악의적인 공격자가 사용자의 권한을 도용하여 원하지 않는 작업을 수행하게 만드는 공격
        return http.build();
    }
}