package zoo.animal;

import zoo.animal.feeding.FeedingAttributes;
import lombok.Getter;
import zoo.animal.reproduction.ReproductionAttributes;
import zoo.paddock.biome.BiomeAttributes;

/**
 *
 * @author doyenm
 */
public class AnimalsAttributes {

    @Getter
    private BiomeAttributes biomeAttributes;
    
    @Getter 
    private FeedingAttributes feedinAttributes;
    
    @Getter
    private ReproductionAttributes reproductionAttributes;
    
    public AnimalsAttributes(BiomeAttributes biome, FeedingAttributes feeding,
            ReproductionAttributes reproduction){
        this.biomeAttributes = biome;
        this.feedinAttributes = feeding;
        this.reproductionAttributes = reproduction;
    }
}
