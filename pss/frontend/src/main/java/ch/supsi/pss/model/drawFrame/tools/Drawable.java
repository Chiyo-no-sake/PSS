package ch.supsi.pss.model.drawFrame.tools;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public interface Drawable {

    public EventHandler<MouseEvent> getOnMousePressed();

    public EventHandler<MouseEvent> getOnMouseDragged();

    public EventHandler<MouseEvent> getOnMouseReleased();
}
