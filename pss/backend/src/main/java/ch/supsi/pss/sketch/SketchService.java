package ch.supsi.pss.sketch;

import ch.supsi.pss.misc.PreferencesRepository;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Collection;

public class SketchService {
    private String uuid;

    public SketchService(String uuid) {
        this.uuid = uuid;
    }

    public boolean saveSketch(final Image sketch, final Collection<String> tags) {
        if (saveDraw(uuid, sketch) && saveMetadata(uuid, tags))
            return true;

        return false;
    }

    private boolean saveDraw(final String uuid, final Image sketch) {
        File file = new File(PreferencesRepository.getDrawsPath() + File.separator + uuid + ".png");
        try {
            ImageIO.write((RenderedImage) sketch, "png", file);
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