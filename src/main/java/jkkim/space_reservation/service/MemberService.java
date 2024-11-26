package jkkim.space_reservation.service;

import jkkim.space_reservation.controller.MemberForm;
import jkkim.space_reservation.domain.Member;
import jkkim.space_reservation.repository.*;
import org.hibernate.NonUniqueResultException;
import org.hibernate.loader.NonUniqueDiscoveredSqlAliasException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

// 서비스 클래스는 일반적으로 비즈니스 용어들을 채택한다.
@Transactional
public class MemberService {
    private MemberRepository memberRepository;

    //    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
        // memberRepository를 외부에서 받아오도록 하는 것. dependency injection
    }

    /*
     * 회원가입
     */
    public Long join(Member member) {
        // 같은 이름이 있는 중복 회원은 X
//        Optional<Member> result = memberRepository.findByMemberName(member.getMemberName());
//        result.ifPresent(m -> {
//            throw new IllegalStateException(("이미 존재하는 회원입니다"));
//        });

        // 중복회원 검증
//        isDuplicateMember(member);

        memberRepository.save(member);
        return member.getMemberId();
    }

    // 회원가입 아이디 중복검사
    public boolean isDuplicateMember(MemberForm member) {
        try {
            return memberRepository.findByMemberName(member.getMemberName()).isPresent();
        } catch (NonUniqueResultException e) {
            // 중복된 데이터가 존재하는 경우 true 반환
            return true;
        }
    }


    /*
    전체 회원 조회
    */
        public List<Member> findMembers () {
            return memberRepository.findAll();
        }

        /*
         * 특정 회원 조회
         * */
        public Optional<Member> findOne (Long memberId){
            return memberRepository.findByMemberId(memberId);
        }
    }
