package ch.supsi.pss.model.drawFrame.toolbar.tools;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 * abstract class, used to create other tools
 *  - listeners need to be implemented in each tool to work with the canvas, that auto calls the method from
 *    a selected object of this type.
 *
 *  - toolName String can be used for testing and debugging purposes
 *  Upper canvas will catch all events, in the Controller, don't use event.src()
 *  instad use the singleton controller *DrawCanvasController.getDrawCanvas()*
 */
public abstract class Tool implements Drawable {
    private final String toolName;
    EventHandler<MouseEvent> onMousePressed;
    EventHandler<MouseEvent> onMouseReleased;
    EventHandler<MouseEvent> onMouseDragged;

    Tool(String toolName){
        this.toolName = toolName;
    }

    public String getToolName() {
        return toolName;
    }

    @Override
    public EventHandler<MouseEvent> getOnMousePressed() {
        return onMousePressed;
    }

    @Override
    public EventHandler<MouseEvent> getOnMouseDragged() {
        return onMouseDragged;
    }

    @Override
    public EventHandler<MouseEvent> getOnMouseReleased() {
        return onMouseReleased;
    }

    @Override
    public void setOnMousePressed(EventHandler<MouseEvent> onMousePressed){
        this.onMousePressed = onMousePressed;
    }

    @Override
    public void setOnMouseDragged(EventHandler<MouseEvent> onMouseDragged){
        this.onMouseDragged = onMouseDragged;
    }

    @Override
    public void setOnMouseReleased(EventHandler<MouseEvent> onMouseReleased){
        this.onMouseReleased = onMouseReleased;
    }
}
