package web.service;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.UUID;

@Service
public class FileService {
    // [8]
    String uploadPath = "C:\\Users\\tj-bu-703-14\\Desktop\\tj_spring\\src\\main\\resources\\static\\upload\\";


    // [1] 파일 업로드
        // 매개변수로 파일의 바이트가 저장된 MultipratFile 인터페이스
        // 업로드 된 파일명 변환
    public String fileUpload(MultipartFile multipartFile) {
        System.out.println(multipartFile.getContentType());
        System.out.println(multipartFile.getSize());
        System.out.println(multipartFile.isEmpty());
        // 1. 첨부 파일의 실제 파일 이름 추출
        // 클라이언트(유저)들이 서로 다른 파일내용의 같은 파일명으로 업로드 했을 때 식별이 불가능.
        // 해결방안 1. UUID(고유성 보장하는 ID 규약) 2. 조합식별 설계(주로 업로드 날짜/시간과 파일명 조합, 게시물번호)
        String uuid = UUID.randomUUID().toString();     // 난수의 UUID 생성, 임의의 UUID 규약에 따른 문자열 생성
        System.out.println("uuid = " + uuid);

        String fileName = multipartFile.getOriginalFilename();
        // UUID + 파일명 합치기, uuid와 파일명 구분하는 문자 조합, 파일명에 _(언더바)가 존재하면 안 된다.
        // 추후에 _(언더바) 기준으로 분해하면 [0] UUID, [1] 순수파일명
        // "문자열".replaceAll("기존문자", "새로운문자") : 만약에 문자열내 기존문자가 존재하면 새로운문자로 치환해서 반환
        fileName = uuid + "_" + fileName.replaceAll("_", "-");      // 파일명에 '_' 문자가 존재하면 '-' 문자로 변경
        System.out.println("fileName = " + fileName);

        // 2. 첨부파일을 저장/복사/이동 할 경로 만들기 - 위에

        // 3. 첨부파일을 저장/복사/이동 경로와 파일명 합치기
        String filePath = uploadPath + fileName;

        // 4. 해당 경로로 설정한 file 객체, transferTo(file객체)
        File file = new File(filePath);
        // 5. transferTo(file객체) : file객체내 설정한 해당 경로로 파일 복사/저장/이동
        // 일반제외 무조건 발생
        try {
            multipartFile.transferTo(file);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
        return fileName;

        // 바이트를 카피하고 있는 것
    }

    @Autowired private HttpServletRequest request;
    @Autowired private HttpServletResponse response;

    // [2] 파일 다운로드
    public void fileDownLoad(String filename) {
        System.out.println("FileService.fileDownLoad");
        System.out.println("filename = " + filename);

        // 1. 다운로드 할 경로 설정 uploadPath
            // 업로드된 경로와 다운로드할 파일명 조합
        String downLoadPath = uploadPath + filename;

        // File 클래스는 file 관련된 다양한 메소드 제공
        File file = new File(downLoadPath);
        if(!file.exists()) {    // 파일이 존재하지 않으면
            return;
        }

        // 2. 해당 다운로드 할 파일을 서버(자바)로 바이트 형식 읽기
            // 스트림이란 : 자바 외부와 통신시 바이트가 다니는 통로
            // InputStream : 읽어들이는 통로, OutPutStream : 내보내는 통로
            // Buffered, 버퍼 : 특정 위치로 이동하는 동안 일시적으로 데이터를 보관하는 메모리(스트림에서도 사용)
        try {
            // ===================== 파일을 바이트 배열로 읽어오기 ===================== //
            // 2-1 파일 입력스트림 객체 생성, new BufferedInputStream (읽어올 파일의 스트림 객체)
            //BufferedInputStream fin = new BufferedInputStream(new FileInputStream(downLoadPath));
            FileInputStream fin = new FileInputStream( downLoadPath );
            // 2-2 파일의 용량만큼 배열 선언
                // 파일의 용량
            long fileSize = file.length();
                // 파일의 용량만큼 배열의 길이 선언
            byte[] bytes = new byte[(int)fileSize];
                // .read(파일명)
            fin.read(bytes);     // 경로에 대당하는 파일을 바이트로 가져오기

            System.out.println(Arrays.toString(bytes));
            fin.close();    // 버퍼 닫기

            // ===================== 읽어온 바이트배열을 HTTP 바이트 형식으로 응답하기 ===================== //
            // [3] HTTP 스트림으로 응답하기
                // 3-1 출력스트림 객체 생성, new BufferedOutputStream(출력할 대상의 스트림객체)

            ServletOutputStream fout = response.getOutputStream();

            // HTTP 응답의 헤더 속성 추가 .setHeader({ key, value)
                // Content-Disposition : 브라우저가 제공하는 다운로드 형식
                // attachment;filename='
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename.split("_")[1], "UTF-8"));


            // 3-2 바이트배열 내보내기/출력/쓰기
            fout.write(bytes);

            fout.close();    // 버퍼 닫기
        } catch (Exception e) {
            System.out.println(e);
        } finally { // 예외 발생 여부와 상관없이 무조건 실행되는 구역
            // 선언부를 밖으로 빼야
            //fout.close();    // 버퍼 닫기
        }



    }


}
