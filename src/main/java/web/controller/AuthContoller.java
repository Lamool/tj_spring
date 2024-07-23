package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web.service.AuthServicce;

@RestController             // @Controller + @ResponseBody
@RequestMapping("/auth")    // 해당 클래스내 메소드들의 공통 HTML URL
public class AuthContoller {

    // 스프링 컨테이너의 빈(객체) 가져오기/주입
    @Autowired AuthServicce authService;

    // 1. 인증 번호 요청/생성
    @GetMapping("/code")
    public boolean authCode(String email) {
        return authService.authCode(email);
    }


    // 2. 입력받은 값과 인증 번호를 인증/비교
    @PostMapping("/check")
    public boolean authCheck(String authCodeInput) {
        return authService.authCheck(authCodeInput);
    }


}
