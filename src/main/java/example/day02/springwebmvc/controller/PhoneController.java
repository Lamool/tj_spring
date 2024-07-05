package example.day02.springwebmvc.controller;

import example.day02.springwebmvc.model.dao.PhoneDao;
import example.day02.springwebmvc.model.dto.PhoneDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

// 웹/인터넷 이용한 통신 환경구축,(JS랑 통신하기 위해서)
@RestController     // 해당 클래스는 MVC 컨트롤러 역할 주입
public class PhoneController {
//    // [1] 싱글톤 패턴 ((패턴 : 문법 X)) ---> spring에서 자체적으로 객체 관리 기능
//    private static PhoneController phoneCont = new PhoneController();   // ((객체를 미리 만들어 놓는 것))
//    // 다른 곳에서 사용하지 못하도록 생성자 private
//    private PhoneController(){}
//    public static PhoneController getInstance() {
//        return phoneCont;
//    }

    // 1.
    @PostMapping("/phone/create")   // HTTP method : post, url : http:localhost:8080/phone/create
    public boolean postPhone(@RequestBody PhoneDto phoneDto) {  // @RequestBody : 자바스크립트로 들어온 형식을 클래스 형식으로 바꿔주는 애
        System.out.println("phoneDto = " + phoneDto);
        System.out.println("----- postPhone 컨트롤 통신 성공 -----");
        return PhoneDao.getInstance().postPhone(phoneDto);

    }

    // 2.
    @GetMapping("/phone/read")      // HTTP method : get, url : http:localhost:8080/phone/read
    public ArrayList<PhoneDto> getPhone() {
        System.out.println("----- getPhone 컨트롤 통신 성공 -----");
        return PhoneDao.getInstance().getPhone();

    }

}
