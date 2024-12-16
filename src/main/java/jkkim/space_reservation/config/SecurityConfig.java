package jkkim.space_reservation.config;

import jkkim.space_reservation.security.JwtAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// Spring Security 설정
@EnableWebSecurity
@Configuration
public class SecurityConfig {

    // SecurityFilterChain으로 HttpSecurity 설정
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())  // CSRF 비활성화 (REST API에서는 CSRF 보호가 필요하지 않음)
                .authorizeHttpRequests(authorizeHttpRequests ->
                        authorizeHttpRequests
                                // 관리자 권한이 필요한 경로는 ROLE_ADMIN만 접근 가능
                                .requestMatchers("/admin/**").hasRole("ADMIN")
                                // 예약 및 마이페이지 기능은 로그인 필요
                                .requestMatchers("/user/**").hasRole("USER")
                                // 로그인, 회원가입 페이지는 모두 접근 가능
                                .requestMatchers("/","/login", "/register").permitAll()
                                // 나머지 페이지는 인증된 상태에서만 접근 가능
                                .anyRequest().authenticated()
                )
                // formLogin을 비활성화하여 세션을 생성하지 않도록 함
                .formLogin(form ->
                        form.disable()
                )
                // 세션 관리를 "STATELESS"로 설정하여 세션을 사용하지 않도록 설정
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                // 인증되지 않은 사용자가 접근할 경우, 로그인 페이지로 리다이렉트
                .exceptionHandling(exceptionHandling ->
                        exceptionHandling.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"))
                )
                // JWT 필터를 추가하여 로그인 과정 없이 JWT토큰을 이용한 인증 처리
//                .addFilterBefore(new JwtAuthFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class)
                // 로그아웃 URL설정
                .logout(logout -> logout.logoutUrl("/logout"));

        return http.build();
    }

    // AuthenticationManager를 Bean으로 등록
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }



    // 비밀번호 암호화 방식 설정 (BCryptPasswordEncoder)
    @Bean // PasswordEncoder Bean 등록
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // BCryptPasswordEncoder를 사용하여 비밀번호 암호화
    }
}
