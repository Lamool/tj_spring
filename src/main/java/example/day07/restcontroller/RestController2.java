package example.day07.restcontroller;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.coyote.Request;
import org.apache.tomcat.util.net.jsse.JSSEUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
public class RestController2 {
    //[1] HTTP GET
    @RequestMapping(value = "/exampl/rest2", method = RequestMethod.GET)
    @ResponseBody   // 응답할 데이터의 자바 타입을 HTTP타입으로 자동 변경 설정 : (jav)String -> (HTTP)text/
    public String getRest2(HttpServletRequest request) {
        System.out.println("RestController2.getRest2");
        // 1. 요청
        String key =  request.getParameter("key");
        System.out.println("key = ");
        return "";
    }

    // [2] HTTP POST
    @RequestMapping(value = "/example/rest2", method = RequestMethod.POST)
    @ResponseBody   // 응답할 데이터의 자바타입을 HTTP타입으로 자동 타입 설정 : (java) [] -> (HTTP) application/json;
    public ArrayList<RestDto> getPost2 (HttpServletRequest request) {
        System.out.println("RestController2.getPost2");
        String key =  request.getParameter("key");
        System.out.println("key = "  + key);
        // 데이터 구성
        // (1) 배열[] 타입
        // String[] strArray = new String[2];
        // strArray[0] = "[POST]";
        // strArray[1] = "ClientHi";
        // (2) 리스트 타입
//        ArrayList<String> strArray = new ArrayList<>();
//        strArray.add("[POST]");
//        strArray.add("ClientHi");
        // (3) 객체(DTO) 타입
        //RestDto strArray = new RestDto("[POST]", "ClientHi");
        // (4) 리스트 안에 DTO 타입
        ArrayList<RestDto> strArray = new ArrayList<>();
        strArray.add(new RestDto("[POST1]", "ClientHi1"));
        strArray.add(new RestDto("[POST2]", "ClientHi2"));

        return strArray;        // JSON : [ "[POST]", "ClientHi" ]
    }

    // [3] HTTP PUT
    @RequestMapping(value = "/example/rest2", method = RequestMethod.PUT)
    public int putRest2(HttpServletRequest request) {
        System.out.println("RestController2.putRest2");
        String key = request.getParameter("key");
        System.out.println("key = " + key);
        return 10 + 10;     // application/json     // 사이트에서 Content-Type 확인하기
    }


    // [4] HTTP DELETE
    @RequestMapping(value = "/example/rest2", method = RequestMethod.DELETE)
    public boolean deleteRest2(HttpServletRequest request) {
        System.out.println("RestController2.deleteRest2");
        String key = request.getParameter("key");
        System.out.println("key = " + key);
        return true;     // application/json     // 사이트에서 Content-Type 확인하기
    }


}
