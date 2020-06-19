package ch.supsi.pss.sketch;

import ch.supsi.pss.misc.RepositoryController;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ReadService {
    private static final Map<byte[], Set<String>> sketches = new HashMap<>();

    public static boolean refreshSketches(){
        sketches.clear();

        File drawsFolder = null;
        try {
             drawsFolder = new File(RepositoryController.getInstance().getDrawsPath());
        }catch (Exception e){
            return false;
        }

        File[] listOfFiles = drawsFolder.listFiles();

        if(listOfFiles != null)
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    String path = file.toString();
                    String uuid = path.replace( RepositoryController.getInstance().getDrawsPath(), "").replace(".png", "");

                    try{
                        byte[] bytes = Files.readAllBytes(Paths.get(path));

                        Set<String> tags = getTags(uuid);

                        if(!tags.isEmpty()) {
                            sketches.put(bytes, tags);
                        }else
                            System.out.println("Can't find metadata for draw: " + uuid);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        return true;
    }

    private static Set<String> getTags(String uuid){
        File metaFolder = new File(RepositoryController.getInstance().getMetaPath());
        File[] listOfFiles = metaFolder.listFiles();

        Set<String> tags = new HashSet<>();

        if(listOfFiles != null)
            for (File file : listOfFiles){
                if (file.isFile() && file.getAbsolutePath().contains(uuid))
                    try(BufferedReader br = new BufferedReader(new FileReader(file))){
                        String line = br.readLine();
                        while (line != null){
                            tags.add(line);
                            line = br.readLine();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }

        return tags;
    }

    public static Map<byte[], Set<String>> getSketches() {
        return sketches;
    }
}
