package zoo.paddock.biome;

import exception.name.UnknownNameException;
import lombok.Getter;

/**
 *
 * @author doyenm
 */
public enum Ecoregion {
    UNKNOWN(0, "Unknown"),
    UNKNOWN2(1, "Unknown2");
    
    @Getter 
    private int id;
    @Getter
    private final String name;

    Ecoregion(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public boolean equals(Ecoregion eco){
        return eco.equals(this);
    }
    
    public Ecoregion findByName(String name) throws UnknownNameException{
        for(Ecoregion region : Ecoregion.values()){
            if(region.getName().equalsIgnoreCase(name)){
                return region;
            }
        }
        throw new UnknownNameException("No ecoregion has this name.");
    }
    
    static public Ecoregion findById(int id) throws UnknownNameException{
          for(Ecoregion region : Ecoregion.values()){
            if(region.getId() == id){
                return region;
            }
        }
        throw new UnknownNameException("No region has this identifier.");
    }
    
    
}
