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
    @Getter
    ResourceBundle reproductionBundle;
    @Getter
    ResourceBundle lifespanBundle;
    @Getter
    ResourceBundle socialBundle;
    @Getter
    ResourceBundle territoryBundle;
    @Getter
    ResourceBundle conservationBundle;
    @Getter
    ResourceBundle ecoregionBundle;
    @Getter
    ResourceBundle familyBundle;
    @Getter
    ResourceBundle sizeBundle;
    @Getter
    ResourceBundle continentBundle;
    @Getter
    ResourceBundle keeperBundle;

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
        this.ecoregionBundle = ResourceBundle.getBundle("i18n.paddock.ecoregion", locale);
        this.familyBundle = ResourceBundle.getBundle("i18n.specie.family", locale);
        this.sizeBundle = ResourceBundle.getBundle("i18n.animal.feeding.size", locale);
        this.continentBundle = ResourceBundle.getBundle("i18n.paddock.continent", locale);
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
