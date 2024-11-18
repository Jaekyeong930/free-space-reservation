package jkkim.space_reservation.service;

import jakarta.persistence.EntityManager;
import jkkim.space_reservation.repository.JpaMemberRepository;
import jkkim.space_reservation.repository.MemberRepository;
import jkkim.space_reservation.repository.MemoryMemberRepository;
import jkkim.space_reservation.service.MemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    private final EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new JpaMemberRepository(em);
    }
}
