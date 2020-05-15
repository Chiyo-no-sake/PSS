package ch.supsi.pss;

import java.util.Locale;
import java.util.ResourceBundle;

public class LanguageController {
    private static LanguageController istance;

    private Locale locale;
    private ResourceBundle resourceBundle;

    private LanguageController(){
        locale = new Locale(PreferencesRepository.getLanguage());
        resourceBundle = ResourceBundle.getBundle("myProps", locale);
    }

    public static LanguageController getIstance() {
        if(istance == null)
            istance = new LanguageController();

        return istance;
    }

    public String getString(final String element){
        return resourceBundle.getString(element);
    }


    public Locale getLocale() {
        return locale;
    }
}
