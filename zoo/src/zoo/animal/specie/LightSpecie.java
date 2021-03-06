package zoo.animal.specie;

import java.util.List;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import zoo.animal.Names;

/**
 *
 * @author doyenm
 */
public class LightSpecie {

    @Getter @Setter
    private  List<Integer> diet;
    @Getter @Setter
    private  List<Integer> family;
    @Getter @Setter
    private  Names names;
    @Getter @Setter
    private  List<Integer> ecoregion;
    @Getter @Setter
    private  List<Integer> conservation;
    @Getter @Setter
    private  List<Integer> biome;
    @Getter @Setter
    private  List<Integer> size;
    @Getter @Setter
    private  List<Integer> continent;
    @Getter @Setter
    private List<Integer> breedingProgramme;
    @Getter @Setter
    private List<Integer> fastDay;
    @Getter @Setter
    private Set<String> tags;

    public LightSpecie(Names names,
            List<Integer> diet, List<Integer> conservation,
            List<Integer> ecoregion, List<Integer> family, List<Integer> biome, 
            List<Integer> size, List<Integer> continent, List<Integer> breedingProgramme, 
            List<Integer> fastDay, Set<String> tags) {
        this.names = names;
        this.diet = diet;
        this.family = family;
        this.ecoregion = ecoregion;
        this.biome = biome;
        this.size = size;
        this.conservation = conservation;
        this.continent = continent;
        this.breedingProgramme = breedingProgramme;
        this.fastDay = fastDay;
        this.tags = tags;
    }
}
