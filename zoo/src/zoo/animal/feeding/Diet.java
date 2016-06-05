package zoo.animal.feeding;

import exception.name.UnknownNameException;
import lombok.Getter;

/**
 *
 * @author doyenm
 */
public enum Diet {

    NONE(0, "does not eat"),
    APHIDIPHAGOUS(1, "feeds on aphids"),
    CARNIVOROUS(2, "feeds on raw meat"),
    HEMATOPHAGOUS(3, "feeds on blood"),
    INSECTIVOROUS(4, "feeds on insects"),
    LEPIDOPHAGOUS(5, "feeds on fish scales"),
    MOLLUSCIVOROUS(6, "feeds on mollucs"),
    MYRMECOPHAGOUS(7, "feeds on ants"),
    NECROPHAGOUS(8, "feeds on carrions"),
    OOPHAGOUS(9, "feeds on eggs"),
    OPHIOPHAGOUS(10, "feeds on snakes"),
    ORNITHOPHAGOUS(11, "feeds on birds"),
    PISCIVOROUS(12, "feeds on fishes"),
    PLANCTONIVOROUS(13, "feeds on plankton"),
    VERMIVOROUS(14, "feeds on worms"),
    BACCIVOROUS(15, "feeds on berries"),
    EXSUDATIVOROUS(16, "feeds on exudate of plants"),
    FRUGIVOROUS(17, "feeds on fruits"),
    GRANIVOROUS(18, "feeds on seeds"),
    HERBIVOROUS(19, "feeds on grass"),
    MELLIPHAGOUS(20, "feeds on honey"),
    MYCOPHAGOUS(21, "feeds on mushrooms"),
    NECTARIVOROUS(22, "feeds on nectar"),
    PHYLLOPHAGOUS(23, "feeds on leaves"),
    POLLINOVOROUS(24, "feeds on pollen"),
    XYLOPHAGOUS(25, "feeds on wood"),
    ZEOPHAGOUS(26, "feeds on corn");

    @Getter
    int id;
    @Getter
    String definition;

    Diet(int id, String text) {
        this.id = id;
        this.definition = text;
    }

    public Diet findDietById(int id) throws UnknownNameException {
        for (Diet diet : Diet.values()) {
            if (diet.getId() == id) {
                return diet;
            }
        }
        throw new UnknownNameException("No diet has this identifier.");
    }
}
