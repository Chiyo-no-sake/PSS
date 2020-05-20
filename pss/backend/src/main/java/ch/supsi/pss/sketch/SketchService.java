package ch.supsi.pss.sketch;

import ch.supsi.pss.misc.PreferencesRepository;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SketchService {

    private static final int WIDTH = 1366;
    private static final int HEIGHT = 768;

    private String uuid;

    public SketchService(String uuid) {
        this.uuid = uuid;
    }

    public boolean saveSketch(final Canvas sketch, final Collection<String> tags) {
        if (saveDraw(uuid, sketch) && saveMetadata(uuid, tags))
            return true;

        return false;
    }

    private boolean saveDraw(final String uuid, final Canvas sketch) {
        File file = new File(PreferencesRepository.getDrawsPath() + File.separator + uuid + ".png");
        try {
            WritableImage writableImage = new WritableImage(WIDTH, HEIGHT);
            sketch.snapshot(null, writableImage);

            RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
            ImageIO.write(renderedImage, "png", file);

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    private boolean saveMetadata(final String uuid, final Collection<String> tags) {
        File file = new File(PreferencesRepository.getMetadataPath() + File.separator + uuid);
        try {
            PrintWriter out = new PrintWriter(new FileWriter(file));

            for (String t :
                    tags) {
                System.out.println("writing " +t );
                out.println(t);
            }
            out.close();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

}