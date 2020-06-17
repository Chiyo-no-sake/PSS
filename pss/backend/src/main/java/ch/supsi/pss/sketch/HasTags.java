package ch.supsi.pss.sketch;

import java.util.Collection;
import java.util.Set;

public interface HasTags {
    public void setTags(Collection<String> tags);
    public Set<String> getTags();
}
