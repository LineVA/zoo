package zoo.animal.specie;

import lombok.Getter;
import zoo.animal.Names;
import zoo.animal.conservation.ConservationStatus;
import zoo.animal.death.GaussianLifeSpanAttributes;
import zoo.animal.death.LifeSpanAttributes;
import zoo.animal.feeding.FeedingAttributes;
import zoo.animal.feeding.GaussianFeedingAttributes;
import zoo.animal.reproduction.GaussianReproductionAttributes;
import zoo.animal.reproduction.ReproductionAttributes;
import zoo.animal.social.GaussianSocialAttributes;
import zoo.animal.social.SocialAttributes;
import zoo.paddock.GaussianTerritoryAttributes;
import zoo.paddock.TerritoryAttributes;
import zoo.paddock.biome.BiomeAttributes;
import zoo.paddock.biome.GaussianBiomeAttributes;

/**
 *
 * @author doyenm
 */
public class LightSpecie {

    @Getter
    private final int diet;
    @Getter
    private final int family;
    @Getter
    private final Names names;
    @Getter
    private final int ecoregion;
    @Getter
    private final int conservation;
    @Getter
    private final int biome;
    @Getter
    private final int size;

    public LightSpecie(Names names,
            int diet, int conservation,
            int ecoregion, int family, int biome, int size) {
        this.names = names;
        this.diet = diet;
        this.family = family;
        this.ecoregion = ecoregion;
        this.biome = biome;
        this.size = size;
        this.conservation = conservation;
    }
}
