package xyz.nonamed.gameclient.printable;

import javafx.scene.layout.Pane;

public interface PrintableFx {
    void addToPane(Pane pane);
    void print(Pane pane);
}
