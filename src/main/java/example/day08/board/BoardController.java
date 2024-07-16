package example.day08.board;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class BoardController {

    // 1.글추가
    @PostMapping("/day08/board")
    public boolean boardWrite(String btitle, String bcontent, String bpassword){
        BoardDto dto = new BoardDto();
        dto.setBtitle(btitle);
        dto.setBcontent(bcontent);
        dto.setBpassword(bpassword);
        return BoardDao.getInstance().boardWrite(dto);
    }

    // 2.글출력
    @GetMapping("/day08/board")
    public ArrayList<BoardDto> boardReadAll(){
        return BoardDao.getInstance().boardReadAll();
    }

    // 2-1. 상세보기출력
    @GetMapping("/day08/board/view")
    public BoardDto boardView(int bno, int bview){
        BoardDto dto = new BoardDto();
        dto.setBno(bno);
        dto.setBview(bview);
        return BoardDao.getInstance().boardView(dto);
    }

    // 3.글수정
    @PutMapping("/day08/board")
    public boolean boardEdit(int bno, String btitle, String bcontent){
        BoardDto dto = new BoardDto();
        dto.setBno(bno);
        dto.setBtitle(btitle);
        dto.setBcontent(bcontent);
        return BoardDao.getInstance().boardEdit(dto);
    }

    // 4.글삭제
    @DeleteMapping("/day08/board")
    public boolean boardDelete(int bno){
        return BoardDao.getInstance().boardDelete(bno);
    }

    // 5. 비밀번호확인
    @GetMapping("/day08/board/pass")
    public boolean boardCheckPassWord(int bno, String bpassword){
        System.out.println("Checking Password");
        BoardDto dto = new BoardDto();
        dto.setBno(bno);
        dto.setBpassword(bpassword);
        return BoardDao.getInstance().boardCheckPassWord(dto);
    }

}
