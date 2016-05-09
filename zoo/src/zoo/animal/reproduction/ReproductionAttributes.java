package zoo.animal.reproduction;

import lombok.Getter;
import zoo.animal.Animal;

/**
 *
 * @author doyenm
 */
public class ReproductionAttributes {

    @Getter
    private int femaleMaturityAge;
    @Getter
    private int maleMaturityAge;
    @Getter
    private double gestationFrequency;
    @Getter
    private int litterSize;
    
    public ReproductionAttributes(int female, int male, double frequency, int litter){
        this.femaleMaturityAge = female;
        this.maleMaturityAge = male;
        this.gestationFrequency = frequency;
        this.litterSize = litter;
    }
}
