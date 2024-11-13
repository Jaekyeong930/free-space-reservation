package jkkim.space_reservation.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // 기본 페이지로 매핑되는 템플릿이 있으면 index 템플릿은 무시됨
    @GetMapping("/")
    public String home() {
        return "home";
    }
}
