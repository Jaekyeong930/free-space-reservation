package jkkim.space_reservation.domain;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Member {

    // 엔티티 클래스와 DB 스키마는 항상 동기화되어야 하므로, 실제 테이블의 컬럼과 엔티티 클래스의 변수가 일치하도록 유지한다.

    @Id // Id는 PK를 의미
    @GeneratedValue(strategy = GenerationType.IDENTITY) // IDENTITY는 자동으로 값을 넣어주는 컬럼이라는 것을 의미
    @Column(name = "member_id")
    private Long    memberId;

    @Column(name = "member_name")
    private String  memberName;

//    @Column(name = "member_type")
//    private boolean memberType;
//
//    @Column(name = "member_password")
//    private String  memberPassword;
//
//    @Column(name = "member_email")
//    private String  memberEmail;
//
//    @Column(name = "member_created_at")
//    private Date    memberCreatedAt;

    // setter
    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

//    public void setMemberType(boolean memberType) {
//        this.memberType = memberType;
//    }
//
//    public void setMemberPassword(String memberPassword) {
//        this.memberPassword = memberPassword;
//    }
//
//    public void setMemberEmail(String memberEmail) {
//        this.memberEmail = memberEmail;
//    }
//
//    public void setMemberCreatedAt(Date memberCreatedAt) {
//        this.memberCreatedAt = memberCreatedAt;
//    }

    // getter
    public Long getMemberId() {
        return memberId;
    }

    public String getMemberName() {
        return memberName;
    }

//    public boolean isMemberType() {
//        return memberType;
//    }
//
//    public String getMemberPassword() {
//        return memberPassword;
//    }
//
//    public String getMemberEmail() {
//        return memberEmail;
//    }
//
//    public Date getMemberCreatedAt() {
//        return memberCreatedAt;
//    }

}
