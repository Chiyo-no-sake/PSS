package ch.supsi.pss;


import javafx.scene.canvas.Canvas;

public class SketchController {

    private SketchService sketchService;

    public void newSketch(final Canvas sketch){
        sketchService = new SketchService(sketch);
    }

    public void updateSketch(final Canvas sketch){

    }

    public SketchService getSketchService() {
        return sketchService;
    }
}
