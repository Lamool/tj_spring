package example.dya13;

public class Member implements Comparable<Member> {
    String name;
    int age;

    public Member(String name, int age) {
        this.name = name;
        this.age = age;
    }


    // * Comparable 인터페이승의 추상메소드 정의
    @Override
    public int compareTo(Member o) {
        // 1. name 기준으로 정렬, String 클래스내 compareTo 활용
        // return this.name.compareTo(o.name);
        // 2. age 기준으로 정렬
        if ( this.age < o.age ) { return -1; }
        else if (this.age== 0){ return 0;
        } else {
            return 1;
        }
    }

    @Override
    public String toString() {
        return "Member{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
