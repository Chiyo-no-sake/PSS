package ch.supsi.pss.model.drawFrame.canvas;

import ch.supsi.pss.sketch.SketchController;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

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
        sketchController.setBytes(getCanvasSnapshot());
        return sketchController;
    }

    public void setDrawCanvas(DrawCanvas dc) {
        this.dc = dc;
    }

    public DrawCanvas getDrawCanvas(){
        return dc;
    }

    private byte[] getCanvasSnapshot(){
        WritableImage writableImage = new WritableImage(
                (int)dc.getWidth(),
                (int)dc.getHeight());
        dc.snapshot(null, writableImage);

        BufferedImage buff = SwingFXUtils.fromFXImage(writableImage, null);

        try(ByteArrayOutputStream baos = new ByteArrayOutputStream()){
            ImageIO.write(buff, "png", baos);
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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
