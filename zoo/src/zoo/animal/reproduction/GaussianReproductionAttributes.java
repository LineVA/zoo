package zoo.animal.reproduction;

import lombok.Getter;
import zoo.statistics.Gaussian;

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
    private Gaussian gestationFrequency;
    @Getter
    private Gaussian litterSize;
    @Getter
    private Gaussian gestationDuration;

    public GaussianReproductionAttributes(ReproductionAttributes average) {
        this.femaleMaturityAge = new Gaussian(average.getFemaleMaturityAge(), average.getFemaleMaturityAge()/10.0);
        this.maleMaturityAge = new Gaussian(average.getMaleMaturityAge(), average.getMaleMaturityAge()/10.0);
        this.gestationFrequency = new Gaussian(average.getGestationFrequency(), average.getGestationFrequency()/10.0);
        this.litterSize = new Gaussian(average.getLitterSize(), average.getLitterSize()/10.0);
        // Gestation duration is a constant
        this.gestationDuration = new Gaussian(average.getGestationDuration(), 0);
    }
}
