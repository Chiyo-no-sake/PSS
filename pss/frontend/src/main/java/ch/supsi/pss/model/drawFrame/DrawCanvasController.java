package ch.supsi.pss.model.drawFrame;

import ch.supsi.pss.sketch.SketchController;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 * singleton class for canvas controller
 */
public class DrawCanvasController {
    private DrawCanvas dc;
    private SketchController sketchController;

    private EventHandler<MouseEvent> activeEventOnDown;
    private EventHandler<MouseEvent> activeEventOnDrag;
    private EventHandler<MouseEvent> activeEventOnUp;

    private static DrawCanvasController instance;

    public static DrawCanvasController getInstance(){
        if(instance == null){
            instance = new DrawCanvasController();
        }

        return instance;
    }

    private DrawCanvasController(){
        activeEventOnUp = null;
        activeEventOnDrag = null;
        activeEventOnDown = null;
    }

    public void setSketchController(SketchController sketchController){
        this.sketchController = sketchController;
    }

    public SketchController getSketchController(){
        return sketchController;
    }

    public void setDrawCanvas(DrawCanvas dc) {
        this.dc = dc;
    }

    public DrawCanvas getDrawCanvas(){
        return dc;
    }

    void setMouseHandlers(EventHandler<MouseEvent> onDown, EventHandler<MouseEvent> onDrag, EventHandler<MouseEvent> onUp){
        activeEventOnDown = onDown;
        activeEventOnDrag = onDrag;
        activeEventOnUp = onUp;

        dc.getUpperCanvas().addEventHandler(MouseEvent.MOUSE_PRESSED,  onDown);
        dc.getUpperCanvas().addEventHandler(MouseEvent.MOUSE_DRAGGED, onDrag);
        dc.getUpperCanvas().addEventHandler(MouseEvent.MOUSE_RELEASED, onUp);
    }

    void resetMouseHandlers(){
        if(activeEventOnDown != null)
            dc.getUpperCanvas().removeEventHandler(MouseEvent.MOUSE_PRESSED, activeEventOnDown);

        if(activeEventOnDrag != null)
            dc.getUpperCanvas().removeEventHandler(MouseEvent.MOUSE_DRAGGED, activeEventOnDrag);

        if(activeEventOnUp != null)
            dc.getUpperCanvas().removeEventHandler(MouseEvent.MOUSE_RELEASED, activeEventOnUp);

        activeEventOnUp = null;
        activeEventOnDrag = null;
        activeEventOnDown = null;
    }
}