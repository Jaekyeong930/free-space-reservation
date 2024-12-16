package jkkim.space_reservation.service;

import jkkim.space_reservation.entity.Member;
import jkkim.space_reservation.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final MemberRepository memberRepository;

    // 생성자를 통한 UserRepository 의존성 주입
    @Autowired
    public UserService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    // 회원가입
    public Member createMember(Member member) {
        return memberRepository.save(member);
    }

    // 회원 정보 조회 (로그인된 사용자만)
    public Optional<Member> getMemberByMemberId(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // 비밀번호 변경
    public Member updatePassword(Long memberId, String newPassword) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new RuntimeException("Member not found"));
        member.setMemberPassword(newPassword);
        return memberRepository.save(member);
    }

    // 관리자인지 확인
    public boolean isAdmin(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new RuntimeException("Member not found"));
        return member.getMemberRole();
    }
    
    // 스페이스 리스트 조회
    
    // 스페이스 예약
    
    // 예약내역 조회
    
    // 예약내역 취소
    
    // 리뷰 리스트 조회
    
    // 리뷰 작성
}
