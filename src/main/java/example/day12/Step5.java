package example.day12;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Step5 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Map<String, Integer> map = new HashMap<>();

        while (true) {
            System.out.print("1.등록 2.전체출력 3.수정 4.삭제 5.종료 : ");
            int ch = scan.nextInt();

            if (ch == 1) {
                // 1. 이름, 나이 입력 받기
                System.out.print("이름 입력 : ");
                String name = scan.next();
                System.out.print("나이 입력 : ");
                int age = scan.nextInt();

                // 2. LinkedList에 값 추가하기
                User user = new User();
                user.name = name;
                user.age = age;
                map.put(name, age);
            } else if (ch == 2) {
                map.keySet().forEach(key -> {System.out.printf("%s \t %d\n", key, map.get(key));});
            } else if (ch == 3) {
                System.out.print("수정할 나이 : ");
                String name = scan.next();

                map.keySet().forEach(key -> {
                    if(key.equals(name)) {
                        System.out.println("수정할 나이");
                        int age =scan.nextInt();
                        map.put(key, age);
                        // map.replace(key, age)    // 수정
                    }
                });
            } else if (ch == 4) {
                System.out.print("삭제할 이름 : ");
                String name = scan.next();
                map.remove((name));
            }
        }
    }

}
