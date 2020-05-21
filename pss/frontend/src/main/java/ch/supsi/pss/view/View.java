package ch.supsi.pss.view;

import javafx.scene.layout.VBox;

public abstract class View extends VBox {
    public abstract void onShow();
    public abstract void onHide();
}
