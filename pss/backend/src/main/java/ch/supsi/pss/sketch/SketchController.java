package ch.supsi.pss.sketch;


import javafx.scene.canvas.Canvas;

import java.util.*;

public class SketchController {

    private SketchService sketchService;

    private final String uuid;
    private final Canvas sketch;
    private final Set<String> tags;

    private boolean bAlreadySaved;

    public Canvas getSketch() {
        return sketch;
    }

    public SketchController(final Canvas sketch) {
        uuid = UUID.randomUUID().toString();
        sketchService = new SketchService(uuid);
        this.sketch = sketch;

        tags = new TreeSet<>();

        bAlreadySaved = false;
    }

    // to initialize some start tags
    public SketchController(final Canvas sketch, Collection<String> tags) {
        uuid = UUID.randomUUID().toString();
        sketchService = new SketchService(uuid);
        this.sketch = sketch;

        bAlreadySaved = false;

        this.tags = new TreeSet<>();
        this.tags.addAll(tags);
    }

    public boolean saveSketch(){
        if(sketchService.saveSketch(sketch, tags)) {
            bAlreadySaved = true;
            return true;
        }

        return false;
    }

    public void setSketchService(SketchService sketchService) {
        this.sketchService = sketchService;
    }

    public SketchService getSketchService() {
        return sketchService;
    }

    public boolean addTag(String tag){
        return tags.add(tag);
    }

    public Set<String> getTags() {
        return tags;
    }

    public boolean isAlreadySaved() {
        return bAlreadySaved;
    }

    public void setAlreadySaved(boolean bAlreadySaved) {
        this.bAlreadySaved = bAlreadySaved;
    }

    public String getTagsAsString(){
        StringBuilder sb = new StringBuilder();

        tags.forEach(t->{
            sb.append(t);
            sb.append("\n");
        });

        return sb.toString();
    }
}
