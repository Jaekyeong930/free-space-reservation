package jkkim.space_reservation.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

        private final JwtUtil jwtUtil;
        private final UserDetailsService userDetailsService;

        public SecurityConfig(JwtUtil jwtUtil, UserDetailsService userDetailsService) {
            this.jwtUtil = jwtUtil;
            this.userDetailsService = userDetailsService;
        }

        // SecurityFilterChain 설정
        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http.csrf(csrf -> csrf.disable())  // CSRF 비활성화
                    .authorizeRequests(authorizeRequests ->
                            authorizeRequests
                                    // 관리자 권한이 필요한 경로는 ROLE_ADMIN만 접근 가능
                                    .requestMatchers("/admin/**").hasRole("ADMIN")
                                    // 예약 및 마이페이지 기능은 로그인 필요
                                    .requestMatchers("/reservation/**", "/mypage/**").authenticated()
                                    // 나머지 페이지는 모두 접근 가능
                                    .anyRequest().permitAll()
                    )
                    .formLogin(form ->
                            form.loginPage("/members/login") // 로그인 페이지 URL 지정
                                    .permitAll() // 로그인 페이지는 누구나 접근 가능
                    )
                    .exceptionHandling(exceptionHandling ->
                            exceptionHandling.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/members/login"))
                    )
                    // JWT 필터 등록
                    .addFilterBefore(new JwtAuthFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);

            return http.build();
        }

        // AuthenticationManager 설정 (람다식 사용)
        // HttpSecurity에서 AuthenticationManagerBuilder를 얻어오고, 그 후 userDetailsService와 passwordEncoder를 설정한 후 build() 메서드 호출
        // 이전에는 http.getSharedObject(AuthenticationManagerBuilder.class)에서 바로 build()를 호출했으나,
        // 최신 방식에 맞게 AuthenticationManagerBuilder 객체를 먼저 만들어 build()를 호출하는 방식으로 수정
        @Bean
        public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
            AuthenticationManagerBuilder authenticationManagerBuilder =
                    http.getSharedObject(AuthenticationManagerBuilder.class);

            authenticationManagerBuilder
                    .userDetailsService(userDetailsService)  // UserDetailsService 설정
                    .passwordEncoder(passwordEncoder());    // PasswordEncoder 설정

            return authenticationManagerBuilder.build();
        }

    // PasswordEncoder를 @Bean으로 등록 (비밀번호 암호화 용)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // BCryptPasswordEncoder 사용
    }
}