package jkkim.space_reservation.controller;

import jkkim.space_reservation.entity.Member;
import jkkim.space_reservation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 회원가입
    @PostMapping
    public Member createMember(@RequestBody Member member) {
        return userService.createMember(member);
    }

    // 회원 정보 조회 (마이페이지)
    @GetMapping("/{memberId}")
    public Member getMemberById(@PathVariable Long memberId) {
        return userService.getMemberByMemberId(memberId).orElseThrow(() -> new RuntimeException("Member not found"));
    }

    // 비밀번호 변경
    @PutMapping("/{memberId}/password")
    public Member updatePassword(@PathVariable Long memberId, @RequestParam String newPassword) {
        return userService.updatePassword(memberId, newPassword);
    }

    // 관리자인지 확인
    @GetMapping("/{memberId}/admin")
    public boolean isAdmin(@PathVariable Long memberId) {
        return userService.isAdmin(memberId);
    }
}
