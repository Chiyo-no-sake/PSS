package ch.supsi.pss.sketch;
import java.time.LocalDate;
import java.util.*;

public class SketchController {

    private SketchService sketchService;

    private final String uuid;
    private byte[] sketch;
    private final Set<String> tags;

    private boolean bAlreadySaved;

    public void setSketch(byte[] sketch) {
        this.sketch = sketch;
    }

    public byte[] getSketch() {
        return sketch;
    }


    public SketchController(final byte[] sketch) {
        uuid = UUID.randomUUID().toString();
        sketchService = new SketchService(uuid);
        this.sketch = sketch;

        tags = new TreeSet<>();

        bAlreadySaved = false;
    }

    // to initialize some start tags
    public SketchController(final byte[] sketch, Collection<String> tags) {
        uuid = UUID.randomUUID().toString();
        sketchService = new SketchService(uuid);
        this.sketch = sketch;

        bAlreadySaved = false;

        this.tags = new TreeSet<>();
        this.tags.add(LocalDate.now().toString());
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
