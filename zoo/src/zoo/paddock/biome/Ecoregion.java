package zoo.paddock.biome;

import exception.name.UnknownNameException;
import java.util.ArrayList;
import java.util.List;
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
   /**
     * The option used to know the current language
     */
    private Option option;
    
     public void setOption(Option option) {
        for (Ecoregion region : Ecoregion.values()) {
             region.option = option;
         }
    }

    Ecoregion(int id) {
        this.id = id;
    }

    /**
     * Test the equality of two ecoregions according to their identifier
     * @param eco the ecoregion we compare to the current one
     * @return true if they are equals
     */
    public boolean equals(Ecoregion eco) {
        return eco.equals(this);
    }

      /**
     * Find an ecoregion by its identifier
     * @param id the identifier to search
     * @return the corresponding diet
     * @throws UnknownNameException if no ecoregion matches the identifier 
     */
     public Ecoregion findById(int id) throws UnknownNameException {
        for (Ecoregion region : Ecoregion.values()) {
            if (region.getId() == id) {
                return region;
            }
        }
        throw new UnknownNameException(
                this.option.getPaddockBundle().getString("ECOREGION.UNKNOWN_ID"));
    }

      /**
     * toString with the current language
     * @return the name of the ecoregion, according to the current language
     */
      public String toStringByLanguage(){
        return this.option.getPaddockBundle().getString("ECOREGION." + this.toString().toUpperCase());
    }
      
        /**
     * List all of the ecoregions
     * @return the list, each item corresponding to an element of the enumeration
     */
      public List<String> list() {
        List<String> list = new ArrayList<>();
        for (Ecoregion region : Ecoregion.values()) {
            list.add(region.id + " - " +
                  region.toStringByLanguage());
        }
        return list;
    }
}
