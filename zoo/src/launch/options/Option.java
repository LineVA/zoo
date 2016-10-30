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
    private Locale locale;

    @Getter
    private ResourceBundle generalCmdBundle;
    @Getter
    private ResourceBundle zooBundle;
    @Getter
    private ResourceBundle paddockBundle;
    @Getter
    private ResourceBundle animalBundle;
    @Getter
    private ResourceBundle specieBundle;
    @Getter
    private ResourceBundle dietBundle;
    @Getter
    private ResourceBundle reproductionBundle;
    @Getter
    private ResourceBundle lifespanBundle;
    @Getter
    private ResourceBundle socialBundle;
    @Getter
    private ResourceBundle territoryBundle;
    @Getter
    private ResourceBundle conservationBundle;
    @Getter
    private ResourceBundle keeperBundle;

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
        this.reproductionBundle = ResourceBundle.getBundle("i18n.animal.reproduction.reproduction", locale);
        this.lifespanBundle = ResourceBundle.getBundle("i18n.animal.lifespan.lifespan", locale);
        this.socialBundle = ResourceBundle.getBundle("i18n.animal.social.social", locale);
        this.territoryBundle = ResourceBundle.getBundle("i18n.animal.territory.territory", locale);
        this.conservationBundle = ResourceBundle.getBundle("i18n.animal.conservation.conservation", locale);
        this.keeperBundle = ResourceBundle.getBundle("i18n.animalKeeper.keeper", locale);
    }

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
