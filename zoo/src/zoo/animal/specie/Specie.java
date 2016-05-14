package zoo.animal.specie;

import lombok.Getter;
import zoo.animal.Names;
import zoo.animal.feeding.FeedingAttributes;
import zoo.animal.reproduction.ReproductionAttributes;
import zoo.paddock.biome.BiomeAttributes;

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

    public Specie(Names names, BiomeAttributes biome, FeedingAttributes feeding,
            ReproductionAttributes repro) {
        this.names = names;
        this.biome = biome;
        this.feeding = feeding;
        this.reproduction = repro;
    }
}
