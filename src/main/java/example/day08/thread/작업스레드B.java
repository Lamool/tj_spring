package example.day08.thread;

import java.awt.*;

public class 작업스레드B implements Runnable {
    // implements : 구현하다

    @Override
    public void run() {
        // 1. '띵' 5회 소리 출력
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        for (int i = 1; i <= 5; i++) {  // 5회 반복
            toolkit.beep();     // '띵' 비프음 소리 출력
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println("e = " + e);
            }
        }   // for end
    }   // run end
}
