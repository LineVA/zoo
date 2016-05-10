package zoo.animal;

import lombok.Getter;
import zoo.animal.feeding.FeedingAttributes;
import zoo.animal.reproduction.ReproductionAttributes;
import zoo.paddock.biome.BiomeAttributes;

/**
 *
 * @author doyenm
 */
public class AnimalsLightAttributes {

    @Getter
    private BiomeAttributes biomeAttributes;

    @Getter
    private FeedingAttributes feedinAttributes;

    public AnimalsLightAttributes(BiomeAttributes biome, FeedingAttributes feeding) {
        this.biomeAttributes = biome;
        this.feedinAttributes = feeding;
    }

    @Override
    public Object clone() {
        Object o = null;
        try {
            o = super.clone();
        } catch (CloneNotSupportedException cnse) {
            cnse.printStackTrace(System.err);
        }
        return o;
    }
}
