package ch.supsi.pss;

import javafx.scene.layout.BorderPane;

public class SketchController {

    private SketchService sketchService;

    public void newSketch(final BorderPane sketch){
        sketchService = new SketchService(sketch);
    }

    public void updateSketch(final BorderPane sketch){

    }

    public SketchService getSketchService() {
        return sketchService;
    }
}
