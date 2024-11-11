package jkkim.space_reservation.domain;

import java.util.Date;

public class Member {

    private Long    memberId;
    private boolean memberType;
    private String  memberName;
    private String  memberPassword;
    private String  memberEmail;
    private Date    memberCreatedAt;

    // setter
    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public void setMemberType(boolean memberType) {
        this.memberType = memberType;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public void setMemberPassword(String memberPassword) {
        this.memberPassword = memberPassword;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }

    public void setMemberCreatedAt(Date memberCreatedAt) {
        this.memberCreatedAt = memberCreatedAt;
    }

    // getter
    public Long getMemberId() {
        return memberId;
    }

    public boolean isMemberType() {
        return memberType;
    }

    public String getMemberName() {
        return memberName;
    }

    public String getMemberPassword() {
        return memberPassword;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public Date getMemberCreatedAt() {
        return memberCreatedAt;
    }

}
