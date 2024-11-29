package jkkim.space_reservation.service;

import jkkim.space_reservation.controller.LoginForm;
import jkkim.space_reservation.controller.MemberForm;
import jkkim.space_reservation.domain.Member;
import jkkim.space_reservation.repository.*;
import org.hibernate.NonUniqueResultException;
import org.hibernate.loader.NonUniqueDiscoveredSqlAliasException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

// 서비스 클래스는 일반적으로 비즈니스 용어들을 채택한다.
@Transactional
public class MemberService {
    private MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        // memberRepository를 외부에서 받아오도록 하는 것. dependency injection
        this.passwordEncoder = passwordEncoder;
    }

    /*
     * 회원가입
     */
    public Long join(MemberForm memberForm) {
        // 같은 이름이 있는 중복 회원은 X
//        Optional<Member> result = memberRepository.findByMemberName(member.getMemberName());
//        result.ifPresent(m -> {
//            throw new IllegalStateException(("이미 존재하는 회원입니다"));
//        });

        // 중복회원 검증
//        isDuplicateMember(member);

        Member member = new Member();
        // member 객체에 값 세팅
        member.setMemberName(memberForm.getMemberName());
        member.setMemberPassword(passwordEncoder.encode(memberForm.getMemberPassword()));
        member.setMemberEmail(memberForm.getMemberEmail());

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

    // 로그인 정보 확인. 아이디가 존재하는지 확인하고, 존재한다면 비밀번호 일치 여부를 확인한다.
    // 회원 정보가 존재하면 true를 반환한다.
    public boolean authenticate(LoginForm loginData) {
        return memberRepository.findByMemberName(loginData.getMemberName())
                .filter(member -> passwordEncoder.matches(loginData.getMemberPassword(), member.getMemberPassword()))  // 암호화된 비밀번호 비교
                .isPresent();
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
