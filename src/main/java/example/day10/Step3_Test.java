package example.day10;

public class Step3_Test {
    public static void main(String[] args) {
        Point<Integer, Double> p1 = new Point(0, 0.0);
        Point<Integer, Double> p2 = new Point(10, 10.0);

        MakeR_Step3_Test<Integer, Double> makeR = new MakeR_Step3_Test<>();
        double rect = makeR.makeRectangle(p1, p2);     // <Integer, Double> 생략 가능
        System.out.println("rect = " + rect);
    }

}
