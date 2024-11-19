package jkkim.space_reservation.repository;

import jkkim.space_reservation.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// Spring Data JPA 학습
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long> , MemberRepository {

    @Override
    Optional<Member> findByMemberName(String MemberName);
}
