package xyz.nonamed.gameclient.threads;

import lombok.Getter;
import lombok.NoArgsConstructor;

import static xyz.nonamed.gameclient.threads.ThreadConfig.*;

@Getter
@NoArgsConstructor
public class CustomThread extends AbstractCustomThread {

    long delay = 100 * ONE_MILLISECOND;


    @Override
    public void doWithDelay() {

    }

}