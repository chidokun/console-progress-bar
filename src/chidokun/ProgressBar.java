package chidokun;

import java.io.Console;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author tuannlh
 */
public class ProgressBar {

    private String animation = "⠋⠙⠹⠸⠼⠴⠦⠧⠇⠏";
    private int width = 40;
    private double value = 0;
    private double maxRange = 100d;

    private int animationIndex = 0;
    private final Timer timer;
    private boolean isSuccess = false;
    private boolean isFailed = false;

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
        this.isSuccess = true;
        this.isFailed = false;
        this.render();
        this.stop();
    }

    public void reportError() {
        this.isSuccess = false;
        this.isFailed = true;
        this.render();
        this.stop();
    }

    public void render() {
        int currentBlock = (int) (value / maxRange * width);
        char symbol = isSuccess
                ? '\u2714'
                : isFailed
                ? '\u2718'
                : animation.charAt(animationIndex % animation.length());
        String text = String.format("%s Processing: [%s%s] %.0f/%.0f\r",
                symbol,
                Strings.repeat('█', currentBlock),
                Strings.repeat('░', width - currentBlock),
                value,
                maxRange);
        System.out.print(text);
    }

    public void stop() {
        timer.cancel();
    }
}
