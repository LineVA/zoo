package zoo.animal.feeding;

import exception.name.UnknownNameException;
import java.util.ArrayList;
import java.util.Arrays;
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
    PLANKTONIVOROUS(13, "feeds on plankton"),
    VERMIVOROUS(14, "feeds on worms"),
    BACCIVOROUS(15, "feeds on berries"),
    EXSUDATIVOROUS(16, "feeds on exsudate of plants"),
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
    boolean[][] eatables;

    Diet(int id, String text) {
        this.id = id;
        this.definition = text;
        fill();
    }

    public void fill() {
        eatables = new boolean[27][27];
        Arrays.fill(this.eatables[0], false);
        Arrays.fill(this.eatables[1], false);
        Arrays.fill(this.eatables[2], false);
        Arrays.fill(this.eatables[3], false);
        Arrays.fill(this.eatables[4], false);
        Arrays.fill(this.eatables[5], false);
        Arrays.fill(this.eatables[6], false);
        Arrays.fill(this.eatables[7], false);
        Arrays.fill(this.eatables[8], false);
        Arrays.fill(this.eatables[9], false);
        Arrays.fill(this.eatables[10], false);
        Arrays.fill(this.eatables[11], false);
        Arrays.fill(this.eatables[12], false);
        Arrays.fill(this.eatables[13], false);
        Arrays.fill(this.eatables[14], false);
        Arrays.fill(this.eatables[15], false);
        Arrays.fill(this.eatables[16], false);
        Arrays.fill(this.eatables[17], false);
        Arrays.fill(this.eatables[18], false);
        Arrays.fill(this.eatables[19], false);
        Arrays.fill(this.eatables[20], false);
        Arrays.fill(this.eatables[21], false);
        Arrays.fill(this.eatables[22], false);
        Arrays.fill(this.eatables[23], false);
        Arrays.fill(this.eatables[24], false);
        Arrays.fill(this.eatables[25], false);
        Arrays.fill(this.eatables[26], false);
        for (int i = 0; i < this.eatables.length; i++) {
            this.eatables[i][2] = true;
            this.eatables[i][8] = true;
        }

    }

    public Diet findDietById(int id) throws UnknownNameException {
        for (Diet diet : Diet.values()) {
            if (diet.getId() == id) {
                return diet;
            }
        }
        throw new UnknownNameException("No diet has this identifier.");
    }

    public boolean isCompatible(int diet) throws UnknownNameException {
        return !this.canBeEatenBy(diet) && !Diet.NONE.findDietById(diet).canBeEatenBy(this.id);
    }

    public boolean canBeEatenBy(int diet) {
        return this.eatables[this.id][diet];
    }

    public Diet findDietByName(String name) throws UnknownNameException {
        for (Diet diet : Diet.values()) {
            if (diet.toString().equals(name)) {
                return diet;
            }
        }
        throw new UnknownNameException("No diet has this identifier.");
    }

    public ArrayList<String> list() {
        ArrayList<String> list = new ArrayList<>();
        for (Diet diet : Diet.values()) {
            list.add(diet.toString() + " : " + diet.definition);
        }
        return list;
    }
}
