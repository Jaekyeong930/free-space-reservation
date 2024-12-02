package jkkim.space_reservation.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Member {

    // 엔티티 클래스와 DB 스키마는 항상 동기화되어야 하므로, 실제 테이블의 컬럼과 엔티티 클래스의 변수가 일치하도록 유지한다.

    @Id // Id는 PK를 의미
    @GeneratedValue(strategy = GenerationType.IDENTITY) // IDENTITY는 자동으로 값을 넣어주는 컬럼이라는 것을 의미
    @Column(name = "member_id", nullable = false, updatable = false)
    private Long    memberId;

    @Column(name = "member_name", nullable = false, updatable = false)
    private String  memberName;

    @Column(name = "member_role", nullable = false, updatable = false)
    private boolean memberRole = false;

    @Column(name = "member_password", nullable = false)
    private String  memberPassword;

    @Column(name = "member_email", nullable = false)
    private String  memberEmail;

    // 가입날짜는 자동 입력되도록 함
    @Column(name = "member_created_at", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime memberCreatedAt = LocalDateTime.now();

    // setter
    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

//    회원의 타입(운영자/이용자)은 유저가 지정할 수 없도록 한다. 기본값은 0(이용자)
//    public void setMemberType(boolean memberType) {
//        this.memberType = memberType;
//    }
    public void setMemberRole(Boolean memberRole) {
    this.memberRole = memberRole;
}

    public void setMemberPassword(String memberPassword) {
        this.memberPassword = memberPassword;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }

//    회원가입한 날짜는 유저가 임의로 지정할 수 없게 해야하므로 setter를 삭제한다.
//    public void setMemberCreatedAt(LocalDateTime memberCreatedAt) {
//        this.memberCreatedAt = memberCreatedAt;
//    }

    // getter
    public Long getMemberId() {
        return memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public boolean getMemberRole() { return memberRole; }

    public String getMemberPassword() {
        return memberPassword;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public LocalDateTime getMemberCreatedAt() { return memberCreatedAt; }


}
