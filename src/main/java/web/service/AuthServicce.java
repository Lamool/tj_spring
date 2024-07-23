package web.service;

import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service    // 스프링 컨테이너의 등록하고 빈(객체) 생성
public class AuthServicce {

    @Autowired HttpServletRequest request;      // HTTP 요청 객체

    // 1. 인증번호 요청/생성
    public boolean authCode(String email) {
        try {
            // 1. 인증코드가 문자인 이유 : 앞자리에 0이 들어가야 해서
            String authCode = "";

            // 2. 난수 생성, Random 클래스
            Random random = new Random();
            // random.nextInt();    // int 타입의 표현범위내 난수 생성 함수
            for (int i = 0; i < 6; i++) {
                authCode += random.nextInt(10);     // 0~9 까지의 난수 생성
            }

            System.out.println("authCode = " + authCode);

            // 3. (저장소 선택) DB : 영구적인 데이터 vs JVM(스택, 힙, 메소드) vs 세션(웹서버 저장소-브라우저마다)
                // 1. 서버 세션 객체 속성의 인증 코드를 저장
            request.getSession().setAttribute("authCode", authCode);    // 들어갈 때는 String이었지만 저장될 때는 Object
                // 2. 서버 세션 객체의 생명주기(세션이 유지되는 시간)  // 초 기준 // 10초 동안 세션 유지하고 10초 후 삭제
            request.getSession().setMaxInactiveInterval(10);
            // 이메일 전송
            //emailSend(email, "000 홈페이지의 회원가입 인증코드", "인증코드 : " + authCode);

                Object object = request.getSession().getAttribute("authCode");
                System.out.println(">>>object = " + object);
            return true;
        } catch (Exception e) {
            System.out.println("e = " + e);
        }
        return false;
    }


    // 2. 입력받은 값과 인증 번호를 인증/비교
    public boolean authCheck(String authCodeInput) {
        // 1. 인증 번호 호출
        System.out.println("AuthServicce.authCheck");
        System.out.println("authCodeInput = " + authCodeInput);

        // 1. 인증 번호 호출
        Object object = request.getSession().getAttribute("authCode");
        System.out.println("object = " + object);
        if (object != null) {   // 세션객체의 인증번호가 존재하지  않으면
            String authCode = (String)object;       // 강제타입 변환
            System.out.println("authCode = " + authCode);
            // 입력받은 인증번호와 인증번호 비교
            if (authCode.equals(authCodeInput)) {
                return true;    // 동일하면 true
            }
        }
        return false;

    }
    
    @Autowired JavaMailSender javaMailSender;   // javaMail 객체 제공

    // 3. 이메일 전송 함수, 매개변수 : 받는 사람의 이메일, 메일 제목, 메일 내용
    public void emailSend(String toEmail, String subject, String content) {
        try {
            // 1. 메일 내용물들을 포맷/형식 맞추기 위해 MIME
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();

            // 2. 메일 내용 구성
            // new MimeMessageHelper(Mime객체, 첨부파일여부, 인코딩타입),    일반 예외 발생
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");

            // 3. 메일 보내는 사람
            mimeMessageHelper.setFrom("");    // 관리자의이메일, 본인이메일

            // 4. 메일 받는 사람
            mimeMessageHelper.setTo(toEmail);

            // 5. 메일 제목
            mimeMessageHelper.setSubject(subject);

            // 6. 메일 내용
            mimeMessageHelper.setText(content);

            // 7. *** 메일 전송
            javaMailSender.send(mimeMessage);   // mime 객체를 보내기

        } catch (Exception e) {
            System.out.println("e = " + e);
        }
    }



}   // class end
