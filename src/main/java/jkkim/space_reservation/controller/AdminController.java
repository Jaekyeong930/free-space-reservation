package jkkim.space_reservation.controller;

import jkkim.space_reservation.entity.Member;
import jkkim.space_reservation.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// 관리자 전용 API
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    // 모든 회원 조회 (관리자만 접근)
    @GetMapping("/members-list")
    public List<Member> getAllMembers() {
        return adminService.getAllMembers();
    }

    // 특정 사용자 삭제
    @DeleteMapping("/members/{id}")
    public void deleteMember(@PathVariable Long id) {
        adminService.deleteMember(id);
    }
}
