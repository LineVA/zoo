package zoo.animal.personality;

import launch.options.Option;
import lombok.Getter;
import utils.Utils;

/**
 *
 * @author doyenm
 */
public class PersonalityAttributes {

    @Getter
    Double bravery;
    @Getter
    Double intelligence;
    @Getter
    Double meticulousness;
    @Getter
    Double greed;
    @Getter
    Double curiosity;

    public PersonalityAttributes(Double bravery, Double intelligence, Double meticulousness,
            Double greed, Double curiosity) {
        this.bravery = bravery;
        this.intelligence = intelligence;
        this.meticulousness = meticulousness;
        this.greed = greed;
        this.curiosity = curiosity;
    }

    public String toStringByLanguage(Option option) {
        String toStr = "";
        toStr += option.getAnimalBundle().getString("BRAVERY") + Utils.truncate(this.bravery) + ", ";
        toStr += option.getAnimalBundle().getString("INTELLIGENCE") + Utils.truncate(this.intelligence) + ", ";
        toStr += option.getAnimalBundle().getString("METICULOUSNESS") + Utils.truncate(this.meticulousness) + ", ";
        toStr += option.getAnimalBundle().getString("GREED") + Utils.truncate(this.greed) + ", ";
        toStr += option.getAnimalBundle().getString("CURIOSITY") + Utils.truncate(this.curiosity);
        return toStr;
    }
}
