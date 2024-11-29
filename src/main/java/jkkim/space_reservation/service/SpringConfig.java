package jkkim.space_reservation.service;

import jakarta.persistence.EntityManager;
import jkkim.space_reservation.repository.*;
import jkkim.space_reservation.service.MemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SpringConfig {

    // JPA용 상수
//    private final EntityManager em;
    // Spring Data JPA용 상수
    @Autowired
    private final MemberRepository memberRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
//        this.em = em;
        this.memberRepository = memberRepository;
    }

    // Spring Security에서 비밀번호를 안전하게 암호화하거나 검증하기 위해 사용되는 객체
    // Configuration 클래스에 Bean 정의를 해주어야 함
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    @Bean
    public MemberService memberService() {
//        return new MemberService(memberRepository());
        return new MemberService(memberRepository, passwordEncoder);
    }

}
