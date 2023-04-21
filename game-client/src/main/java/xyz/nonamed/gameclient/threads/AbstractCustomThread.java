package xyz.nonamed.gameclient.threads;

import javafx.animation.AnimationTimer;
import lombok.Getter;

import static xyz.nonamed.gameclient.threads.ThreadConfig.ONE_MILLISECOND;

@Getter
public abstract class AbstractCustomThread extends AnimationTimer {

    long startTime = 0;
    long delay = 100 * ONE_MILLISECOND;

    @Override
    public void handle(long now) {
        if (startTime == 0) {
            startTime = now;
        } else if (now - startTime >= getDelay()) {
            doWithDelay();
            startTime = 0;
        }
    }

    public abstract void doWithDelay();

    public abstract long getDelay();

}