package example.day08.todo;

import org.springframework.web.bind.annotation.*;

import java.sql.DriverManager;
import java.util.ArrayList;

@RestController     // 해당 클래스가 스프링MVC 패턴에서 controller 역할, 스프링 컨테이너(저장소) 빈)객체) 등록
public class ToDoController {
    // [1] 싱글톤 패턴 : JVM 메소드 영역에 static 변수에 객체 주소값 저장
//    private static ToDoController todoController = new ToDoController();
//    private ToDoController(){ }
//    public static ToDoController getInstance() {
//        return todoController;
//    }


    // 1. 할 일 등록
    @PostMapping("/todo/create")    // ((주소를 정하는 건 본인 마음대로))
    public boolean todoCreate(String tcontent) {
        boolean result = TodoDao.getInstance().todoCreate(tcontent);
        return result;
    }

    // 2. 할 일 전체 출력
    @GetMapping("todo/readall")
    public ArrayList<TodoDto> todoReadAll() {
        ArrayList<TodoDto> result = TodoDao.getInstance().todoReadAll();
        return result;
    }

    // 3. 할 일 (상태) 수정
    @PutMapping("/todo/update")
    public boolean todoUpdate(int tno) {
        boolean result = TodoDao.getInstance().todoUpdate(tno);
        return result;
    }


    // 4. 할 일 삭제
    @DeleteMapping("todo/delete")
    public boolean todoDelete(int tno) {
        boolean result = TodoDao.getInstance().todoDelete(tno);
        return result;
    }



}
