package ch.supsi.pss.drawFrame;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;


class DrawCanvasEventController {
    private final DrawCanvas dc;

    private EventHandler<MouseEvent> activeEventOnDown;
    private EventHandler<MouseEvent> activeEventOnDrag;
    private EventHandler<MouseEvent> activeEventOnUp;

    DrawCanvasEventController(DrawCanvas dc){
        this.dc = dc;

        activeEventOnUp = null;
        activeEventOnDrag = null;
        activeEventOnDown = null;
    }

    void setMouseHandlers(EventHandler<MouseEvent> onDown, EventHandler<MouseEvent> onDrag, EventHandler<MouseEvent> onUp){
        activeEventOnDown = onDown;
        activeEventOnDrag = onDrag;
        activeEventOnUp = onUp;

        dc.addEventHandler(MouseEvent.MOUSE_PRESSED,  onDown);
        dc.addEventHandler(MouseEvent.MOUSE_DRAGGED, onDrag);
        dc.addEventHandler(MouseEvent.MOUSE_RELEASED, onUp);
    }

    void resetMouseHandlers(){
        if(activeEventOnDown != null)
            dc.removeEventHandler(MouseEvent.MOUSE_PRESSED, activeEventOnDown);

        if(activeEventOnDrag != null)
            dc.removeEventHandler(MouseEvent.MOUSE_DRAGGED, activeEventOnDrag);

        if(activeEventOnUp != null)
            dc.removeEventHandler(MouseEvent.MOUSE_RELEASED, activeEventOnUp);

        activeEventOnUp = null;
        activeEventOnDrag = null;
        activeEventOnDown = null;
    }
}
