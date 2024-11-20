package jkkim.space_reservation.repository;

import jkkim.space_reservation.domain.Member;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// Spring Data JPA 학습
@Primary // 여러 개의 동일한 타입의 빈이 있을 때, 우선적으로 주입될 빈을 지정함
@Repository // 현재 memberRepository와 springDataJpaMemberRepository라는 두개의 빈이 지정된 상태
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long> , MemberRepository {

    // JpaRepository 인터페이스를 받으면 스프링 구현체를 자동으로 만들어준다. 그래서 Sprig Bean을 자동으로 등록한다.
    @Override
    Optional<Member> findByMemberName(String MemberName);

    // Spring Data JPA는 메서드 이름을 분석하여, 필요한 쿼리를 자동으로 생성한다.
    // 즉, "findBy" 뒤에 엔티티의 필드 이름을 붙이면 해당 필드를 조건으로 조회하는 메서드가 자동으로 동작한다.
}
