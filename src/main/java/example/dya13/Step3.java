package example.dya13;


import java.util.TreeSet;

public class Step3 {
    public static void main(String[] args) {
        // 1.
        TreeSet<Member> members = new TreeSet<>();
            //

        // 2,
        members.add(new Member("홍길동", 29));
        members.add(new Member("유재석", 48));
        members.add(new Member("강호동", 35));
        members.add(new Member("신동엽", 24));
        System.out.println("members = " + members);




    }

}
