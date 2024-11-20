//package jkkim.space_reservation.repository;
//
//import jakarta.persistence.EntityManager;
//import jkkim.space_reservation.domain.Member;
//
//import java.util.List;
//import java.util.Optional;
//
//public class JpaMemberRepository implements MemberRepository {
//
//    // JPA를 사용하기 위해서는 EntityManager를 주입받아야 함
//    private final EntityManager em;
//
//    public JpaMemberRepository(EntityManager em) {
//        this.em = em;
//    }
//
//    @Override
//    public Member save(Member member) {
//        // JPA의 영속성 컨텍스트(Persistence Context)에 해당
//        // member 엔티티가 관리상태로 추가됨. JPA가 해당 객체를 영속성 상태로 변환하고, DB에 저장될 준비를 한다는 의미.
//        em.persist(member); // 이렇게 하면 JPA가 insert 쿼리 만들어서 넣는대... 와
//        return member;
//    }
//
//    @Override
//    public Optional<Member> findByMemberId(Long memberId) {
//        Member member = em.find(Member.class, memberId);
//        return Optional.ofNullable(member);
//    }
//
//    // JPQL(엔티티 객체를 조회하는 객체지향 쿼리)을 작성한다.
//    // JPQL은 SQL과 유사하지만, DB테이블이 아닌 엔티티 객체를 대상으로 쿼리를 실행하는 것이 특징.
//    @Override
//    public Optional<Member> findByMemberName(String memberName) {
//        // ※주의 : JPQL은 엔티티 필드명을 기반으로 작성하므로 where절은 엔티티 클래스의 변수명을 적용
//        // m은 Member Entity의 별칭이며, JPQL에서는 필수로 있어야 함.
//        // em.createQuery 메서드는 쿼리 실행 준비
//        List<Member> result = em.createQuery("select m from Member m where m.memberName = :memberName", Member.class)
//                .setParameter("memberName", memberName) // JPQL에서 정의한 memberName 파라미터에 실제 값을 바인딩
//                .getResultList(); // 쿼리를 실행한 결과를 List 형태로 반환
//
//        // 리스트에서 첫 번째 요소를 Optional로 반환. 리스트가 비어 있으면 Optional.empty()
//        return result.stream().findAny();
//    }
//
//    @Override
//    public List<Member> findAll() {
//        // m이라는 약칭은 필수
//        // Member Entity를 m이라는 객체로 호출하는 것
//        return em.createQuery("select m from Member m", Member.class)
//                .getResultList();
//    }
//}
