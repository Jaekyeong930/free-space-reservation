package jkkim.space_reservation.service;

import jkkim.space_reservation.domain.Member;
import jkkim.space_reservation.repository.MemoryMemberRepository;
import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach // 각 테스트를 실행하기 전 수행하는 작업
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }
    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test // 테스트 메서드는 영어가 아니어도 됨! 프로덕트가 build될 때 테스트코드는 포함되지 않음
    void 회원가입() {
        // given (기반이 되는 데이터)
        Member member = new Member();
        member.setMemberName("spring");

        // when (어떤 검증을 하는가)
        Long saveMemberId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveMemberId).get();
        Assertions.assertThat(member.getMemberName()).isEqualTo(findMember.getMemberName());
    }

    @Test
    public void 중복_회원_예외처리() {
        // given
        Member member1 = new Member();
        member1.setMemberName("spring");

        Member member2 = new Member();
        member2.setMemberName("spring");

        // when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        // 객체가 특정 작업을 수행하기에 적절한 상태가 아닐 때 발생하는 런타임 예외
        // 예를 들어, 초기화되지 않은 상태에서 메서드를 호출하려고 하거나, 이미 종료된 리소스에 접근하려고 할 때
        // try-catch문 대신 IllegalStateException을 쓰는 것의 장점?

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");

//        try {
//            memberService.join(member2);
//            fail();
//        } catch (IllegalStateException e) {
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");
//        }
        // then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}