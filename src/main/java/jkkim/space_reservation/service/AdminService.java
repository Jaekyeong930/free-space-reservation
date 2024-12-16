package jkkim.space_reservation.service;

import jkkim.space_reservation.entity.Member;
import jkkim.space_reservation.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    private final MemberRepository memberRepository;

    @Autowired
    public AdminService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    // 모든 회원 조회 (관리자만 접근)
    public List<Member> getAllMembers() {
        return memberRepository.findAll();  // UserRepository에서 모든 사용자 목록을 가져옴
    }

    // 특정 사용자 삭제 (관리자만 접근)
    public void deleteMember(Long memberId) {
        memberRepository.deleteById(memberId);  // UserRepository에서 사용자 삭제
    }

    // 전체 예약 리스트 조회


    // 예약 내역 취소 (관리자만 접근)

}
