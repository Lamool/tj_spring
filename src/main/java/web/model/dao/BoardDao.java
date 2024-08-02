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

    // 3-2. 전체 게시물 수 반환 처리, 조건추가1) 카테고리
    public int getTotalBoardSize(int bcno, String searchKey, String searchKeyword) {
        try {
            String sql = "select count(*) as 총게시물수 from board inner join member on board.no = member.no ";

            // 카테고리가 존재하면, 0 : 카테고리가 없다는 의미, 1 이상 : 카테고리의 pk 번호
            if (bcno >= 1) {
                sql += " where bcno = " + bcno;
            }
            // 검색이 존재 했을 때, keyword가 존재하면
            if (searchKeyword.isEmpty()) {      // 문자열이 비어 있으면, 검색이 없다라는 의미의 뜻으로 활용
            } else {    // 비어있지 않으면, 검색이 있다라는 의미의 뜻으로 활용
                // 카테고리가 있을 때는 and 추가
                if (bcno >= 1) {
                    sql += " and ";
                } else {
                    sql += " where ";
                }
                // 검색 sql
                sql += searchKey + " like '%" + searchKeyword + "%' ";   // '' 문자처리 잘해줘야 됨
            }

            System.out.println("sql = " + sql);
                // 1. 전체보기 : select count(*) as 총게시물수 from board
                // 2. 카테고리 보기 : select count(*) as 총게시물수 from board where bcno = 숫자

            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);    // "총게시물수"
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    // 3. 게시물 전체 출력
    public List<BoardDto> bFindAll(int startRow, int pageBoardSize, int bcno, String searchKey, String searchKeyword) {
        System.out.println("BoardDao.bFindAll");
        ArrayList<BoardDto> list = new ArrayList<>();

        try {
            String sql = "select * " +                       // 1. 조회
                    " from board b inner join member m " + // 조인 테이블
                    " on b.no = m.no ";        // 3. 조인 조건

            // 4. 일반 조건
                // 전체보기이면 where절 생략 , bcno = 0 이면
                // 카테고리별 보기이면 where절 추가 , bcmo >= 1 이상
            if (bcno >= 1) {
                sql += " where bcno = " + bcno;
            }

            // 4. 일반 조건2
            if (searchKeyword.isEmpty()) {
            } else {
                if (bcno >= 1) {
                    sql += " and ";
                } else {
                    sql += " where ";
                }
                sql += searchKey + " like '%" + searchKeyword + "%'";
            }

            // 5. 정렬조건, 내림차순
            sql += " order by b.bno desc ";
            // 6. 레코드 제한, 페이징처리
            sql += " limit ?, ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, startRow);
            ps.setInt(2, pageBoardSize);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // 레코드 를 하나씩 조회해서 Dto vs Map 컬렉션
                BoardDto boardDto = BoardDto.builder()
                        .bno(rs.getLong("bno"))
                        .btitle(rs.getString("btitle"))
                        .bcontent(rs.getString("bcontent"))
                        .bfile(rs.getString("bfile"))
                        .bview(rs.getLong("bview"))
                        .bdate(rs.getString("bdate"))
                        .id(rs.getString("id"))
                        .bcno(rs.getInt("bcno"))
                        .bfile(rs.getString(("bfile")))
                        .build();
                System.out.println(boardDto);
                list.add(boardDto);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    // 4. 게시물 개별 조회 처리
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
                        .id(rs.getString("id"))
                        .bview(rs.getLong("bview"))
                        .bdate(rs.getString("bdate"))
                        .btitle(rs.getString("btitle"))
                        .bcontent(rs.getString("bcontent"))
                        .bcno( rs.getInt("bcno") )
                        .bfile( rs.getString("bfile"))
                        .build();
                System.out.println(boardDto);
                return boardDto;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    // 5. 조회수 증가 처리
    public boolean bViewIncrease(int bno) {
        try {
            String sql = "update board set bview = bview + 1 where bno = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, bno);
            int count = ps.executeUpdate();
            if (count == 1) return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    // 5. 게시물의 댓글 쓰기 처리
    public boolean bReplyWrite(Map<String, String> map) {
        System.out.println("BoardDao.bReplyWrite");
        System.out.println("map = " + map);

        // brindex, brcontent, no, bno 왜?? 네 가지를 저장하는지?
        try {
            String sql = "insert into breply(brindex, brcontent, no, bno) values(?, ?, ?, ?);";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(map.get("brindex")));     // 왜?? Integer.parseInt 하는지?
            ps.setString(2, map.get("brcontent"));
            ps.setInt(3, Integer.parseInt(map.get("no")));
            ps.setInt(4, Integer.parseInt(map.get("bno")));

            int count = ps.executeUpdate();
            if (count == 1) return true;        // 왜 if (count == 1) 하는지?
        } catch (Exception e) {
            System.out.println(e);
        }

        return false;   // 왜?? true/false 사용하는지?
    }

    // 6. 댓글 출력 처리
    public List< Map<String, String> > bReplyPrint(int bno) {
        System.out.println("BoardDao.bReplyPrint");
        System.out.println("bno = " + bno);

        // - list 컬렉션 선언 , map컬렉션(객체)을 여러 개 저장하기 위해 list 선언
        List< Map<String, String> >  list = new ArrayList<>();

        try {
            String sql = "select * from breply inner join member on breply.no = member.no where bno= ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, bno);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Map<String,String> map = new HashMap<>();       // - map 컬렉션/객체 선언
                // - map 컬렉션/객체 엔트리 6개 추가, 댓글번호, 댓글인덱스, 댓글내용, 작성일, 댓글을 작성한 작성자의 회원번호, 댓글이 위치한 게시물번호
                map.put("brno", String.valueOf(rs.getInt("brno")));
                map.put("brindex", String.valueOf(rs.getInt("brindex")));
                map.put("brcontent", String.valueOf(rs.getString("brcontent")));
                map.put("brdate", String.valueOf(rs.getString("brdate")));
                map.put("name", String.valueOf(rs.getString("name")));
                map.put("bno", String.valueOf(rs.getInt("bno")));
                list.add( map );    // - map 컬렉션/객체를 리스트/객체에 담기
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }





}   // class end
