package ch.supsi.pss;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;

import javax.imageio.ImageIO;

import java.awt.image.RenderedImage;
import java.io.File;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;

public class SketchService{

    private static final int WIDTH = 1366;
    private static final int HEIGHT = 768;

    private boolean saved = false;

    public boolean isSaved() {
        return saved;
    }

    private final Set<String> tags = new TreeSet<>();
    private Canvas sketch;

    public SketchService(final Canvas sketch) {
        this.sketch = sketch;
    }

    public boolean saveSketch(){
        String uuid = UUID.randomUUID().toString();
        if(saveDraw(uuid) && saveMetadata(uuid))
            return true;

        return false;
    }

    private boolean saveDraw(final String uuid){
        File file = new File(PreferencesRepository.getDrawsPath() + "/" + uuid );
        try {
            WritableImage writableImage = new WritableImage(WIDTH, HEIGHT);
            sketch.snapshot(null, writableImage);

            RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage,null);
            ImageIO.write(renderedImage,"png",file);

            return true ;

        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    private boolean saveMetadata(final String uuid){
        File file = new File(PreferencesRepository.getMetadataPath() + "/" + uuid );
        try {
            // TODO: 12/05/20 Stampare su file lista tags

            return true ;

        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean addTag(String tag){
        return tags.add(tag);
    }

    public Set<String> getTags() {
        return tags;
    }
}