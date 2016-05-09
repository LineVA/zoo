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
    private int gestationTime;
    @Getter
    private int litterSize;
    
    public ReproductionAttributes(int female, int male, int gestation, int litter){
        this.femaleMaturityAge = female;
        this.maleMaturityAge = male;
        this.gestationTime = gestation;
        this.litterSize = litter;
    }
}
