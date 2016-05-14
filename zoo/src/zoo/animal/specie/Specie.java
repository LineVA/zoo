package zoo.animal.specie;

import lombok.Getter;
import zoo.animal.Names;
import zoo.animal.feeding.FeedingAttributes;
import zoo.animal.feeding.GaussianFeedingAttributes;
import zoo.animal.reproduction.GaussianReproductionAttributes;
import zoo.animal.reproduction.ReproductionAttributes;
import zoo.paddock.biome.BiomeAttributes;
import zoo.paddock.biome.GaussianBiomeAttributes;

/**
 *
 * @author doyenm
 */
public class Specie {

    @Getter
    private final BiomeAttributes specieBiome;
    @Getter
    private final FeedingAttributes specieFeeding;
    @Getter
    private final ReproductionAttributes specieReproduction;
    @Getter
    private final Names names;
    @Getter
    private GaussianBiomeAttributes gaussianBiome;
    @Getter
    private GaussianFeedingAttributes gaussianFeeding;
    @Getter
    private GaussianReproductionAttributes gaussianReproduction;

    public Specie(Names names, BiomeAttributes biome, FeedingAttributes feeding,
            ReproductionAttributes repro) {
        this.names = names;
        this.specieBiome = biome;
        this.specieFeeding = feeding;
        this.specieReproduction = repro;
        this.gaussianBiome = new GaussianBiomeAttributes(biome);
        this.gaussianFeeding = new GaussianFeedingAttributes(feeding);
        this.gaussianReproduction = new GaussianReproductionAttributes(repro);

    }
}