package zoo.animal;

import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import zoo.animal.feeding.Diet;
import zoo.animal.reproduction.Sex;
import zoo.paddock.IPaddock;
import zoo.paddock.biome.Biome;

/**
 *
 * @author doyenm
 */
public class LightAnimal {

    @Getter
    @Setter
    private Set<Integer> diets;
    @Getter
    @Setter
    private Set<Integer> starvations;
    @Getter
    @Setter
    private Set<Integer> drownings;
    @Getter
    @Setter
    private Set<Sex> sexes;
    @Getter
    @Setter
    private Set<Biome> biomes;
    @Getter
    @Setter
    private Set<IPaddock> paddocks;
    @Getter
    @Setter
    private Set<Integer> fastDays;

    public LightAnimal(Set<Integer> diets,
            Set<Integer> starvations, Set<Integer> drownings, Set<Sex> sexes,
            Set<IPaddock> paddocks, Set<Integer> fastDays, Set<Biome> biomes) {
        this.diets = diets;
        this.starvations = starvations;
        this.drownings = drownings;
        this.sexes = sexes;
        this.biomes = biomes;
        this.paddocks = paddocks;
        this.fastDays = fastDays;
    }
}
