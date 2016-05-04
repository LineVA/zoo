package zoo.animal;

import lombok.Getter;
import zoo.paddock.Biome;
import zoo.paddock.BiomeAttributes;

/**
 * Enum of the species
 *
 * @author doyenm
 */
public enum Species {

    CAT("cat", Biome.RAINFOREST);

    /**
     * The name of the specie
     */
    @Getter
    private final String name;
    /**
     * Its biome
     */
    @Getter
    private final String biome;
    /**
     * Its average attributes
     */
    private BiomeAttributes average;
    /**
     * The standard deviations of its attributes
     */
    private BiomeAttributes standardDeviation;

    /**
     * The constructor of the specie
     *
     * @param name its name
     * @param biome its biome
     */
    Species(String name, Biome biome) {
        this.name = name;
        this.biome = biome.getName();
        this.average = (BiomeAttributes) biome.getAttributes().clone();
        this.standardDeviation = (BiomeAttributes) biome.getAttributes().clone();
    }
}
