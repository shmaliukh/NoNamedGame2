package xyz.nonamed.gameclient.threads;

import lombok.Getter;
import lombok.NoArgsConstructor;

import static xyz.nonamed.gameclient.threads.ThreadConfig.*;

@Getter
@NoArgsConstructor
public abstract class CustomThread extends AbstractCustomThread {

    long delay = 100 * ONE_MILLISECOND;

    public abstract void doWithDelay();

}