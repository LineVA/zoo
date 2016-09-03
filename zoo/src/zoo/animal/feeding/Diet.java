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

    NONE(0),
    APHIDIPHAGOUS(1),
    BACCIVOROUS(2),
    CARNIVOROUS(3),
    EXSUDATIVOROUS(4),
    FRUGIVOROUS(5),
    GRANIVOROUS(6),
    HEMATOPHAGOUS(7),
    HERBIVOROUS(8),
    INSECTIVOROUS(9),
    LEPIDOPHAGOUS(10),
    MELLIPHAGOUS(11),
    MOLLUSCIVOROUS(12),
    MYCOPHAGOUS(13 ),
    MYRMECOPHAGOUS(14),
    NECROPHAGOUS(15),
    NECTARIVOROUS(16),
    OOPHAGOUS(17),
    OPHIOPHAGOUS(18),
    ORNITHOPHAGOUS(19),
    PHYLLOPHAGOUS(20),
    PISCIVOROUS(21),
    PLANKTONIVOROUS(22),
    POLLINOVOROUS(23),
    VERMIVOROUS(24),
    XYLOPHAGOUS(25),
    ZEOPHAGOUS(26);

    @Getter
    int id;
    boolean[][] eatables;

//    private static ResourceBundle bundle;
    private Option option;

    Diet(int id) {
        this.id = id;
        fill();
    }

    public void setOption(Option option) {
        this.option = option;
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
                this.option.getDietBundle().getString("UNKNOWN_DIET_BY_ID"));
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
            list.add(diet.id + " - " +this.option.getDietBundle().getString(diet.toString().toUpperCase()) 
                    + " : " + this.option.getDietBundle().getString(diet.toString().toUpperCase()+"_DESCRIPTION"));
        }
        return list;
    }
    
    public String toStringByLanguage(){
        return this.option.getDietBundle().getString(this.toString().toUpperCase());
    }
}
