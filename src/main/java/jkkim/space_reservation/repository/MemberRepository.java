package jkkim.space_reservation.repository;

import jkkim.space_reservation.domain.Member;

import java.util.List;
import java.util.Optional;

/*
* repository는 데이터베이스에 접근, 도메인 객체를 DB에 저장하고 관리
* */
public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findByMemberId(Long memberId);
    Optional<Member> findByMemberName(String memberName);
    List<Member> findAll();
//    Optional이란?
//    특정 컬럼을 키값으로 하여 검색했을 때 null을 반환하면 Optinal로 감싸서 반환한다
}
