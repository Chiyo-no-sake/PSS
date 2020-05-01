package ch.supsi.pss.drawFrame.tools;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 * abstract class, used to create other tools
 *  - listeners need to be implemented in each tool to work with the canvas, that auto calls the method from
 *    a selected object of this type.
 *
 *  - toolName String can be used for testing and debugging purposes
 */
public abstract class Tool {
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

    public EventHandler<MouseEvent> getOnMousePressed() {
        return onMousePressed;
    }

    abstract void setOnMousePressed();

    public EventHandler<MouseEvent> getOnMouseReleased() {
        return onMouseReleased;
    }

    abstract void setOnMouseReleased();

    public EventHandler<MouseEvent> getOnMouseDragged() {
        return onMouseDragged;
    }

    abstract void setOnMouseDragged();
}
