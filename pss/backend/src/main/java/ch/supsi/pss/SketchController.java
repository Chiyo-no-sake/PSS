package ch.supsi.pss;


import javafx.scene.canvas.Canvas;

import java.util.UUID;

public class SketchController {

    private SketchService sketchService;
    private String uuid;
    private Canvas sketch;

    public Canvas getSketch() {
        return sketch;
    }

    public void setSketch(Canvas skecth) {
        this.sketch = skecth;
    }

    public SketchController() {
        uuid = UUID.randomUUID().toString();
        sketchService = new SketchService(uuid);
    }

    public SketchController(final Canvas sketch) {
        uuid = UUID.randomUUID().toString();
        this.sketch = sketch;
        //sketchService = new SketchService(sketch, uuid);
    }

    public void newUUID(){
        uuid = UUID.randomUUID().toString();
    }

    public boolean saveSketch(){
        return sketchService.saveSketch(sketch);
    }

    public void setSketchService(SketchService sketchService) {
        this.sketchService = sketchService;
    }

    public SketchService getSketchService() {
        return sketchService;
    }
}
