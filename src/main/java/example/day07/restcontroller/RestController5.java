package example.day07.restcontroller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/example")
public class RestController5 {
    // ============= HTTP BODY 매개변수들을 매핑하는 방법 ============ //

    // [1] HTTP POST : 쿼리스트링 가능.
    @PostMapping("/test5")
    public String test5(String key1, int key2) {
        System.out.println("RestController5.test5");
        System.out.println("key1 = " + key1 + ", key2 = " + key2);
        return "test5 HI";
    }

    // [2] HTTP POST
    @PostMapping("/test6")
    //public String test6(String key1, int key2) {  // [1] HTTP BODY 데이터 매핑 안 됨
    // public String test6 (RestDto restDto) {
    public String test6 (@RequestBody RestDto restDto) {
        System.out.println("RestController6.test6");
        //System.out.println("key1 = " + key1 + ", key2 = " + key2);
        // System.out.println("restDto = " + restDto);
        System.out.println("restDto = " + restDto);
        return "test6 HI";
    }

}
