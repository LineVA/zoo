package zoo.paddock.biome;

import exception.name.UnknownNameException;
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

    static public Continent findById(int id) throws UnknownNameException {
        for (Continent continent : Continent.values()) {
            if (continent.getId() == id) {
                return continent;
            }
        }
        throw new UnknownNameException("No continent has this identifier.");
    }
}
