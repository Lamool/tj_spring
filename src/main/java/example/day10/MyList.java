package example.day10;

import java.util.Arrays;

public class MyList < T > {
    private int size;
    private T[] array;

    public MyList(){
        this.array = (T[]) new Object [this.size ];
    }

//    private Integer[] array;
//
//    public MyList(){
//        this.array = (Integer[]) new Byte[ 3 ];
//    }
//
//    Long[] numbers = (Long[]) new Integer[3];
//    Integer[] array = (Integer[]) new Byte[ 3 ];

    public boolean add( T value ){
        // 코드구현
        // [1] 사이즈 증가
        this.size++;

        // [2] 증가된 사이즈로 새로운 배열(기존 배열에 사이즈를 추가가 아닌 교체 형식으로 구현)
        T[] newArray = (T[])(new Object[ this.size ]);      // Object로 만든 다음에 제네릭 타입으로 강제 형변환

        // [3] 기존 배열의 요소들을 새로운 배열에 대입
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }

        // [4] 기존 배열 보다 새로운 배열이 사이즈가 1개 더 크기 때문에
        newArray[size - 1] = value;

        // [5] 새로운 배열에는 기존 요소와 새로운 요소 들어있는 상태를 기본배열에 대입
        array = newArray;

        return true;

    }

    public boolean remove( int index ){
        // 존재하지 않는 인덱스가 있으면 false 반환
        if (index >= this.array.length) return false;

        //array[index] = null;  // 이거랑 밑에 for문 안 주석 세 줄 예전에 backend -> day09/step6 방식

        // [1] 사이즈 감소
        this.size--;

        // [2] 감소된 사이즈로 새로운 배열
        T[] newArray = (T[])(new Object[ this.size ]);

        // [3] 기존 배열에서 삭제한 인덱스 뒤로 한 칸씩 당기기
        int j = 0;  // 새로운 배열의 인덱스
        // i : 기존 배열의 인덱스
        for (int i = 0; i < this.array.length; i++) {
//            if (array[i] == null) n++;
//            if (i + n == array.length) break;
//            newArray[i] = array[i + n];

            // 삭제할 인덱스는 제외하고 복사
            if (i == index) {
                continue;   // 반복문(증감식)으로 이동
            }
            newArray[j] = this.array[i];
            j++;
        }

        // [4] add와 다르게 remove는 [4]가 없다

        // [5]
        this.array = newArray;

        return true;
    }

    public T get( int index ){
        // 존재하지 않는 인덱스가 있으면 null 반환
        if (index >= this.array.length) {
            return null;
        }

        return this.array[index];
    }

    @Override
    public String toString() {
        return "MyList{" +
                "size=" + size +
                ", array=" + Arrays.toString(array) +
                '}';
    }
}
