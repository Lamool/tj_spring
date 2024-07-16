package example.day09.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BoardService {

    @Autowired BoardDao boardDao;
    // 1.글추가
    public boolean boardWrite(BoardDto boardDto){
        return boardDao.boardWrite(boardDto);
    }

    // 2.글출력
    public ArrayList<BoardDto> boardReadAll(){
        return boardDao.boardReadAll();
    }

    // 2-1. 상세보기출력
    public BoardDto boardView(int bno, int bview){
        BoardDto dto = new BoardDto();
        dto.setBno(bno);
        dto.setBview(bview);
        return boardDao.boardView(dto);
    }

    // 3.글수정
    public boolean boardEdit(int bno, String btitle, String bcontent){
        BoardDto dto = new BoardDto();
        dto.setBno(bno);
        dto.setBtitle(btitle);
        dto.setBcontent(bcontent);
        return boardDao.boardEdit(dto);
    }

    // 4.글삭제
    public boolean boardDelete(int bno){
        return boardDao.boardDelete(bno);
    }

    // 5. 비밀번호확인
    public boolean boardCheckPassWord(int bno, String bpassword){
        System.out.println("Checking Password");
        BoardDto dto = new BoardDto();
        dto.setBno(bno);
        dto.setBpassword(bpassword);
        return boardDao.boardCheckPassWord(dto);
    }

}
