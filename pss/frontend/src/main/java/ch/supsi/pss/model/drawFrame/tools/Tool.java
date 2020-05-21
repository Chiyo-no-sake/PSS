package ch.supsi.pss.model.drawFrame.tools;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 * abstract class, used to create other tools
 *  - listeners need to be implemented in each tool to work with the canvas, that auto calls the method from
 *    a selected object of this type.
 *
 *  - toolName String can be used for testing and debugging purposes
 *  // Upper canvas will catch all events, in canvasController, don't use event.src()
 *  // instad use the singleton controller *DrawCanvasController.getDrawCanvas()*
 */
public abstract class Tool implements Drawable {
    private String toolName;
    EventHandler<MouseEvent> onMousePressed;
    EventHandler<MouseEvent> onMouseReleased;
    EventHandler<MouseEvent> onMouseDragged;

    Tool(String toolName){
        this.toolName = toolName;
        setOnMousePressed();
        setOnMouseReleased();
        setOnMouseDragged();
    }

    public String getToolName() {
        return toolName;
    }

    @Override
    public EventHandler<MouseEvent> getOnMousePressed() {
        return onMousePressed;
    }

    /**
     * implement inside a method that set this.onMousePressed to a function
     */
    abstract void setOnMousePressed();

    @Override
    public EventHandler<MouseEvent> getOnMouseDragged() {
        return onMouseDragged;
    }

    abstract void setOnMouseDragged();

    @Override
    public EventHandler<MouseEvent> getOnMouseReleased() {
        return onMouseReleased;
    }

    abstract void setOnMouseReleased();
}
