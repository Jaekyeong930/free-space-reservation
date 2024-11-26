package jkkim.space_reservation.controller;

public class LoginForm {

    private String memberName;

    private String memberPassword;

    // setter
    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public void setMemberPassword(String memberPassword) { this.memberPassword = memberPassword; }

    // getter

    public String getMemberPassword() { return memberPassword; }

    public String getMemberName() {
        return memberName;
    }

}
