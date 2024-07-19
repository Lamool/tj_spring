package example.day12;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Stpe4 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        // 1. HashSet 선언
        Set<User> set = new HashSet<>();

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
                set.add(user);
            } else if (ch == 2) {
                set.forEach(user -> System.out.printf("%s \t %d\n", user.name, user.age));
            } else if (ch == 3) {
                System.out.print("수정할 이름 : ");
                String name = scan.next();
                set.forEach(user -> {
                    if(user.name.equals(name)) {
                        System.out.println("수정할 나이");
                        int age = scan.nextInt(); user.age = age;
                    }
                });
            } else if (ch == 4) {
                System.out.print("삭제할 이름 : ");
                String name = scan.next();
                set.forEach(user -> {
                    if (user.name.equals(name)) {
                        set.remove(user);
                    }
                });
            } else {
                System.out.println("잘못된 입력입니다.");
            }
        }
    }

}
