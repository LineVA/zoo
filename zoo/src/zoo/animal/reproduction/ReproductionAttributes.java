package zoo.animal.reproduction;

import exception.IncorrectDataException;
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

    public String toStringByLanguage(Option option) throws IncorrectDataException {
        String info = "";
        ResourceBundle bundle = option.getAnimalBundle();
        info += bundle.getString("REPRODUCTION.AGE_FEMALE") + Utils.infoAge(this.femaleMaturityAge, bundle) + ", ";
        info += bundle.getString("REPRODUCTION.AGE_MALE") + Utils.infoAge(this.maleMaturityAge, bundle) + ", ";
        info += bundle.getString("REPRODUCTION.GESTATION_FREQUENCY") + Utils.truncate(this.gestationFrequency) + ", ";
        info += bundle.getString("REPRODUCTION.GESTATION_DURATION") + Utils.infoAge(this.gestationDuration, bundle)+ ", ";
        info += bundle.getString("REPRODUCTION.LITTER_SIZE") + this.litterSize;
        return info;
    }

//    abstract public String femaleToStringByLanguage(Option option) throws IncorrectDataException;

    public String maleToStringByLanguage(Option option) throws IncorrectDataException {
        String info = "";
        ResourceBundle bundle = option.getAnimalBundle();
        info += bundle.getString("REPRODUCTION.AGE_MALE") + Utils.infoAge(this.maleMaturityAge, bundle)+ ", ";
        return info;
    }
}
