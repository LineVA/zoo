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
public enum Continent {

    UNKNOWN(0),
    AFRICA(1),
    LATINAMERICA(2),
    NORTHENAMERICA(3),
    EUROPE(4),
    ASIA(5),
    OCEANIA(6),
    ARCTIC(7),
    ANTARTIC(8);

    @Getter
    private final int id;

      /**
     * The option used to know the current language
     */
    private Option option;

    Continent(int id) {
        this.id = id;
    }

    public void setOption(Option option) {
        for (Continent continent : Continent.values()) {
            continent.option = option;
        }
    }

      /**
     * Find a continent by its identifier
     * @param id the identifier to search
     * @return the corresponding diet
     * @throws UnknownNameException if no continent matches the identifier 
     */
    public Continent findById(int id) throws UnknownNameException {
        for (Continent continent : Continent.values()) {
            if (continent.getId() == id) {
                return continent;
            }
        }
        throw new UnknownNameException(
                this.option.getContinentBundle().getString("UNKNOWN_CONTINENT"));
    }

     /**
     * toString with the current language
     * @return the name of the continent, according to the current language
     */
    public String toStringByLanguage() {
        return this.option.getContinentBundle().getString(this.toString().toUpperCase());
    }
    
      /**
     * List all of the continents
     * @return the list, each item corresponding to an element of the enumeration
     */
    public List<String> list() {
        List<String> list = new ArrayList<>();
        for (Continent continent : Continent.values()) {
            list.add(continent.id + " - " +
                    this.option.getContinentBundle().getString(continent.toString().toUpperCase()));
        }
        return list;
    }
}
