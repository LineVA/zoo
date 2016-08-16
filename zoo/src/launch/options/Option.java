package launch.options;

import java.util.Locale;
import java.util.ResourceBundle;
import lombok.Getter;

/**
 *
 * @author doyenm
 */
public class Option {

    Locale locale;

    @Getter
    ResourceBundle generalCmdBundle;
    @Getter
    ResourceBundle zooBundle;
    @Getter
    ResourceBundle paddockBundle;
    @Getter
    ResourceBundle animalBundle;
    @Getter
    ResourceBundle specieBundle;

    public Option() {
        locale = Locale.getDefault();
        this.generalCmdBundle = ResourceBundle.getBundle("i18n.info");
        this.zooBundle = ResourceBundle.getBundle("i18n.zoo.info");
    }

    public void updateBundles() {
        this.zooBundle = ResourceBundle.getBundle("i18n.zoo.info", locale);
        this.generalCmdBundle = ResourceBundle.getBundle("i18n.info", locale);
        this.paddockBundle = ResourceBundle.getBundle("i18n.paddock.paddock", locale);
        this.animalBundle = ResourceBundle.getBundle("i18n.animal.animal", locale);
        this.specieBundle = ResourceBundle.getBundle("i18n.specie.specie", locale);
    }

    public void setLanguage(String lang) {
        if (lang.equals("en")) {
            locale = new Locale("");
        } else if (lang.equals("fr")) {
            locale = new Locale("fr");
        }
        updateBundles();
    }

}
