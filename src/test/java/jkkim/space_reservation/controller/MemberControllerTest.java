package jkkim.space_reservation.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
//import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

//@SpringBootTest
//@WebMvcTest(MemberController.class) // Spring MVC 환경에서 MemberController만 테스트. 다른 컴포넌트들은 Mock으로 처리되며, 이 어노테이션은 컨트롤러 계층만 테스트
public class MemberControllerTest {

    private final MemberController memberController = new MemberController(null);

    // @DisplayName은 테스트 메서드의 이름을 더 직관적이고 이해하기 쉬운 설명으로 바꾸는 데 유용함
    /*
    * 아이디 테스트
    * */
    @Test
    @DisplayName("아이디가 영문 소문자+숫자 6~20자리 형식이면 true 반환")
    void testValidName_Success() {
        // given
        MemberForm validForm = new MemberForm();
        validForm.setMemberName("testid"); // 유효한 아이디

        // when
        boolean result = memberController.isValidName(validForm);

        // then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("아이디가 숫자만 있으면 false 반환")
    void testValidName_NumericOnly() {
        // given
        MemberForm numericForm = new MemberForm();
        numericForm.setMemberName("123456");

        // when
        boolean result = memberController.isValidName(numericForm);

        // then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("아이디가 숫자로 시작하면 false 반환")
    void testValidName_StartsWithNumber() {
        // given
        MemberForm startsWithNumberForm = new MemberForm();
        startsWithNumberForm.setMemberName("1valid");

        // when
        boolean result = memberController.isValidName(startsWithNumberForm);

        // then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("아이디에 대문자가 포함되어 있으면 false 반환")
    void testValidName_WithUpperCase() {
        // given
        MemberForm WithUpperCaseForm = new MemberForm();
        WithUpperCaseForm.setMemberName("Valid123");

        // when
        boolean result = memberController.isValidName(WithUpperCaseForm);

        // then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("아이디가 6자리 미만일 경우 false 반환")
    void testValidName_LessThan6Digits() {
        // given
        MemberForm WithUpperCaseForm = new MemberForm();
        WithUpperCaseForm.setMemberName("test1");

        // when
        boolean result = memberController.isValidName(WithUpperCaseForm);

        // then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("아이디가 20자리 초과일 경우 false 반환")
    void testValidName_MoreThan20Digits() {
        // given
        MemberForm WithUpperCaseForm = new MemberForm();
        WithUpperCaseForm.setMemberName("id0123456789012345678");

        // when
        boolean result = memberController.isValidName(WithUpperCaseForm);

        // then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("아이디가 null이면 false 반환")
    void testValidName_NullId() {
        // given
        MemberForm nullForm = new MemberForm();
        nullForm.setMemberName(null);

        // when
        boolean result = memberController.isValidName(nullForm);

        // then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("아이디가 비어 있으면 false 반환")
    void testValidName_EmptyId() {
        // given
        MemberForm emptyForm = new MemberForm();
        emptyForm.setMemberName("");

        // when
        boolean result = memberController.isValidName(emptyForm);

        // then
        assertThat(result).isFalse();
    }

    /*
    * 비밀번호 테스트
    * */
    @Test
    @DisplayName("영문 대소문자+숫자 필수, 특수문자 허용, 8~20자리 형식이면 true 반환")
    void testValidPassword_Success() {
        // given
        MemberForm validForm = new MemberForm();
        validForm.setMemberPassword("test012!"); // 유효한 비밀번호

        // when
        boolean result = memberController.isValidPassword(validForm);

        // then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("비밀번호에 숫자가 없으면 false 반환")
    void testValidPassword_WithoutNumeric() {
        // given
        MemberForm WithoutNumericForm = new MemberForm();
        WithoutNumericForm.setMemberPassword("password");

        // when
        boolean result = memberController.isValidPassword(WithoutNumericForm);

        // then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("비밀번호에 영문자가 없으면 false 반환")
    void testValidPassword_WithoutChar() {
        // given
        MemberForm WithoutCharForm = new MemberForm();
        WithoutCharForm.setMemberPassword("12345678");

        // when
        boolean result = memberController.isValidPassword(WithoutCharForm);

        // then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("비밀번호가 8자리 미만일 경우 false 반환")
    void testValidPassword_LessThan8Digits() {
        // given
        MemberForm LessThan8DigitsForm = new MemberForm();
        LessThan8DigitsForm.setMemberPassword("test123");

        // when
        boolean result = memberController.isValidPassword(LessThan8DigitsForm);

        // then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("비밀번호가 20자리 초과일 경우 false 반환")
    void testValidPassword_MoreThan20Digits() {
        // given
        MemberForm LessThan8DigitsForm = new MemberForm();
        LessThan8DigitsForm.setMemberPassword("password0123456789012");

        // when
        boolean result = memberController.isValidPassword(LessThan8DigitsForm);

        // then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("비밀번호가 null이면 false 반환")
    void testValidName_NullPw() {
        // given
        MemberForm nullForm = new MemberForm();
        nullForm.setMemberPassword(null);

        // when
        boolean result = memberController.isValidPassword(nullForm);

        // then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("비밀번호가 비어 있으면 false 반환")
    void testValidName_EmptyPw() {
        // given
        MemberForm emptyForm = new MemberForm();
        emptyForm.setMemberPassword("");

        // when
        boolean result = memberController.isValidPassword(emptyForm);

        // then
        assertThat(result).isFalse();
    }

    /*
    * 이메일 테스트
    * */
    @Test
    @DisplayName("유효한 이메일이면 true 반환")
    void testValidEmail_Success1() {
        // given
        MemberForm validForm = new MemberForm();
        validForm.setMemberEmail("test@test.co.kr"); // 유효한 비밀번호

        // when
        boolean result = memberController.isValidEmail(validForm);

        // then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("유효한 이메일이면 true 반환")
    void testValidEmail_Success2() {
        // given
        MemberForm validForm = new MemberForm();
        validForm.setMemberEmail("test@test.com"); // 유효한 비밀번호

        // when
        boolean result = memberController.isValidEmail(validForm);

        // then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("이메일 아이디가 없는 경우 false 반환")
    void testValidEmail_WithoutId() {
        // given
        MemberForm WWithoutIdForm = new MemberForm();
        WWithoutIdForm.setMemberEmail("@test.com");

        // when
        boolean result = memberController.isValidEmail(WWithoutIdForm);

        // then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("도메인의 . 앞 주소가 없는 경우 false 반환")
    void testValidEmail_WithInvalidDomain1() {
        // given
        MemberForm WithInvalidDomain1Form = new MemberForm();
        WithInvalidDomain1Form.setMemberEmail("test@.com");

        // when
        boolean result = memberController.isValidEmail(WithInvalidDomain1Form);

        // then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("이메일의 도메인의 . 뒤 주소가 없는 경우 false 반환")
    void testValidEmail_WithInvalidDomain2() {
        // given
        MemberForm WithoutDomainForm = new MemberForm();
        WithoutDomainForm.setMemberEmail("test@test.");

        // when
        boolean result = memberController.isValidEmail(WithoutDomainForm);

        // then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("이메일의 도메인이 잘못된 경우 false 반환")
    void testValidEmail_WithInvalidDomain3() {
        // given
        MemberForm WithoutDomainForm = new MemberForm();
        WithoutDomainForm.setMemberEmail("test@.");

        // when
        boolean result = memberController.isValidEmail(WithoutDomainForm);

        // then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("이메일의 도메인이 잘못된 경우 false 반환")
    void testValidEmail_WithInvalidDomain4() {
        // given
        MemberForm WithoutDomainForm = new MemberForm();
        WithoutDomainForm.setMemberEmail("test@");

        // when
        boolean result = memberController.isValidEmail(WithoutDomainForm);

        // then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("이메일이 xxxx@xxxx.com 형식이 아닐 경우 false 반환")
    void testValidEmail_WithoutAt() {
        // given
        MemberForm WithoutAtForm = new MemberForm();
        WithoutAtForm.setMemberEmail("email.com");

        // when
        boolean result = memberController.isValidEmail(WithoutAtForm);

        // then
        assertThat(result).isFalse();
    }


    @Test
    @DisplayName("이메일이 null이면 false 반환")
    void testValidEmail_NullEmail() {
        // given
        MemberForm nullForm = new MemberForm();
        nullForm.setMemberEmail(null);

        // when
        boolean result = memberController.isValidEmail(nullForm);

        // then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("이메일이 비어 있으면 false 반환")
    void testValidEmail_EmptyEmail() {
        // given
        MemberForm emptyForm = new MemberForm();
        emptyForm.setMemberEmail("");

        // when
        boolean result = memberController.isValidEmail(emptyForm);

        // then
        assertThat(result).isFalse();
    }
}
