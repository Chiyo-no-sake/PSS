package ch.supsi.pss.sketch;

import ch.supsi.pss.misc.PreferencesRepository;
import javafx.scene.image.Image;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SketchReader {
    private static final Map<Image, Set<String>> sketches = new HashMap<>();
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
                    Image image = new Image("file:" + path);
                    String uuid = path.replace( PreferencesRepository.getDrawsPath(), "").replace(".png", "");

                    Set<String> tags = getTags(uuid);

                    if(!tags.isEmpty()) {
                        sketches.put(image, tags);
                    }else{
                        System.out.println("Can't find metadata for draw: " + uuid);
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

    public Set<Image> searchByTag(String tag){

        Set<Image> imageSet = new HashSet<>();
        for (Image image : sketches.keySet())
            if (sketches.get(image).contains(tag))
                imageSet.add(image);

        return imageSet;
    }

    public Set<Image> searchByTags(Set<String> tags){

        Set<Image> imageSet = new HashSet<>();
        for (Image image : sketches.keySet())
            for (String tag : tags)
                if(sketches.get(image).contains(tag))
                    imageSet.add(image);

        return imageSet;
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
