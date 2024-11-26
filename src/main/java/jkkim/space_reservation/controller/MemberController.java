package jkkim.space_reservation.controller;

import jkkim.space_reservation.domain.Member;
import jkkim.space_reservation.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    /*
    * 회원 가입
    * */
    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form, Model model) {
        Member member = new Member();

        // 아이디 유효성 검사
        if (!isValidName(form)) {
            model.addAttribute("errorMessage", "아이디는 영문 소문자와 숫자만 포함하며 6~20자리여야 합니다.");
            return "members/createMemberForm";
        }

        // 아이디 중복 검사 (반환값이 true일 경우 알림을 표시)
        if (memberService.isDuplicateMember(form)) {
            model.addAttribute("duplicateIdMessage", "중복된 아이디입니다.");
            return "members/createMemberForm";
        }

        // 비밀번호 유효성 검사
        if (!isValidPassword(form)) {
            model.addAttribute("errorMessage", "비밀번호는 영문 대소문자와 숫자만 포함하며, 6~20자리여야 합니다. (특수문자 허용)");
            return "members/createMemberForm";
        }

        // 이메일 유효성 검사 (xxxxx@xxx.xxx 형식)
        if (!isValidEmail(form)) {
            model.addAttribute("errorMessage", "유효하지 않은 이메일 형식입니다.");
            return "members/createMemberForm";
        }

        // 모두 유효한 값일 경우, member 객체에 값 세팅
        member.setMemberName(form.getMemberName());
        member.setMemberPassword(form.getMemberPassword());
        member.setMemberEmail(form.getMemberEmail());

        memberService.join(member);

        return "redirect:/";
    }

    // 회원가입 아이디 유효성 검사
    boolean isValidName(MemberForm form) {
        String id = form.getMemberName();
        // id 입력값이 null인 경우, false 반환
        if (id == null || id.isEmpty()) {
            return false;
        }
        // 정규표현식으로부터 포맷을 생성 (영문 소문자+숫자 조합, 6~20자리, 영문으로 시작, 숫자만으로 허용불가)
        final String ID_FORMAT = "^[a-z][a-z0-9]{5,19}$";
        // 정규표현식으로부터 포맷을 생성
        Pattern pattern = Pattern.compile(ID_FORMAT);
        // id가 포맷과 일치할 경우 true 반환, 불일치할 경우 false 반환
        Matcher matcher = pattern.matcher(id);

        return matcher.matches();
    }

    // 회원가입 비밀번호 유효성 검사
    boolean isValidPassword(MemberForm form) {
        String password = form.getMemberPassword();
        // id 입력값이 null인 경우, false 반환
        if (password == null || password.isEmpty()) {
            return false;
        }
        // 정규표현식으로부터 포맷을 생성 (영문 대소문자+숫자 필수, 특수문자 허용, 8~20자리)
        final String PW_FORMAT = "^(?=.*[A-Za-z])(?=.*\\d).{8,20}$";
        // 정규표현식으로부터 포맷을 생성
        Pattern pattern = Pattern.compile(PW_FORMAT);
        // password가 포맷과 일치할 경우 true 반환, 불일치할 경우 false 반환
        Matcher matcher = pattern.matcher(password);

        return matcher.matches();
    }

    // 회원가입 이메일(memberEmail) 유효성 검사 - 자바 정규식을 사용한다.
    boolean isValidEmail(MemberForm form) {
        String email = form.getMemberEmail();
        // email 입력값이 null일 경우 false 반환
        if (email == null || email.isEmpty()) {
            return false;
        }

        // email 포맷 정규식 (xxxx@xxxx.xxx 형식)
        final String EMAIL_FORMAT = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        // 정규표현식으로부터 포맷을 생성
        Pattern pattern = Pattern.compile(EMAIL_FORMAT);
        // email이 포맷과 일치할 경우 true 반환, 불일치할 경우 false 반환
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    /*
    * 회원 조회
    * */
    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
