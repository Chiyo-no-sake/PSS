package ch.supsi.pss;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.WritableImage;

import javax.imageio.ImageIO;

import java.awt.image.RenderedImage;
import java.io.File;
import java.util.Set;
import java.util.TreeSet;

public class SketchService{

    private static final int WIDTH = 1366;
    private static final int HEIGHT = 768;

    private String uuid ;

    public SketchService(String uuid) {
        this.uuid = uuid;
    }

    public boolean saveSketch(final Canvas sketch){
        if(saveDraw(uuid, sketch) && saveMetadata(uuid))
            return true;

        return false;
    }

    private boolean saveDraw(final String uuid, final Canvas sketch){
        File file = new File(PreferencesRepository.getDrawsPath() + File.separator + uuid + ".png" );
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
        File file = new File(PreferencesRepository.getMetadataPath() + File.separator + uuid );
        try {
            // TODO: 12/05/20 Stampare su file lista tags

            return true ;

        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

}