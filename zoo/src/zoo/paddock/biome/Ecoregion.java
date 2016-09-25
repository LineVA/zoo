package zoo.paddock.biome;

import exception.name.UnknownNameException;
import java.util.ArrayList;
import launch.options.Option;
import lombok.Getter;

/**
 *
 * @author doyenm
 */
public enum Ecoregion {
    UNKNOWN(0),
    UNKNOWN2(1);

    @Getter
    private int id;
    Option option;
    
     public void setOption(Option option) {
        for (Ecoregion region : Ecoregion.values()) {
             region.option = option;
         }
    }

    Ecoregion(int id) {
        this.id = id;
    }

    public boolean equals(Ecoregion eco) {
        return eco.equals(this);
    }

     public Ecoregion findById(int id) throws UnknownNameException {
        for (Ecoregion region : Ecoregion.values()) {
            if (region.getId() == id) {
                return region;
            }
        }
        throw new UnknownNameException(
                this.option.getEcoregionBundle().getString("UNKNOWN_ID"));
    }

      public String toStringByLanguage(){
        return this.option.getEcoregionBundle().getString(this.toString().toUpperCase());
    }
      
      public ArrayList<String> list() {
        ArrayList<String> list = new ArrayList<>();
        for (Ecoregion region : Ecoregion.values()) {
            list.add(region.id + " - " +
                    this.option.getEcoregionBundle().getString(region.toString().toUpperCase()));
        }
        return list;
    }
}
