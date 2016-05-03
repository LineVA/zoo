package zoo.animal;

import lombok.Getter;

/**
 * Enum of the species
 * @author doyenm
 */
public enum Species {
    ELEPHANT(0), 
    JAGUAR(1);
    
    @Getter
    private final int idSpecies;
    
    Species(int id){
        this.idSpecies = id;
    }
}
