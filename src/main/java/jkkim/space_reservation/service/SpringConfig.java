package jkkim.space_reservation.service;

import jkkim.space_reservation.repository.MemberRepository;
import jkkim.space_reservation.repository.MemoryMemberRepository;
import jkkim.space_reservation.service.MemberService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
