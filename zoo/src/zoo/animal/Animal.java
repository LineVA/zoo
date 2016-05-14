package zoo.animal;

import zoo.animal.reproduction.Sex;
import zoo.animal.feeding.FeedingAttributes;
import zoo.paddock.Paddock;
import lombok.Getter;
import lombok.Setter;
import zoo.animal.reproduction.ReproductionAttributes;
import zoo.animal.specie.Specie;
import zoo.paddock.biome.BiomeAttributes;

/**
 *
 * @author doyenm
 */
public class Animal {

    @Getter
    //private final Species specie;
    private final Specie specie;
    @Getter
    private final String name;
    @Getter
    private final Paddock paddock;
    @Getter
    private final Sex sex;
    @Getter
    private int age;
    // There is both optimal and actual biome attributes :
    // the first are determined when the animal is created,
    // the second are the ones of its paddock.
    @Getter
    private BiomeAttributes optimalBiome;
    // There is both optimal and actual feeding attributes : 
    // the first are computed when the animal is created,
    // the second are determined by the player.
    @Getter
    private final FeedingAttributes optimalFeeding;
    @Getter
    @Setter
    private FeedingAttributes actualFeeding;
    // The actual reproduction attributes are computed 
    // when the animal is created ;
    // there is no notion of "optimal reproduction attributes".
    @Getter
    private final ReproductionAttributes actualReproduction;

    public Animal(Specie spec, String name, Paddock paddock, Sex sex, int age) {
        this.specie = spec;
        this.name = name;
        this.paddock = paddock;
        this.sex = sex;
        this.age = age;
        this.optimalBiome = drawOptimalBiome(spec);
        this.optimalFeeding = drawOptimalFeeding(spec);
        this.actualFeeding = drawActualFeeding(spec);
        this.actualReproduction = drawActualReproduction(spec);
    }

    /**
     * Computes the optimal values of the biome attributes for this animal
     * according to its specie
     *
     * @param spec the Specie of the animal
     * @return its optimalBiomeAttributes
     */
    public BiomeAttributes drawOptimalBiome(Specie spec) {
        double night = 0.0;
        double day = 0.0;
        double pluvio = 0.0;
        double treeH = 0.0;
        double treeD = 0.0;
        double drop = 0.0;
        double water = 0.0;
        double humidity = 0.0;
        return new BiomeAttributes(night, day, pluvio, treeD, treeH, drop, water, humidity);
    }

    /**
     * Compute the optimal values of the feeding attributes for this animal
     * according to its specie
     *
     * @param spec the Specie of the animal
     * @return its optimalFeedingAttributes
     */
    public FeedingAttributes drawOptimalFeeding(Specie spec) {
        return new FeedingAttributes(0.0);
    }

    /**
     * Compute the actual values of the biome attributes for this animal
     * according to its specie : there are the ones of the specie
     *
     * @param spec the Specie of the animal
     * @return its actualFeedingAttributes
     */
    public FeedingAttributes drawActualFeeding(Specie spec) {
        return spec.getFeeding();
    }

    /**
     * Compute the actual values of the reproduction attributes for this animal
     * according to its specie
     *
     * @param spec the Specie of the animal
     * @return its actualReproductionAttributes
     */
    public ReproductionAttributes drawActualReproduction(Specie spec) {
        return new ReproductionAttributes(0, 0, 0, 0);
    }
}
