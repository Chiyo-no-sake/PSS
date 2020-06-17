package ch.supsi.pss.sketch;

import java.time.LocalDate;
import java.util.*;

public class SketchController implements HasUUID, HasBytes, HasTags, Savable {

    private String uuid;
    private byte[] bytes;
    private Set<String> tags;
    private static boolean bAlreadySaved;

    public SketchController(final byte[] bytes, Collection<String> tags) {
        setBytes(bytes);
        setUUID(UUID.randomUUID().toString());
        setTags(tags);
        this.tags.add(LocalDate.now().toString());
        bAlreadySaved = false;
    }

    @Override
    public void setUUID(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String getUUID() {
        return uuid;
    }

    @Override
    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    @Override
    public byte[] getBytes() {
        return bytes;
    }

    @Override
    public void setTags(Collection<String> tags) {
        this.tags = new TreeSet<>();
        this.tags.addAll(tags);
    }

    @Override
    public Set<String> getTags() {
        return tags;
    }

    public boolean addTag(String tag){
        return tags.add(tag);
    }

    @Override
    public boolean save() {
        if(SaveService.saveSketch(bytes,tags,uuid)) {
            bAlreadySaved = true;
            return true;
        }
        return false;
    }

    public static boolean isAlreadySaved() {
        return bAlreadySaved;
    }

    public static void setAlreadySaved(boolean bAlreadySaved) {
        SketchController.bAlreadySaved = bAlreadySaved;
    }

    public String getTagsAsString(){
        StringBuilder sb = new StringBuilder();

        tags.forEach(t->{
            sb.append(t);
            sb.append("\n");
        });

        return sb.toString();
    }

    public static void refresh() {
        ReadService.refreshSketches();
    }

    public static Map<byte[], Set<String>> getAllSketches() {
        return ReadService.getSketches();
    }
}
