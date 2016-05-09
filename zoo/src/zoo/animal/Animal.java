package zoo.animal;

import zoo.animal.reproduction.Sex;
import zoo.animal.feeding.FeedingAttributes;
import zoo.paddock.Paddock;
import lombok.Getter;
import zoo.animal.reproduction.ReproductionAttributes;
import zoo.paddock.biome.BiomeAttributes;

/**
 *
 * @author doyenm
 */
public class Animal {

    @Getter
    private final Species specie;
    @Getter
    private final String name;
    @Getter
    private final Paddock paddock;
    @Getter
    private final Sex sex;
    @Getter
    private int age;
    @Getter
    private AnimalsAttributes optimals;
    @Getter
    private AnimalsAttributes actuals;

    public Animal(Species spec, String name, Paddock paddock, Sex sex, int age) {
        this.specie = spec;
        this.name = name;
        this.paddock = paddock;
        this.sex = sex;
        this.age = age;
        this.optimals = new AnimalsAttributes(this.drawBiomeOptimals(spec),
                this.drawFeedingOptimals(spec), this.drawReproductionOptimals(spec));
        // Initially, the values are the ones of the specie
        this.actuals = new AnimalsAttributes(null, null, null);
    }
    
    public Animal(Species spec, String name, Paddock paddock, Sex sex, int age,
            BiomeAttributes biome, FeedingAttributes feeding, ReproductionAttributes repro) {
        this.specie = spec;
        this.name = name;
        this.paddock = paddock;
        this.sex = sex;
        this.age = age;
        this.optimals = new AnimalsAttributes(biome, feeding, repro);
        // Initially, the values are the ones of the specie
        this.actuals = new AnimalsAttributes(null, null, null);
    }

    public BiomeAttributes drawBiomeOptimals(Species spec) {
        double night = spec.getGaussiansAnimals().getBiome().getNightTemperature().nextGaussian();
        double day = 0.0;
        double pluvio = 0.0;
        double trreH = 0.0;
        double treeD = 0.0;
        double drop = 0.0;
        double water = 0.0;
        double humidity = 0.0;

        BiomeAttributes biome = new BiomeAttributes(night, water, pluvio, treeD, treeD, drop, water, humidity);

        return biome;
    }

    public FeedingAttributes drawFeedingOptimals(Species spec) {
        return new FeedingAttributes(0.0);
    }

    public ReproductionAttributes drawReproductionOptimals(Species spec) {
        return new ReproductionAttributes(0, 0, 0, 0);
    }

}
