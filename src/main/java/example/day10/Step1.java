package example.day10;

public class Step1 {
    public static void main(String[] args) {
        // 1.
        Box1 box1 = new Box1();
        box1.content = "안녕하세요";
        String content1 = box1.content;

        Box2 box2 = new Box2();
        box2.content = 100;
        int content2 = box2.content;

        // 2.
        Box3 box3 = new Box3();
        // 자동타입 변환 String -> object
        box3.content = "안녕하세요";
        // 강제타입 변환 object -> String     ((상속을 생각))
        // String content3 = box3.content;  // 이렇게만 써주면 에러
        String content3 = (String)box3.content;

        // 자동타입 변환 int -> object
        box3.content = 100;
        // 강제타입 변환 object -> int
        // int content4 = box3.content;     // 이렇게만 써주면 에러
        int content4 = (int)box3.content;

        // 3.
        Box4<String> box4 = new Box4 <String>();
        box4.content = "안녕하세요";
        String content5 = box4.content;

        Box4<Integer> box5 = new Box4<>();      // ((기본 타입은 안 된다. int 안 됨.    뒤에 제네릭 생략 가능.))
        box5.content = 100;
        int content6 = box5.content;

    }

}
