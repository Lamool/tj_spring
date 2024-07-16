package example.day09.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// [2] Web (springboot)
@SpringBootApplication
// 1. 내장톰캣(웹서버) 2. restful 지원 3. 스프링 MVC 지원
public class AppStart {
    public static void main(String[] args) {
        SpringApplication.run(AppStart.class);

//        [1]
//        // 1. 할 일 등록
//        ToDoController.getInstance().todoCreate("파이썬공부");
//
//        // 2. 할 일 전체 출력
//        ArrayList<TodoDto> result = ToDoController.getInstance().todoReadAll();
//        System.out.println(result);
//
//        // 3. 할 일 (상태) 수정
//        ToDoController.getInstance().todoUpdate(3);
//
//        // 4. 할 일 삭제
//        ToDoController.getInstance().todoDelete(2);





    }

}


/*
    DB 설계 -> DTO -> Dao db 연동
        처음부터 script하지 말고 샘플 데이터를 이용해서(Talend 사이트에서 테스트 진행. Response에 뜨면 성공) Controller -> dao 처리
        HTML -> JS(AJAX) 처리

*/
