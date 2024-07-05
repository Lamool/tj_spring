package example.day02.consolemvc.view;

import example.day02.consolemvc.controller.PhoneController;
import example.day02.consolemvc.model.dto.PhoneDto;

import java.util.ArrayList;
import java.util.Scanner;

public class PhoneView {
    // [1] 싱글톤 패턴 ((패턴 : 문법 X))
    private static PhoneView phoneView = new PhoneView();   // ((객체를 미리 만들어 놓는 것))
    // 다른 곳에서 사용하지 못하도록 생성자 private
    private PhoneView(){}
    public static PhoneView getInstance() {
        return phoneView;
    }

    private Scanner scan = new Scanner(System.in);

    public void run() {
        while (true) {
            System.out.print("1.[POST/CREATE/C] 2.[GET/SELECT/R] : ");
            int ch = scan.nextInt();

            switch (ch) {
                case 1 :
                    postPhone();
                case 2 :
                    getPhone();
                default :
                    break;
            }
        }
    }

    // 1. 이름과 번호를 입력받아 두 입력값을 객체(dto)화 해서 컨트롤에게 매개변수로 전달후 boolean결과 받아 출력문을 처리한다.
    public void postPhone() {
        // 이름과 전화번호를 입력 받기
        System.out.print(">> name : ");
        String name = scan.next();
        System.out.print(">> phone : ");
        String phone = scan.next();

        PhoneDto phoneDto = new PhoneDto(0, name, phone);
        boolean result = PhoneController.getInstance().postPhone(phoneDto);
;
        if (result) {
            System.out.println(">> save");
        } else {
            System.out.println(">> fail");
        }
    }


    // 2. 컨트롤에게 매개변수 없이 전달 후 ArrayLsist<PhoneDto> 결과물 받아 출력문을 처리한다
    public void getPhone() {
        ArrayList<PhoneDto> result = PhoneController.getInstance().getPhone();
        result.forEach(phone -> {
            System.out.printf("%5d %20s %29s",
            phone.getId(), phone.getName(), phone.getPhone());
        });

    }

}
