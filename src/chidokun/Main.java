package chidokun;

import java.util.Random;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        final int MAX = 100;
        Random rand = new Random();
        ProgressBar p = new ProgressBar();
        p.setMaxRange(MAX);
        for (int i = 1; i <= MAX; i++) {
            p.setValue(i);
            Thread.sleep(50 + rand.nextInt(300));
        }
        p.reportSuccess();
    }
}
