package zoo.animal;

import zoo.animal.feeding.FeedingAttributes;
import lombok.Getter;
import zoo.animal.social.SocialAttributes;
import zoo.paddock.TerritoryAttributes;
import zoo.paddock.biome.BiomeAttributes;

/**
 *
 * @author doyenm
 */
public class AnimalsAttributes implements Cloneable {

    @Getter
    private final BiomeAttributes optimalBiome;

    @Getter
    private final FeedingAttributes optimalFeeding;

    @Getter
    private final FeedingAttributes actualFeeding;

    @Getter
    private final int actualDiet;

    @Getter
    private final SocialAttributes optimalSocial;

    @Getter
    private final TerritoryAttributes optimalTerritory;

    public AnimalsAttributes(BiomeAttributes biome, FeedingAttributes optimalFeeding,
            FeedingAttributes actualFeeding, int diet, SocialAttributes social, TerritoryAttributes territory) {
        this.optimalBiome = biome;
        this.optimalFeeding = optimalFeeding;
        this.actualFeeding = actualFeeding;
        this.optimalSocial = social;
        this.optimalTerritory = territory;
        this.actualDiet = diet;
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
