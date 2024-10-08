package web.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import web.model.dao.MemberDao;
import web.model.dto.MemberDto;

import java.util.Map;

@Service
public class MemberService {

    @Autowired MemberDao memberDao;

    // 1. 회원가입
    public boolean mSignup(MemberDto memberDto) {
        System.out.println("MemberService.mSignup");
        System.out.println("memberDto = " + memberDto);
        return memberDao.mSignup(memberDto);
    }

    @Autowired      // 현재 요청을 보낸 클라이언트의 HTTP 요청 정보를 가지고 있는 객체를 주입
    HttpServletRequest request;     // ((request라는 객체 생성. 원래는 매개변수로 가져오는 게 정석인데(?) (day06, day07) Spring에서는 @Autowired를 써서))

    // 2. 로그인
    public boolean mLogin(MemberDto memberDto) {
        System.out.println("MemberService.mLogin");
        System.out.println("memberDto = " + memberDto);

        int result = memberDao.mLogin(memberDto);

        if (result >= 1) {  // 만약에 로그인 성공시
            // 빌더패턴 : 생성자가 아닌 메소드를 이용한 방식의 객체 생성
            MemberDto loginDto = MemberDto.builder()
                    .no(result)
                    .id(memberDto.getId())
                    .build();
            // ----- HTTP 세션 처리 ----- //
            // 1. 현재 요청을 보내온 클라이언트의 세션 객체 호출
            HttpSession session = request.getSession();
            // 2. 세션 객체에 속성 추가
            session.setAttribute("loginDto", loginDto);    // Object로 타입변환이 된다
            return true;
        }
        return false;
    }

    // 3. 로그인의 상태 반환
    public MemberDto mLoginCheck() {        // ((회원번호 여기 있다))
        // 1. 현재 요청을 보내온 클라이언트의 세션객체호출
        HttpSession session = request.getSession();
        // 2. 세션 객체내 속성 값 호출, 타입변환 필요하다.
        Object object = session.getAttribute("loginDto");
        if (object != null) {
            return (MemberDto)object;
        }
        //MemberDto loginDto = (MemberDto)session.getAttribute("loginDto"); // -> 유효성 검사 하는 걸로 바꾸기 전
        return null;
    }

    // 4. 로그아웃 : 세션 초기화
    public void mLogout() {
        // 1. 현재 요청을 보내온 클라이언트의 세션 객체 호출
        HttpSession session = request.getSession();
        // 2. 세션 객체내 모든 속성 값 초기화
        session.invalidate();
            // request.getSession().invalidate();))
    }

    // 5. 마이페이지 정보
    public MemberDto mMyInfo() {
        // 1. 로그인된 회원번호
        MemberDto loginDto = mLoginCheck();     // 로그인된 세션정보 요청
        if (loginDto == null) return null;      // 비로그인이라면 리턴
        int loginMno = loginDto.getNo();
        // 2.
        return memberDao.mMyInfo(loginMno);

//        내가 한 거
//        // 1. 현재 요청을 보내온 클라이언트의 세션 객체 호출
//        HttpSession session = request.getSession();
//        // 2. 세션 객체내 속성 값 호출, 타입변환 필요하다.
//        MemberDto loginDto = (MemberDto)session.getAttribute("loginDto");
//
//        return memberDao.mMyInfo(loginDto);
    }

    // 6. 아이디 중복 검사
    public boolean mIdCheck(String id) {
        return memberDao.mIdCheck(id);
    }


    // 7. 회원 정보 출력
    public MemberDto mUpdatePrint() {
        // 1. 현재 탈퇴하는 회원의 로그인된 번호
        // 1. 로그인 세션객체 호출
        Object object = request.getSession().getAttribute("loginDto");
        // 2. 로그인이 안 된 상태이면 false
        if (object == null) return null;
        // 3. 로그인 세션 객체내 로그인 정보를 타입 변환
        MemberDto memberDto = (MemberDto)object;
        // 4. 로그인 정보에서 회원정보만 추출
        int loginNo = memberDto.getNo();
        // 예전에 만들어둔 mLoginCheck(); 호출해서 써도

        // 5. dao에게 전달
        return memberDao.mUpdatePrint(loginNo);
    }

    // [8] 수정
    public boolean mUpdate( Map<String , String> map ){
        // 1. 현재 로그인된 회원번호 추출
        MemberDto loginDto = mLoginCheck();
        if( loginDto == null ) return false;
        int loginNo = loginDto.getNo();
        // 2. 로그인된 회원번호를 map 엔트리 추가
        map.put( "no" , String.valueOf(loginNo) );
        //map.put( "no" , loginNo+"" );
        return memberDao.mUpdate( map );
    }


//    // 8. 회원 정보 수정
//    public boolean mUpdate(MemberDto memberDto) {
//        return memberDao.mUpdate(memberDto);
//    }


    // 9. 회원 탈퇴
    public boolean mLeave(String pwConfirm) {
        // 1. 현재 탈퇴하는 회원의 로그인된 번호
            // 1. 로그인 세션객체 호출
        Object object = request.getSession().getAttribute("loginDto");
            // 2. 로그인이 안 된 상태이면 false
        if (object == null) return false;
            // 3. 로그인 세션 객체내 로그인 정보를 타입 변환
        MemberDto memberDto = (MemberDto)object;
            // 4. 로그인 정보에서 회원정보만 추출
        int loginNo = memberDto.getNo();
            // 예전에 만들어둔 mLoginCheck(); 호출해서 써도

            // 5. dao에게 전달
        boolean result = memberDao.mLeave(loginNo, pwConfirm);
            // 6. 만약에 탈퇴 성공시 로그아웃
        if (result) {
            mLogout();
        }
            // 7.
        return result;
    }




}   // class end
