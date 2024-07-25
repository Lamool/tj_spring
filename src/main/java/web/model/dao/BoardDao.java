package web.model.dao;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import web.model.dto.BoardDto;
import web.service.BoardService;

import java.security.cert.Extension;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class BoardDao extends Dao {

    // 1. 전체 카테고리 호출
    public List< Map<String,String> > bcFindAll( ) {
        // public List< BoardDto > bcFindAll( ) {
        System.out.println("BoardDao.bcFindAll");
        // - list 컬렉션 선언 , map컬렉션(객체) 을 여러개 저장하기 위해 list 선언
        List< Map<String, String> >  list = new ArrayList<>();
        try {
            String sql = "select * from bcategory;";                System.out.println(sql);
            PreparedStatement ps = conn.prepareStatement(sql);      System.out.println(ps);
            ResultSet rs = ps.executeQuery();                       System.out.println(rs);
            while( rs.next() ){
                // ==================== 1. Map 활용 ============= //
                // - map 컬렉션/객체 선언
                Map<String,String> map = new HashMap<>();
                // - map 컬렉션/객체 엔트리 2개 추가 , 카테고리번호 , 카테고리이름
                map.put( "bcno" ,  String.valueOf( rs.getInt( "bcno" )  ) );
                map.put( "bcname" ,  String.valueOf( rs.getString( "bcname" )  ) );
                // - map 컬렉션/객체를 리스트/객체에 담기
                list.add( map );
                // ==================== 2. Dto 활용 ============= //
                /* BoardDto boardDto = new BoardDto();
                boardDto.setBcno( rs.getInt( "bcno" ) );
                boardDto.setBcname( rs.getString( "bcname" ) );
                list.add( boardDto ); */
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list; // 리스트 반환
    }

    // 2. 글 쓰기 처리
    public boolean bWrite(BoardDto boardDto) {
        System.out.println("BoardDao.bWrite");
        System.out.println("boardDto = " + boardDto);

        try {
            String sql = "insert into board(bcno, btitle, bcontent, no, bfile) value (?, ?, ?, ?, ?);";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, boardDto.getBcno());
            ps.setString(2, boardDto.getBtitle());
            ps.setString(3, boardDto.getBcontent());
            ps.setLong(4, boardDto.getNo());
            ps.setString(5, boardDto.getBfile());
            int count = ps.executeUpdate();
            if (count == 1) return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    // 3. 글 전체 호출 처리
    public ArrayList<BoardDto> bPrint() {
        System.out.println("BoardDao.bPrint");
        ArrayList<BoardDto> list = new ArrayList<>();

        try {
            String sql = "select * from board b inner join member m on b.no = m.no order by bdate desc;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                BoardDto boardDto = BoardDto.builder()
                        .bno(rs.getLong("bno"))
                        .btitle(rs.getString("btitle"))
                        .bcontent(rs.getString("bcontent"))
                        .bfile(rs.getString("bfile"))
                        .bview(rs.getLong("bview"))
                        .bdate(rs.getString("bdate"))
                        .name(rs.getString("name"))
                        .bcno(rs.getInt("bcno"))
                        .build();
                System.out.println(boardDto);
                list.add(boardDto);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    // 4. 글 상세 페이지 호출 처리
    public BoardDto bView(int bno) {
        System.out.println("BoardDao.bView");
        try {
            String sql = "select * from board b inner join member m on b.no = m.no inner join bcategory c on b.bcno = c.bcno where bno=? order by bdate desc;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, bno);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                BoardDto boardDto = BoardDto.builder()
                        .bcname(rs.getString("bcname"))
                        .name(rs.getString("name"))
                        .bview(rs.getLong("bview"))
                        .bdate(rs.getString("bdate"))
                        .btitle(rs.getString("btitle"))
                        .bcontent(rs.getString("bcontent"))
                        .bno(rs.getLong("bno"))
                        .build();
                System.out.println(boardDto);
                return boardDto;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

}   // class end
