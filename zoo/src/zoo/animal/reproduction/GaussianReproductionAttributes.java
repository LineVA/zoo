package zoo.animal.reproduction;

import lombok.Getter;
import zoo.Statistics.Gaussian;

/**
 *
 * @author doyenm
 */
public class GaussianReproductionAttributes {

    @Getter
    private Gaussian femaleMaturityAge;
    @Getter
    private Gaussian maleMaturityAge;
    @Getter
    private Gaussian gestationTime;
    @Getter
    private Gaussian litterSize;

    public GaussianReproductionAttributes(ReproductionAttributes average, ReproductionAttributes sd) {
        this.femaleMaturityAge = new Gaussian(average.getFemaleMaturityAge(), sd.getFemaleMaturityAge());
        this.maleMaturityAge = new Gaussian(average.getMaleMaturityAge(), sd.getMaleMaturityAge());
        this.gestationTime = new Gaussian(average.getGestationFrequency(), sd.getGestationFrequency());
        this.litterSize = new Gaussian(average.getLitterSize(), sd.getLitterSize());
    }
}
