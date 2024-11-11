package jkkim.space_reservation.repository;

import jkkim.space_reservation.domain.Member;

import java.util.*;

/**
 * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */
public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; // 0L은 타입이 long인 정수 리터럴을 의미함. 숫자 뒤에 L을 붙이면 해당 숫자가 long 타입으로 간주됨.

    @Override
    public Member save(Member member) {
        // 시스템이 알아서 member_id를 저장한다
        member.setMemberId(++sequence);
        store.put(member.getMemberId(), member);
        return member;
    }

    @Override
    public Optional<Member> findByMemberId(Long memberId) {
        return Optional.ofNullable(store.get(memberId));
    }
//    Optional로 감싸는 이유 : NullPointerException(NPE)을 방지하고, 값이 없을 가능성이 있는 상황을 명확하게 표현하기 위해

    @Override
    public Optional<Member> findByMemberName(String memberName) {
        return store.values().stream()
                .filter(member -> member.getMemberName().equals(memberName))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    // 테스트 후 데이터를 비우는 메서드
    public void clearStore() {
        store.clear();
    }
}
