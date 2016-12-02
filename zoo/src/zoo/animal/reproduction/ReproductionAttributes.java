package zoo.animal.reproduction;

import java.util.ResourceBundle;
import launch.options.Option;
import lombok.Getter;
import utils.Utils;

/**
 *
 * @author doyenm
 */
public class ReproductionAttributes {

    @Getter
    private final int femaleMaturityAge;
    @Getter
    private final int maleMaturityAge;
    @Getter
    private final double gestationFrequency;
    @Getter
    private final int litterSize;
    @Getter
    private final int gestationDuration;

    public ReproductionAttributes(int female, int male, double frequency, int litter, int duration) {
        this.femaleMaturityAge = female;
        this.maleMaturityAge = male;
        this.gestationFrequency = frequency;
        this.litterSize = litter;
        this.gestationDuration = duration;
    }

    public String toStringByLanguage(Option option) {
        String info = "";
        ResourceBundle bundle = option.getAnimalBundle();
        info += bundle.getString("REPRODUCTION.AGE_FEMALE") + this.femaleMaturityAge + ", ";
        info += bundle.getString("REPRODUCTION.AGE_MALE") + this.maleMaturityAge + ", ";
        info += bundle.getString("REPRODUCTION.GESTATION_FREQUENCY") + Utils.truncate(this.gestationFrequency) + ", ";
        info += bundle.getString("REPRODUCTION.LITTER_SIZE") + this.litterSize;
        return info;
    }

    public String femaleToStringByLanguage(Option option) {
        String info = "";
       ResourceBundle bundle = option.getAnimalBundle();
        info += bundle.getString("REPRODUCTION.AGE_FEMALE") + this.femaleMaturityAge + ", ";
        info += bundle.getString("REPRODUCTION.GESTATION_FREQUENCY") + Utils.truncate(this.gestationFrequency) + ", ";
        info += bundle.getString("REPRODUCTION.LITTER_SIZE") + this.litterSize;
        return info;
    }

    public String maleToStringByLanguage(Option option) {
        String info = "";
        ResourceBundle bundle = option.getAnimalBundle();
        info += bundle.getString("REPRODUCTION.AGE_MALE") + this.maleMaturityAge + ", ";
        return info;
    }
}
