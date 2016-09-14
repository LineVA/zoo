package zoo.paddock.biome;

import exception.name.UnknownNameException;
import java.util.ArrayList;
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
    OCEANIA(6);

    @Getter
    private final int id;

    @Getter
    Option option;

    Continent(int id) {
        this.id = id;
    }

    public void setOption(Option option) {
        for (Continent continent : Continent.values()) {
            continent.option = option;
        }
    }

    public Continent findById(int id) throws UnknownNameException {
        for (Continent continent : Continent.values()) {
            if (continent.getId() == id) {
                return continent;
            }
        }
        throw new UnknownNameException(
                this.option.getContinentBundle().getString("UNKNOWN_CONTINENT"));
    }

    public String toStringByLanguage() {
        return this.option.getContinentBundle().getString(this.toString().toUpperCase());
    }
    
    public ArrayList<String> list() {
        ArrayList<String> list = new ArrayList<>();
        for (Continent continent : Continent.values()) {
            list.add(continent.id + " - " +
                    this.option.getContinentBundle().getString(continent.toString().toUpperCase()));
        }
        return list;
    }
}
