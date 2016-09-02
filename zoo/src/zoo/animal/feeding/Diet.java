package zoo.animal.feeding;

import exception.name.UnknownNameException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import launch.options.Option;
import lombok.Getter;

/**
 *
 * @author doyenm
 */
public enum Diet {

    NONE(0, "Does not eat"),
    APHIDIPHAGOUS(1, "feeds on aphids"),
    BACCIVOROUS(2, "feeds on berries"),
    CARNIVOROUS(3, "feeds on raw meat"),
    EXSUDATIVOROUS(4, "feeds on exsudate of plants"),
    FRUGIVOROUS(5, "feeds on fruits"),
    GRANIVOROUS(6, "feeds on seeds"),
    HEMATOPHAGOUS(7, "feeds on blood"),
    HERBIVOROUS(8, "feeds on grass"),
    INSECTIVOROUS(9, "feeds on insects"),
    LEPIDOPHAGOUS(10, "feeds on fish scales"),
    MELLIPHAGOUS(11, "feeds on honey"),
    MOLLUSCIVOROUS(12, "feeds on mollucs"),
    MYCOPHAGOUS(13, "feeds on mushrooms"),
    MYRMECOPHAGOUS(14, "feeds on ants"),
    NECROPHAGOUS(15, "feeds on carrions"),
    NECTARIVOROUS(16, "feeds on nectar"),
    OOPHAGOUS(17, "feeds on eggs"),
    OPHIOPHAGOUS(18, "feeds on snakes"),
    ORNITHOPHAGOUS(19, "feeds on birds"),
    PHYLLOPHAGOUS(20, "feeds on leaves"),
    PISCIVOROUS(21, "feeds on fishes"),
    PLANKTONIVOROUS(22, "feeds on plankton"),
    POLLINOVOROUS(23, "feeds on pollen"),
    VERMIVOROUS(24, "feeds on worms"),
    XYLOPHAGOUS(25, "feeds on wood"),
    ZEOPHAGOUS(26, "feeds on corn");

    @Getter
    int id;
    @Getter
    String definition;
    boolean[][] eatables;

    private static ResourceBundle bundle;

    Diet(int id, String text) {
        this.id = id;
        this.definition = text;
        fill();
    }

    public void setOption(Option option) {
        this.bundle = option.getDietBundle();
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
        throw new UnknownNameException(
                this.bundle.getString("UNKNOWN_DIET_BY_ID"));
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
            list.add(diet.toString() + " : " + this.bundle.getString(diet.toString().toUpperCase()));
        }
        return list;
    }
}
