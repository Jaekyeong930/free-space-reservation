package jkkim.space_reservation.service;

import jkkim.space_reservation.controller.JwtResponse;
import jkkim.space_reservation.controller.LoginForm;
import jkkim.space_reservation.domain.Member;
import jkkim.space_reservation.repository.MemberRepository;
import jkkim.space_reservation.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional // DB 작업에 대한 트랜잭션 처리 추가
public class AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(MemberRepository memberRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

//    public JwtResponse login(LoginForm loginForm) {
//        // memberName을 기준으로 DB에서 사용자 정보 조회
//        Optional<Member> optionalMember = memberRepository.findByMemberName(loginForm.getMemberName());
//
//        if (optionalMember.isEmpty()) {
//            throw new IllegalArgumentException("사용자가 존재하지 않습니다.");
//        }
//
//        Member member = optionalMember.get();
//
//        // 비밀번호 확인
//        if (!passwordEncoder.matches(loginForm.getMemberPassword(), member.getMemberPassword())) {
//            throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
//        }
//
//        // JWT 생성 및 반환
//        String token = jwtUtil.generateAccessToken(member.getMemberName(), member.getMemberRole());
//        return new JwtResponse(token);
//    }

    public Member authenticate(LoginForm loginForm) {
        Member member = memberRepository.findByMemberName(loginForm.getMemberName())
                .orElseThrow(() -> new IllegalArgumentException("Invalid ID or password"));

        if (!passwordEncoder.matches(loginForm.getMemberPassword(), member.getMemberPassword())) {
            throw new IllegalArgumentException("Invalid ID or password");
        }

        return member;
    }

    // JWT 생성 로직을 서비스에서 담당
    public String generateJwtToken(Member member) {
        return jwtUtil.generateAccessToken(member.getMemberName(), member.getMemberRole());
    }
}