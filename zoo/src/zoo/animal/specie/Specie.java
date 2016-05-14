package zoo.animal.specie;

import lombok.Getter;
import zoo.animal.GaussianAnimalsAttributes;
import zoo.animal.Names;
import zoo.animal.feeding.FeedingAttributes;
import zoo.animal.reproduction.ReproductionAttributes;
import zoo.paddock.biome.BiomeAttributes;
import zoo.paddock.biome.GaussianBiomeAttributes;

/**
 *
 * @author doyenm
 */
public class Specie {

    @Getter
    private final BiomeAttributes biome;
    @Getter
    private final FeedingAttributes feeding;
    @Getter
    private final ReproductionAttributes reproduction;
    @Getter
    private final Names names;
    @Getter
    private GaussianAnimalsAttributes gaussiansAnimals;

    public Specie(Names names, BiomeAttributes biome, FeedingAttributes feeding,
            ReproductionAttributes repro) {
        this.names = names;
        this.biome = biome;
        this.feeding = feeding;
        this.reproduction = repro;
        this.gaussiansAnimals = new GaussianAnimalsAttributes(null, null);

    }
}
