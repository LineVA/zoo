package zoo.animal.death;

import lombok.Getter;
import zoo.Statistics.Gaussian;

/**
 *
 * @author doyenm
 */
public class GaussianLifeSpanAttributes {

    @Getter
    private final Gaussian femaleLifeSpan;
    @Getter
    private final Gaussian maleLifeSpan;

    public GaussianLifeSpanAttributes(LifeSpanAttributes average) {
        this.femaleLifeSpan = new Gaussian(average.getFemaleLifeSpan(), average.getFemaleLifeSpan() / 10.0);
        this.maleLifeSpan = new Gaussian(average.getMaleLifeSpan(), average.getMaleLifeSpan() / 10.0);
    }
}
