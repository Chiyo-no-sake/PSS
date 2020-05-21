package ch.supsi.pss.view;

import javafx.scene.layout.VBox;

public abstract class View extends VBox {
    /**
     * this method is automatically called by a ViewManager immediately after a view is shown
     */
    public abstract void onShow();

    /**
     * this method is automatically called by a ViewManager immediately after a view is hided
     */
    public abstract void onHide();
}
