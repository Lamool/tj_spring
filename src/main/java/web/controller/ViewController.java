package web.controller;

// == AJAX 통신용이 아닌 템플릿 반환하는 컨트롤러 == //
// ((templates 에 있는 동적 파일 애들은 매핑을 해줘야 함))

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// @RestController     // @Controller + @ResponseBody(@ResponseBody는 응답을 JSON 객체로)
@Controller         // JSON 객체가 아닌 템플릿 파일 반환하므로 @ResponseBody 없이 사용
public class ViewController {
    // ============= [1] 레이아웃 ============= //
    @GetMapping("/")    // http://localhost:8080    // 페이지 요청은 HTTP의 GET 방식을 주로 사용된다
    public String index() {
        return "/index.html";   // templates 폴더내 반환할 경로와 파일명
    }

    // ============= [2] 회원 관련 ============= //
    @GetMapping("/member/signup")
    public String mSignup() {
        return "/member/signup.html";       // ((파일의 경로))
    }

    @GetMapping("/member/login")
    public String mLogin() {
        return "/member/login.html";
    }

    // ((templates에 있는 파일들은 순수한 html이 아니기 때문에 렌더링이 필요함. Controller 매핑해줘야 됨. 이거 안 해주고 그냥 쓰면 안 됨.))
    @GetMapping("/member/mypage")
    public String mMyPage() {
        return "/member/myinfo.html";
    }

    @GetMapping("/member/update")
    public String mUpdate() {
        return "/member/update.html";
    }

    @GetMapping("/member/leave")
    public String mLeave() {
        return "/member/leave.html";
    }


    // ============= [3] 게시판 관련 ============= //
    // 전체 게시물 페이지
    @GetMapping("/board")
    public String board() {
        return "/board/board.html";
    }

    // 게시물 쓰기 페이지
    @GetMapping("/board/write")
    public String bWrite() {
        return "/board/write.html";
    }

    // 게시물 수정 페이지
    @GetMapping("/board/update")
    public String bUpdate() {
        return "/board/update.html";
    }

    // 게시물 상세 페이지
    @GetMapping("/board/view")
    public String bView() {
        return "/board/view.html";
    }



    // ============= [4] api 관련 ============= //
    @GetMapping("/api")
    public String api() {
        return "/api/datago.html";
    }

}
