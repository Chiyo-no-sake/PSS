package ch.supsi.pss.misc;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class PomInformations {

    private static MavenXpp3Reader reader = new MavenXpp3Reader();
    private static Model model = null;

    public static Model getInfoFromPom() {

        try(FileReader fr = new FileReader(System.getProperty("user.dir") + File.separator + "pss" + File.separator + "backend" + File.separator + "pom.xml")){
            model = reader.read(fr);
        }catch (XmlPullParserException | IOException xmlPullParserException) {
            xmlPullParserException.printStackTrace();
        }
        return model;
    }
}