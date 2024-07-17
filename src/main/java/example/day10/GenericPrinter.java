package example.day10;

public class GenericPrinter<T extends Material> {
    // T : plastic, powder 일 수도 있고 그 외일 수도 있고
    private T material;

    public T getMaterial() {
        return material;
    }

    public void setMaterial(T material) {
        this.material = material;
    }

    public String toString() {
        return material.toString();
    }

    public void doPrint() {
        material.doPrinting();  // T : material 필드가 plastic, powder의 메소드를 사용할 수 없다. ((아직 T가 뭔지 안 정해져서?라고? 이런 의미로 적어주신 건가))
        // <T extends 상위클래스> : 상위클래스의 메소드는 사용할 수 있다.
    }

}
