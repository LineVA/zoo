package zoo.animal.specie;

import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;
import zoo.animal.Names;

/**
 *
 * @author doyenm
 */
public class LightSpecie {

    @Getter @Setter
    private  ArrayList<Integer> diet;
    @Getter @Setter
    private  ArrayList<Integer> family;
    @Getter @Setter
    private  Names names;
    @Getter @Setter
    private  ArrayList<Integer> ecoregion;
    @Getter @Setter
    private  ArrayList<Integer> conservation;
    @Getter @Setter
    private  ArrayList<Integer> biome;
    @Getter @Setter
    private  ArrayList<Integer> size;
    @Getter @Setter
    private  ArrayList<Integer> continent;
    @Getter @Setter
    private ArrayList<Integer> breedingProgramme;

    public LightSpecie(Names names,
            ArrayList<Integer> diet, ArrayList<Integer> conservation,
            ArrayList<Integer> ecoregion, ArrayList<Integer> family, ArrayList<Integer> biome, 
            ArrayList<Integer> size, ArrayList<Integer> continent, ArrayList<Integer> breedingProgramme) {
        this.names = names;
        this.diet = diet;
        this.family = family;
        this.ecoregion = ecoregion;
        this.biome = biome;
        this.size = size;
        this.conservation = conservation;
        this.continent = continent;
        this.breedingProgramme = breedingProgramme;
    }
}
