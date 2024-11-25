package jkkim.space_reservation.repository;

import jkkim.space_reservation.repository.MemoryMemberRepository;
import jkkim.space_reservation.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
//import org.assertj.core.api.Assertions;
import static org.assertj.core.api.Assertions.*; // Assertions호출 없이 바로 쓸 수 있음
import static org.springframework.test.web.servlet.result.StatusResultMatchersExtensionsKt.isEqualTo;

import java.util.List;
import java.util.Optional;

// 테스트 코드는 public 아니어도 됨
// MemoryMemberRepository의 save메서드가 정상적으로 작동하는지 검증하는 단위 테스트
class MemoryMemberRepositoryTest {
    // 테스트에 사용할 MemoryMemberRepository 인스턴스를 생성하여 repository 변수에 할당
    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 클래스 실행 시, 메서드의 실행 순서는 보장되지 않기 때문에, 테스트 중 생성된 데이터가 다른 메서드의 테스트에 방해가 될 수 있다.
    // 그러므로 테스트가 끝날 때마다 데이터 초기화가 필요하다.
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test // JUnit에서 테스트 메서드임을 명시
    public void save() {
        // Member 객체 생성, 이후 save메서드를 통해 저장될 테스트용 데이터
        Member member = new Member();
        member.setMemberName("spring");

        // MemoryMemberRepository의 save 메서드를 호출하여 meber객체 저장
        // save 메서드는 MemoryMemberRepository의 데이터 저장소에 member객체를 저장해야 함
        repository.save(member);

        // 저장한 member객체를 findByMemberId 메서드를 사용해 다시 조회
        // findByMemberId는 Optional<Member>를 반환하므로, .get()을 사용하여 Optional에서 Member 객체를 꺼냄
        Member result = repository.findByMemberId(member.getMemberId()).get();

        // JUnit의 Assertions.assertEquals 메서드를 사용해 result와 member가 동일한지 검증
//        Assertions.assertEquals(member, result);
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByMemberName() {
        // 테스트용 데이터를 member객체에 저장
        Member member1 = new Member();
        member1.setMemberName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setMemberName("spring2");
        repository.save(member2);

        Member result = repository.findByMemberName("spring1").get();
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setMemberName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setMemberName("spring2");
        repository.save(member2);

        // repository에 있는 모든 레코드 가져오기
        List<Member> result = repository.findAll();

        // 레코드 개수 확인하기
        assertThat(result.size()).isEqualTo(2);
    }
}
