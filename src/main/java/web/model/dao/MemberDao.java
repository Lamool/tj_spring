package web.model.dao;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import web.model.dto.MemberDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Component
public class MemberDao extends Dao {

    // 1. 회원가입
    public boolean mSignup(MemberDto memberDto) {
        System.out.println("MemberDao.mSignup");
        System.out.println("memberDto = " + memberDto);

        try {
            String sql = "insert into member(id, pw, name, email, phone) values(?, ?, ?, ?, ?);";
            PreparedStatement ps = super.conn.prepareStatement(sql);
            ps.setString(1, memberDto.getId());
            ps.setString(2, memberDto.getPw());
            ps.setString(3, memberDto.getName());
            ps.setString(4, memberDto.getEmail());
            ps.setString(5, memberDto.getPhone());
            int count = ps.executeUpdate();
            if (count == 1) return true;
        } catch (Exception e) {
            System.out.println("MemberDao.mSignup() : " + e);
        }
        return false;
    }   // mSignup end
    
    // 2. 로그인 : 로그인 성공한 회원번호 반환(세션에 저장할려고)
    public int mLogin(MemberDto memberDto) {
        System.out.println("MemberDao.mLogin");
        System.out.println("memberDto = " + memberDto);

        try {
            String sql = " select * from member where id=? and pw=?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, memberDto.getId());
            ps.setString(2, memberDto.getPw());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("no");
            }
        } catch (Exception e) {
            System.out.println("MemberDao.mLogin() : " + e);
        }
        return 0;   // 0은 회원번호가 없다 뜻
    }   // mLogin end

    // 5. 마이페이지 정보
    public MemberDto mMyInfo(int loginMno) {
        try {
            String sql = "select * from member where no=?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, loginMno);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return MemberDto.builder()
                        .no(rs.getInt("no"))
                        .id(rs.getString("id"))
                        .phone(rs.getString("phone"))
                        .email(rs.getString("email"))
                        .name(rs.getString("name"))
                        .build();
            }
        } catch (Exception e) {
            System.out.println("MemberDao.myInfo() : " + e);
        }
        return null;
    }

    /*
    // 5. 마이페이지 정보 - 내가 한 거
    public MemberDto mMyInfo(MemberDto loginDto) {
        System.out.println("MemberDao.myInfo");
        System.out.println(loginDto.getNo());

        try {
            String sql = "select * from member where no=?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, loginDto.getNo());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                MemberDto memberDto = new MemberDto();
                memberDto.setId(rs.getString("id"));
                memberDto.setName(rs.getString("name"));
                memberDto.setEmail(rs.getString("email"));
                memberDto.setPhone(rs.getString("phone"));
                return memberDto;
            }
        } catch (Exception e) {
            System.out.println("MemberDao.myInfo() : " + e);
        }
        return null;
    }
*/

    // 6. 아이디 중복 검사 ( C *R U D )
    public boolean mIdCheck(String id) {
        System.out.println("MemberDao.mIdCheck");
        System.out.println("id = " + id);

        // sql은 대소문자를 구분하지 않고 검색 진행.
        try{
            String sql = "select id from member where binary(id) = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1 , id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;        // 중복이다.
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return false; // 중복이 아니다.
    }


    // 7. 회원 정보 출력
    public MemberDto mUpdatePrint(int loginNo) {
        try {
            System.out.println(loginNo);
            String sql = "select * from member where no = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, loginNo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                MemberDto memberDto = MemberDto.builder()
                        .no(rs.getInt("no"))
                        .id(rs.getString("id"))
                        .pw(rs.getString("pw"))
                        .phone(rs.getString("phone"))
                        .email(rs.getString("email"))
                        .name(rs.getString("name"))
                        .build();
                System.out.println(memberDto);
                return memberDto;
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }


//    // 8. 회원 정보 수정
//    public boolean mUpdate(MemberDto memberDto) {
//        System.out.println("MemberDao.mUpdate");
//        System.out.println("memberDto = " + memberDto);
//
//        try {
//            String sql = "update member set name = ?, pw = ?, phone = ? where no = ?;";
//            PreparedStatement ps = conn.prepareStatement(sql);
//            ps.setString(1, memberDto.getName());
//            ps.setString(2, memberDto.getPw());
//            ps.setString(3, memberDto.getPhone());
//            ps.setInt(4, memberDto.getNo());
//            int count = ps.executeUpdate();
//            if (count == 1) {
//                return true;
//            }
//        } catch (Exception e) {
//            System.out.println("e = " + e);
//        }
//        return false;
//    }


    // 9. 회원 탈퇴
    public boolean mLeave(int loginNo, String pwConfirm) {
        System.out.println("MemberDao.mLeave");
        System.out.println("loginNo = " + loginNo);

        try {
            String sql = "delete from member where no = ? and pw = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, loginNo);
            ps.setString(2, pwConfirm);
            int count = ps.executeUpdate();
            if (count == 1) return true;
        } catch (Exception e) {
            System.out.println("e = " + e);
        }
        return false;
    }



}   // class end
