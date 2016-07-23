package zoo.paddock.biome;

import exception.name.UnknownNameException;
import lombok.Getter;

/**
 *
 * @author doyenm
 */
public enum Continent {
    AFRICA(0),
    LATINAMERICA(1),
    NORTHENAMERICA(2),
    EUROPE(3),
    ASIA(4),
    OCEANIA(5);
    
    
    @Getter 
    private int id;

    Continent(int id) {
        this.id = id;
    }
    
    public boolean equals(Ecoregion eco){
        return eco.equals(this);
    }
    
    static public Continent findById(int id) throws UnknownNameException{
          for(Continent continent : Continent.values()){
            if(continent.getId() == id){
                return continent;
            }
        }
        throw new UnknownNameException("No continent has this identifier.");
    }
}
