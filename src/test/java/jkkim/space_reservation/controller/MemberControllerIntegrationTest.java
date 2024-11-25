package jkkim.space_reservation.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@WebMvcTest(MemberController.class) // Spring MVC 환경에서 MemberController만 테스트. 다른 컴포넌트들은 Mock으로 처리되며, 이 어노테이션은 컨트롤러 계층만 테스트
public class MemberControllerIntegrationTest {

    @Autowired MockMvc mockMvc; // HTTP 요청을 모방하여 POST 요청을 보내고 결과를 검증하는 데 사용
    @Autowired MemberController memberController;

    @Test
    void 회원가입_유효성_테스트() {
        // given (기반이 되는 데이터)
        MemberForm memberForm = new MemberForm();
        memberForm.setMemberName("");
        memberForm.setMemberPassword("");
        memberForm.setMemberEmail("");

        // when (어떤 검증을 하는가)

        // then
    }
}
