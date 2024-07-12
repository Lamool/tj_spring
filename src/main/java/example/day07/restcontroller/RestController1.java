package example.day07.restcontroller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

//
    //
    // SPRING WEB 환경 = MVC2 3티어 제공하여 controller 자동으로 서블릿 등록
@Controller     // 해당 클래스가 SPRING MVC에서 controller 역할의 클래스임을 등록, ((어디에 등록하는 거냐면)) 스프링 컨테이너(전역 저장소) 빈(객체) 등록
    // 제어 역전 (IOC) : 객체 관리를 개발자가 아닌 스프링이 해준다. (왜?? 여러 개발자가 공통으로 사용할 객체는 1명이 관리하면 좋은데 그 1명의 관리를 스프링이 대신 해주겠다)
public class RestController1 {
    // @RequestMapping(value = "해당메소드매핑할HTTP주소", method = RequestMethod(HTTP메소드)
        // value :
        // method : RequestMethod.HTTP메소드명 : GET, POST, PUT, DELETE

    // [1] HTTP GET     // http://localhost:8080/example/rest1              // http://localhost:8080/example/rest1?key=serverHi
    @RequestMapping(value = "/example/rest1", method = RequestMethod.GET)
    public void getRest1(HttpServletRequest request, HttpServletResponse response) throws IOException {  // 매개변수명은 아무거나 가능
        System.out.println("RestController1.getRest1");
        // 데이터 요청
        String key = request.getParameter("key");
        System.out.println("key = " + key);
        // 데이터 응답
        response.getWriter().print("[GET]ClientHi");
    }

    // [2] HTTP POST    // http://localhost:8080/example/rest1
    @RequestMapping(value = "/example/rest1", method = RequestMethod.POST)  // 원래는 똑같은 주소로 만들면 안 되는데 method가 다르기 때문에 가능
    public void postRest1(HttpServletRequest request, HttpServletResponse response) throws IOException {   // 일반 자바 메소드 위에 @RequestMapping 써주지 않으면 일반 메소드
        System.out.println("RestController1.postRest1");
        // 데이터 요청
        String key = request.getParameter("key");
        System.out.println("key = " + key);
        // 데이터 응답
        response.getWriter().print("[POST]ClientHi");
    }

    // [3] HTTP PUT     // http://localhost:8080/example/rest1
    @RequestMapping(value = "/example/rest1", method = RequestMethod.PUT)
    public void putRest1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("RestController1.putRest1");
        // 데이터 요청
        String key = request.getParameter("key");
        System.out.println("key = " + key);
        // 데이터 응답
        response.getWriter().print("[PUT]ClientHi");
    }

    // [4] HTTP DELETE
    @RequestMapping(value = "/example/rest1", method = RequestMethod.DELETE)
    public void deleteRest1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("RestController1.deleteRest1");
        // 데이터 요청
        String key = request.getParameter("key");
        System.out.println("key = " + key);
        // 데이터 응답
        response.getWriter().print("[DELETE]ClientHi");
    }

}
