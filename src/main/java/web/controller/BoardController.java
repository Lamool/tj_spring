package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.model.dto.BoardDto;
import web.model.dto.BoardPageDto;
import web.service.BoardService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    // 1. 전체 카테고리 호출
    @GetMapping("/category")
    // public List<BoardDto> bcFindAll() {
    public List<Map<String, String>> bcFindAll() {
        System.out.println("BoardController.bcFindAll");

        return boardService.bcFindAll();
    }

    // 2. 글 쓰기 처리
    @PostMapping("/write")
    // BODY에 넣어보기 { "bcno" : 1 , "btitle" : "안녕" , "bcontent" : "하하하" }
    public boolean bWrite(BoardDto boardDto) {
        System.out.println("BoardController.bWrite");
        System.out.println("boardDto = " + boardDto);
        return boardService.bWrite(boardDto);
    }

    // 3. 게시물 전체 출력
    @GetMapping("/find/all")
    public BoardPageDto bFindAll(BoardPageDto pageDto) {
        // 매개변수
        // 1. page : 페이징 처리에서 사용할 현재 페이지번호
        // 2. bcno : 현재 선택된 카테고리 번호
        // 3. searchKey : 검색 조회시 사용되는 필드명
        // 4. searchKeyword : 검색 조회시 사용되는 필드의 값
        System.out.println("BoardController.bFindAll");
        return boardService.bFindAll(pageDto);
    }

    // 4. 게시물 개별 조회 처리
    @GetMapping("/bview")
    public BoardDto bView(int bno) {
        System.out.println("BoardController.bView");

        return boardService.bView(bno);
    }

    // 5. 게시물의 댓글 쓰기 처리
    @PostMapping("/reply/write")        // ?? 왜 post - JSON으로 값을 요청하고 응답할 수 있는 게 post랑 put이라서? get이랑 delete는 X. post 저장
    // @ResponseBody - JSON으로 반환하는 애, @RequestBody : JSON으로 요청하는 애
    // JSON이 아닌 일반 폼, 파라미터로 하고 싶다 그러면 @RequestParam
    // day07
    // 왜 @RequestBody를 썼는지
    public boolean bReplyWrite(@RequestBody Map<String, String> map) {
        System.out.println("BoardController.bReplyWrite");  // ?? 왜 map
        System.out.println("map = " + map);
        return boardService.bReplyWrite(map);   // ? 왜 service
    }

    // 6. 댓글 출력 처리
    @GetMapping("/reply/print")
    public List< Map<String, String> > bReplyPrint(int bno) {
        System.out.println("BoardController.bReplyPrint");
        System.out.println("bno = " + bno);

        return boardService.bReplyPrint(bno);
    }





}
