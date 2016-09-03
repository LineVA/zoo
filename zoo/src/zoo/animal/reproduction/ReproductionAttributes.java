package zoo.animal.reproduction;

import java.util.ResourceBundle;
import launch.options.Option;
import lombok.Getter;
import lombok.Setter;

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

    public ReproductionAttributes(int female, int male, double frequency, int litter) {
        this.femaleMaturityAge = female;
        this.maleMaturityAge = male;
        this.gestationFrequency = frequency;
        this.litterSize = litter;
    }

    public String toStringByLanguage(Option option) {
        String info = "";
        ResourceBundle bundle = option.getReproductionBundle();
        info += bundle.getString("AGE_FEMALE") + this.femaleMaturityAge + ", ";
        info += bundle.getString("AGE_MALE") + this.maleMaturityAge + ", ";
        info += bundle.getString("GESTATION_FREQUENCY") + this.gestationFrequency + ", ";
        info += bundle.getString("LITTER_SIZE") + this.litterSize;
        return info;
    }
}
