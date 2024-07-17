package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web.model.dto.MemberDto;
import web.service.MemberService;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired MemberService memberService;

    // 1. 회원가입
    // POST http://localhost:8080/member/signup?id=qwe&pw=asd&name=강호동&email=qwe@qwe&phone=123-123-123
    @PostMapping("/signup")
    //public boolean mSignup(String id, String pw, String name, String email, String phone) {
    public boolean mSignup(MemberDto memberDto) {
        System.out.println("MemberController.mSignup");
        System.out.println("memberDto = " + memberDto);
        return memberService.mSignup(memberDto);
    }

}
