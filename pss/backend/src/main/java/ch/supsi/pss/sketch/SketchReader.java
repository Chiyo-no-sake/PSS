package ch.supsi.pss.sketch;

import ch.supsi.pss.misc.PreferencesRepository;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SketchReader {
    private static final Map<byte[], Set<String>> sketches = new HashMap<>();
    private static SketchReader instance;

    public SketchReader() {}

    public static SketchReader getInstance(){
        if(instance == null)
            instance = new SketchReader();
        return instance;
    }

    public void refreshSketches(){
        sketches.clear();

        File drawsFolder = new File(PreferencesRepository.getDrawsPath());

        File[] listOfFiles = drawsFolder.listFiles();

        if(listOfFiles != null)
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    String path = file.toString();
                    String uuid = path.replace( PreferencesRepository.getDrawsPath(), "").replace(".png", "");

                    try{
                        byte[] bytes = Files.readAllBytes(Paths.get(path));

                        Set<String> tags = getTags(uuid);

                        if(!tags.isEmpty()) {
                            sketches.put(bytes, tags);
                        }else{
                            System.out.println("Can't find metadata for draw: " + uuid);
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
    }

    private Set<String> getTags(String uuid){
        File metaFolder = new File(PreferencesRepository.getMetadataPath());
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

    public Map<byte[], Set<String>> getSketches() {
        return sketches;
    }
}
