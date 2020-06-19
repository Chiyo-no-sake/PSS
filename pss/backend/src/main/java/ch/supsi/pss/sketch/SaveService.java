package ch.supsi.pss.sketch;

import ch.supsi.pss.misc.RepositoryController;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Collection;

public class SaveService {

    public static boolean saveSketch(final byte[]sketch, final Collection<String> tags, String uuid) {
        return saveDraw(uuid, sketch) && saveMetadata(uuid, tags);
    }

    private static boolean saveDraw(final String uuid, final byte[] sketch) {
        File file = new File(RepositoryController.getInstance().getDrawsPath() + File.separator + uuid + ".png");
        try (FileOutputStream stream = new FileOutputStream(file)){
            stream.write(sketch);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    private static boolean saveMetadata(final String uuid, final Collection<String> tags) {
        File file = new File(RepositoryController.getInstance().getMetaPath() + File.separator + uuid);
        try {
            PrintWriter out = new PrintWriter(new FileWriter(file));

            for (String t : tags) {
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