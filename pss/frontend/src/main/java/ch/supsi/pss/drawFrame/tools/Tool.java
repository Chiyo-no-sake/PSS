package ch.supsi.pss.drawFrame.tools;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

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
