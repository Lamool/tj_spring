package example.day09.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "/day09/board")
public class BoardController {

    @Autowired BoardService boardService;
    // 1.글추가
    @PostMapping
    public boolean boardWrite( @RequestBody BoardDto boardDto ){ // HTML Body에서 보내는 JSON Stringify 데이터를 특정 형태로 받기
        // JSON.stringify {"key1" : "value1", "key2" : "value2"}
        // 예시) { "btitle" : "title", "bcontent" : "content", "bpassword" : "passwd" }
        return boardService.boardWrite(boardDto);
    }

    // 2.글출력
    @GetMapping
    public ArrayList<BoardDto> boardReadAll(){
        return boardService.boardReadAll();
    }

    // 2-1. 상세보기출력
    @GetMapping("/view")
    public BoardDto boardView(int bno, int bview){
        return boardService.boardView(bno, bview);
    }

    // 3.글수정
    @PutMapping
    public boolean boardEdit(int bno, String btitle, String bcontent){
        return boardService.boardEdit(bno, btitle, bcontent);
    }

    // 4.글삭제
    @DeleteMapping
    public boolean boardDelete(int bno){
        return boardService.boardDelete(bno);
    }

    // 5. 비밀번호확인
    @GetMapping("/pass")
    public boolean boardCheckPassWord(int bno, String bpassword){
        return boardService.boardCheckPassWord(bno, bpassword);
    }

}