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

    /**
     * The current Locale
     */
    @Getter
    private Locale locale;

    /**
     * General messages about the command lines 
     */
    @Getter
    private ResourceBundle generalCmdBundle;
    /**
     * Messages about the zoo
     */
    @Getter
    private ResourceBundle zooBundle;
     /**
     * Messages about a paddock
     */
    @Getter
    private ResourceBundle paddockBundle;
     /**
     * Messages about an animal
     */
    @Getter
    private ResourceBundle animalBundle;
     /**
     * Messages about a specie
     */
    @Getter
    private ResourceBundle specieBundle;
    @Getter
    private ResourceBundle reproductionBundle;
    @Getter
    private ResourceBundle lifespanBundle;
    @Getter
    private ResourceBundle socialBundle;
    @Getter
    private ResourceBundle territoryBundle;
     /**
     * Messages about an animal keeper
     */
    @Getter
    private ResourceBundle keeperBundle;

    public Option() {
        locale = Locale.getDefault();
        this.updateBundles();
    }

    /**
     * Change the value of locale
     * @param str  the value of the new locale
     */
    public Option(String str) {
        if (str.equals("")) {
            str = "fr";
        }
        locale = new Locale(str);
        updateBundles();
    }

    /**
     * Update all the bundle with the locale
     */
    public void updateBundles() {
        this.zooBundle = ResourceBundle.getBundle("i18n.zoo.info", locale);
        this.generalCmdBundle = ResourceBundle.getBundle("i18n.info", locale);
        this.paddockBundle = ResourceBundle.getBundle("i18n.paddock.paddock", locale);
        this.animalBundle = ResourceBundle.getBundle("i18n.animal.animal", locale);
        this.specieBundle = ResourceBundle.getBundle("i18n.specie.specie", locale);
        this.reproductionBundle = ResourceBundle.getBundle("i18n.animal.reproduction.reproduction", locale);
        this.lifespanBundle = ResourceBundle.getBundle("i18n.animal.lifespan.lifespan", locale);
        this.socialBundle = ResourceBundle.getBundle("i18n.animal.social.social", locale);
        this.territoryBundle = ResourceBundle.getBundle("i18n.animal.territory.territory", locale);
        this.keeperBundle = ResourceBundle.getBundle("i18n.animalKeeper.keeper", locale);
    }

    /**
     * Change the language
     * @param lang  the parameter to update the Locale
     */
    public void setLanguage(String lang) {
        switch (lang) {
            case "en":
            case "":
                locale = new Locale("");
                break;
            case "fr":
                locale = new Locale("fr");
                break;
        }
        updateBundles();
    }

    /**
     * Get the value of the Locale
     * @param friend the private class used to save all the informations about a zoo
     * @return the value of the language of the Locale
     */
    public String getLocale(SaveImpl.FriendSave friend) {
        friend.hashCode();
        return this.locale.getLanguage();
    }
}
