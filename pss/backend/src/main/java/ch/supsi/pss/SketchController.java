package ch.supsi.pss;


import javafx.scene.canvas.Canvas;

import java.util.UUID;

public class SketchController {

    private SketchService sketchService;
    private String uuid;

    public SketchController(final Canvas sketch) {
        uuid = UUID.randomUUID().toString();
        sketchService = new SketchService(sketch, uuid);
    }

    public void saveSketch(){
        sketchService.saveSketch();
    }

    public void updateSketch(final Canvas sketch){
        sketchService = new SketchService(sketch, uuid);
    }

    public SketchService getSketchService() {
        return sketchService;
    }
}
