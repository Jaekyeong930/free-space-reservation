package jkkim.space_reservation.controller;

public class MemberForm {

    private String memberName;

    private String memberPassword;

    private String memberEmail;

    // setter
    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public void setMemberPassword(String memberPassword) { this.memberPassword = memberPassword; }

    public void setMemberEmail(String memberEmail) { this.memberEmail = memberEmail; }

    // getter
    public String getMemberEmail() { return memberEmail; }

    public String getMemberPassword() { return memberPassword; }

    public String getMemberName() {
        return memberName;
    }

}
