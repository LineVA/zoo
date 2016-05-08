package zoo.animal;

import zoo.animal.feeding.FeedingAttributes;
import lombok.Getter;
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
    
    public AnimalsAttributes(BiomeAttributes biome, FeedingAttributes feeding){
        this.biomeAttributes = biome;
        this.feedinAttributes = feeding;
    }
}
