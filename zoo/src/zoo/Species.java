package zoo;

/**
 * Enum of the species
 * @author doyenm
 */
public enum Species {
    ELEPHANT(0), 
    JAGUAR(1);
    
    
    private final int idSpecies;
    Species(int id){
        this.idSpecies = id;
    }

    public int getIdSpecies() {
        return idSpecies;
    }
    
    
}
