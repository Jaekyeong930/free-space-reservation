package jkkim.space_reservation.repository;

import jkkim.space_reservation.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// 멤버 레포지토리(DB연동)
@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    // ID로 회원 조회
    Optional<Member> findByMemberName(String memberName);

    // admin 권한을 가진 회원 조회
    Optional<Member> findByMemberRole(Boolean memberRole);

    // 멤버 삭제

    // 멤버 정보 변경?


}
