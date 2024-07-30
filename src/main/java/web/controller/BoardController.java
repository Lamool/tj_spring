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
        System.out.println("BoardController.bFindAll");
        return boardService.bFindAll(pageDto);
    }

    // 4. 게시물 개별 조회 처리
    @GetMapping("/bview")
    public BoardDto bView(int bno) {
        System.out.println("BoardController.bView");

        return boardService.bView(bno);
    }






}
