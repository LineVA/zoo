package zoo.animal.feeding;

import exception.name.UnknownNameException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import launch.options.Option;
import lombok.Getter;

/**
 * The diets
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
    MYCOPHAGOUS(13),
    MYRMECOPHAGOUS(14),
    NECROPHAGOUS(15),
    NECTARIVOROUS(16),
    OMNIVOROUS(17),
    OOPHAGOUS(18),
    OPHIOPHAGOUS(19),
    ORNITHOPHAGOUS(20),
    PHYLLOPHAGOUS(21),
    PISCIVOROUS(22),
    PLANKTONIVOROUS(23),
    POLLINOVOROUS(24),
    VERMIVOROUS(25),
    XYLOPHAGOUS(26),
    ZEOPHAGOUS(27),
    LIGNIVOROUS(28);

    @Getter
    int id;
    /**
     * Indicates if the line can be eaten by the column
     */
    boolean[][] eatables;

    /**
     * Optionused to know the current language
     */
    private Option option;

    Diet(int id) {
        this.id = id;
        fill();
    }

    public void setOption(Option option) {
        for (Diet diet : Diet.values()) {
            diet.option = option;
        }
    }

    /**
     * Fill the eatables array
     * A carnivore can eat everyone
     */
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
            this.eatables[3][i] = true;
        }
    }

    /**
     * Find a diet by its identifier
     * @param id the identifier to search
     * @return the corresponding diet
     * @throws UnknownNameException if tno diet matches the identifier 
     */
    public Diet findById(int id) throws UnknownNameException {
        for (Diet diet : Diet.values()) {
            if (diet.getId() == id) {
                return diet;
            }
        }
        throw new UnknownNameException(
                this.option.getSpecieBundle().getString("DIET.UNKNOWN_DIET_BY_ID"));
    }

    /**
     * Indicates if several diets are compatible with the current one : 
     * the current cannot eat the ones given in parameters and
     * the ones if parameter cannot eat the current one
     * @param diets  list of diets to check
     * @return true if they are all compatible with the current one
     * @throws UnknownNameException if one of the integer in parameters doest not match
     * with any diet of the enumeration
     */
    public boolean isCompatible(List<Integer> diets) throws UnknownNameException {
        boolean result = false;
        for (Integer diet : diets) {
            result = result || (!this.canBeEatenBy(diet) && !Diet.NONE.findById(diet).canBeEatenBy(this.id));
        }
        return result;
    }

    /**
     * Check if the parameter can be eaten by the current one
     * @param diet the diet to check
     * @return true if it can be eaten by the current one
     */
    public boolean canBeEatenBy(int diet) {
        return this.eatables[diet][this.id];
    }

    /**
     * Find a diet according to its name and the current language
     * @param name the name to search
     * @return the corresponding diet
     * @throws UnknownNameException if the name matches none of the diets 
     */
    public Diet findByNameAccordingToLanguage(String name) throws UnknownNameException {
        for (Diet diet : Diet.values()) {
            if (diet.toStringByLanguage().equalsIgnoreCase(name)) {
                return diet;
            }
        }
        throw new UnknownNameException(
                this.option.getSpecieBundle().getString("DIET.UNKNOWN_DIET_BY_NAME"));
    }

    /**
     * List all of the diets
     * @return the list, each item corresponding to an element of the enumeration
     */
    public List<String> list() {
        List<String> list = new ArrayList<>();
        for (Diet diet : Diet.values()) {
            list.add(diet.id + " - " + diet.toStringByLanguage()
                    + " : " + this.option.getSpecieBundle().getString("DIET." + diet.toString().toUpperCase() + "_DESCRIPTION"));
        }
        return list;
    }

    /**
     * toString with the current language
     * @return the name of the diet, according to the current language
     */
    public String toStringByLanguage() {
        return this.option.getSpecieBundle().getString("DIET." + this.toString().toUpperCase());
    }
}
