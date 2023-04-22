package xyz.nonamed.gameclient.printable;

import javafx.scene.layout.Pane;
import xyz.nonamed.Alive;

public interface AliveFx extends Alive, PrintableFx {

    Long getId();

    void deleteFromPane(Pane pane);

}
