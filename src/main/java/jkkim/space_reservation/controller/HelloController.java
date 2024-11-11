package jkkim.space_reservation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller // 컨트롤러 어노테이션 명시가 필요
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) { // model(data:hello)
    model.addAttribute("data", "스프링");
    return "hello"; // templates의 html 파일을 찾아서 반환함
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody // http의 body에 문자 내용을 직접 반환한다.
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }
    // `view~resolver` 대신에 `HttpMessageConverter`가 동작함
    // 기본 문자처리 : `StringHttpMessageConverter`
    // 기본 객체처리 : `MappingJackson2HttpMessageConverter`

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }
    }
}
