package ch.supsi.pss.sketch;

import ch.supsi.pss.misc.PreferencesRepository;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Collection;

public class SketchService {
    private String uuid;

    public SketchService(String uuid) {
        this.uuid = uuid;
    }

    public boolean saveSketch(final byte[]sketch, final Collection<String> tags) {
        if (saveDraw(uuid, sketch) && saveMetadata(uuid, tags))
            return true;

        return false;
    }

    private boolean saveDraw(final String uuid, final byte[] sketch) {
        File file = new File(PreferencesRepository.getDrawsPath() + File.separator + uuid + ".png");
        try (FileOutputStream stream = new FileOutputStream(file)){
            stream.write(sketch);
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