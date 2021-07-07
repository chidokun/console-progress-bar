package chidokun;

import java.util.Timer;
import java.util.TimerTask;

public class ProgressBar {

    private String animation = "⠋⠙⠹⠸⠼⠴⠦⠧⠇⠏";
    private int width = 40;
    private double value = 0;
    private double maxRange = 100d;

    private int animationIndex = 0;
    private final Timer timer;
    private int flag = 0;

    public ProgressBar() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                animationIndex++;
                render();
            }
        }, 100, 100);
    }

    public void setMaxRange(double maxRange) {
        this.maxRange = maxRange;
    }

    public void setValue(double value) {
        this.value = value;
        this.render();
    }

    public void reportSuccess() {
        this.flag = 1;
        this.render();
        this.stop();
    }

    public void reportError() {
        this.flag = -1;
        this.render();
        this.stop();
    }

    public void render() {
        int currentBlock = (int) (value / maxRange * width);
        char symbol = getSymbol();
        String filledBlock = Strings.repeat('#', currentBlock);
        String remainBlock = Strings.repeat('-', width - currentBlock);
        String text = String.format("%s [%s%s] %.0f/%.0f\r", symbol, filledBlock, remainBlock, value, maxRange);
        System.out.print(text);
    }

    private char getSymbol() {
        switch (flag) {
            case 1: return '\u2714';
            case -1: return '\u2718';
            default: return animation.charAt(animationIndex % animation.length());
        }
    }

    public void stop() {
        timer.cancel();
    }
}
