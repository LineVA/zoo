package launch.options;

import backup.save.SaveImpl;
import java.util.Locale;
import java.util.ResourceBundle;
import lombok.Getter;

/**
 *
 * @author doyenm
 */
public class Option {

    @Getter
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
    @Getter
    ResourceBundle dietBundle;

    public Option() {
        locale = Locale.getDefault();
        this.updateBundles();
    }

    public Option(String str) {
        if (str.equals("")) {
            str = "fr";
        }
        locale = new Locale(str);
        updateBundles();
    }

    public void updateBundles() {
        this.zooBundle = ResourceBundle.getBundle("i18n.zoo.info", locale);
        this.generalCmdBundle = ResourceBundle.getBundle("i18n.info", locale);
        this.paddockBundle = ResourceBundle.getBundle("i18n.paddock.paddock", locale);
        this.animalBundle = ResourceBundle.getBundle("i18n.animal.animal", locale);
        this.specieBundle = ResourceBundle.getBundle("i18n.specie.specie", locale);
        this.dietBundle = ResourceBundle.getBundle("i18n.animal.feeding.diet", locale);
    }

    public void setLanguage(String lang) {
        if (lang.equals("en") || lang.equals("")) {
            locale = new Locale("");
        } else if (lang.equals("fr")) {
            locale = new Locale("fr");
        }
        updateBundles();
    }

    /**
     * For save
     *
     * @param friend
     * @return
     */
    public String getLocale(SaveImpl.FriendSave friend) {
        friend.hashCode();
        return this.locale.getLanguage();
    }
}
