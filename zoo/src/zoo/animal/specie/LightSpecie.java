package zoo.animal.specie;

import lombok.Getter;
import lombok.Setter;
import zoo.animal.Names;

/**
 *
 * @author doyenm
 */
public class LightSpecie {

    @Getter @Setter
    private  int diet;
    @Getter @Setter
    private  int family;
    @Getter @Setter
    private  Names names;
    @Getter @Setter
    private  int ecoregion;
    @Getter @Setter
    private  int conservation;
    @Getter @Setter
    private  int biome;
    @Getter @Setter
    private  int size;
    @Getter @Setter
    private  int continent;

    public LightSpecie(Names names,
            int diet, int conservation,
            int ecoregion, int family, int biome, int size, int continent) {
        this.names = names;
        this.diet = diet;
        this.family = family;
        this.ecoregion = ecoregion;
        this.biome = biome;
        this.size = size;
        this.conservation = conservation;
        this.continent = continent;
    }
}
