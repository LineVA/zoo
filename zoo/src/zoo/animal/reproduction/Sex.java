package zoo.animal.reproduction;

import exception.name.UnknownNameException;
import java.util.ArrayList;
import java.util.List;
import launch.options.Option;
import lombok.Getter;

/**
 * Enum of the sex
 *
 * @author doyenm
 */
public enum Sex {

    UNKNOWN(0),
    FEMALE(1),
    MALE(2);

    @Getter
    private int id;

    Sex(int id) {
        this.id = id;
    }

    public boolean isFemale() {
        return this == Sex.FEMALE;
    }

    public boolean isMale() {
        return this == Sex.MALE;
    }

    /**
     * The option used to know the current language
     */
    private Option option;

    public void setOption(Option option) {
        for (Sex sex : Sex.values()) {
            sex.option = option;
        }
    }
    
   /**
     * Find a sex according to its name and the current language
     * @param name the name to search
     * @return the corresponding diet
     * @throws UnknownNameException if the name matches none of the sexes 
     */
    public Sex findByNameAccordingToLanguage(String name) throws UnknownNameException {
        for (Sex sex : Sex.values()) {
            if (sex.toStringByLanguage().equalsIgnoreCase(name)) {
                return sex;
            }
        }
        throw new UnknownNameException(
                this.option.getReproductionBundle().getString("UNKNOWN_SEX"));
    }

      /**
     * Find a sex by its identifier
     * @param id the identifier to search
     * @return the corresponding diet
     * @throws UnknownNameException if no sex matches the identifier 
     */
    public Sex findById(int id) throws UnknownNameException {
        for (Sex sex : Sex.values()) {
            if (sex.getId() == id) {
                return sex;
            }
        }
        throw new UnknownNameException(
                this.option.getReproductionBundle().getString("UNKNOWN_SEX"));
    }

     /**
     * toString with the current language
     * @return the name of the sex, according to the current language
     */
    public String toStringByLanguage() {
        return this.option.getReproductionBundle().getString(this.toString().toUpperCase());
    }

      /**
     * List all of the sexes
     * @return the list, each item corresponding to an element of the enumeration
     */
    public List<String> list() {
        List<String> list = new ArrayList<>();
        for (Sex sex : Sex.values()) {
            list.add(
                    sex.getId() + " - " + this.option.getReproductionBundle().getString(sex.toString().toUpperCase()));
        }
        return list;
    }
}
