package ch.supsi.pss.sketch;

import ch.supsi.pss.misc.PreferencesRepository;
import javafx.scene.image.Image;

import java.io.*;
import java.util.*;

public class SketchReader {
    private static Map<Image, Set<String>> sketches = new HashMap<>();
    private static SketchReader instance;

    public SketchReader() {}

    public static SketchReader getInstance(){
        if(instance == null)
            instance = new SketchReader();
        return instance;
    }

    public void refreshSketches(){
        File drawsFolder = new File(PreferencesRepository.getDrawsPath());

        File[] listOfFiles = drawsFolder.listFiles();

        for (File file : listOfFiles) {
            if (file.isFile()) {
                String path = file.toString();
                Image image = new Image("file:" + path);
                String uuid = path.replace( PreferencesRepository.getDrawsPath(), "").replace(".png", "");
                sketches.put(image, getTags(uuid));

                sketches.get(image).forEach(System.out::println);
            }
        }
    }

    private Set<String> getTags(String uuid){
        File metaFolder = new File(PreferencesRepository.getMetadataPath());
        File[] listOfFiles = metaFolder.listFiles();

        Set<String> tags = new HashSet<>();

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


    public Set<Image> getImages(){
        return sketches.keySet();
    }

    public Set<String> getTags(Image image){
        return sketches.get(image);
    }

    public Map<Image, Set<String>> getSketches() {
        return sketches;
    }
}
