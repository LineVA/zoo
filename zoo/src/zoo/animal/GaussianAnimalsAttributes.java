package zoo.animal;

import zoo.paddock.biome.GaussianBiomeAttributes;
import lombok.Getter;
import zoo.animal.feeding.GaussianFeedingAttributes;

/**
 *
 * @author doyenm
 */
public class GaussianAnimalsAttributes {

    @Getter
    private GaussianBiomeAttributes biome;
    
    @Getter
    private GaussianFeedingAttributes feeding;
    
    public GaussianAnimalsAttributes(GaussianBiomeAttributes biome, GaussianFeedingAttributes feeding){
        this.biome = biome;
        this.feeding = feeding;
    }
    
}
