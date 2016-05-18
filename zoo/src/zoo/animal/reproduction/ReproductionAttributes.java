package zoo.animal.reproduction;

import lombok.Getter;

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

    @Override
    public String toString() {
        String info = "";
        info += "age of sexual maturity (female) = " + this.femaleMaturityAge + ", ";
        info += "age of sexual maturity (male) = " + this.maleMaturityAge + ", ";
        info += "gestation frequency = " + this.gestationFrequency +", ";
        info += "size of the litter = " + this.litterSize;

        return info;
    }
}
