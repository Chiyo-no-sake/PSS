package ch.supsi.pss.model.drawFrame.toolbar.tools;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public interface Drawable {

    public EventHandler<MouseEvent> getOnMousePressed();

    public EventHandler<MouseEvent> getOnMouseDragged();

    public EventHandler<MouseEvent> getOnMouseReleased();

    void setOnMousePressed(EventHandler<MouseEvent> onMousePressed);

    void setOnMouseDragged(EventHandler<MouseEvent> onMouseDragged);

    void setOnMouseReleased(EventHandler<MouseEvent> onMouseReleased);
}
