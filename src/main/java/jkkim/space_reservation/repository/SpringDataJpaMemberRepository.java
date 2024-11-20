package jkkim.space_reservation.repository;

import jkkim.space_reservation.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// Spring Data JPA 학습
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long> , MemberRepository {

    // JpaRepository 인터페이스를 받으면 스프링 구현체를 자동으로 만들어준다. 그래서 Sprig Bean을 자동으로 등록한다.
    @Override
    Optional<Member> findByMemberName(String MemberName);
}
