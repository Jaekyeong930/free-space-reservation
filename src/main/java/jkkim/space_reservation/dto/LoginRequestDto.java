package jkkim.space_reservation.dto;

import jakarta.validation.constraints.NotBlank;

// 로그인 요청 DTO
public class LoginRequestDto {

    @NotBlank(message = "ID는 필수입니다.")
    private String loginName;

    @NotBlank(message = "Password는 필수입니다.")
    private String loginPassword;

    // 기본 생성자
    public LoginRequestDto() {
    }

    // Getter와 Setter
    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String username) {
        this.loginName = username;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String password) {
        this.loginPassword = password;
    }
}
