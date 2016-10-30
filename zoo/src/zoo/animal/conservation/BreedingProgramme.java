package zoo.animal.conservation;

import exception.name.UnknownNameException;
import java.util.ArrayList;
import java.util.List;
import launch.options.Option;
import lombok.Getter;

/**
 * The breeding programmes
 *
 * @author doyenm
 */
public enum BreedingProgramme {

    NONE(0),
    EEP(1),
    ESB(2);

    @Getter
    private int id;

    /**
     * Constructor
     *
     * @param id the identifier of the breeding programme
     */
    BreedingProgramme(int id) {
        this.id = id;
    }

    /**
     * Options used to know the current language
     */
    private Option option;

    public void setOption(Option option) {
        for (BreedingProgramme programme : BreedingProgramme.values()) {
            programme.option = option;
        }
    }
/**
 * Find a breeding programme by its identifier
 * @param id the identifier to search
 * @return the corresponded breeding programme
 * @throws UnknownNameException if the parameter does not match with any of the programmes
 */
    
    public BreedingProgramme findById(int id) throws UnknownNameException {
        for (BreedingProgramme programme : BreedingProgramme.values()) {
            if (programme.getId() == id) {
                return programme;
            }
        }
        throw new UnknownNameException(
                this.option.getSpecieBundle().getString("UNKNOWN_NAME_BP"));
    }

    /**
     * Return the name of the breeding programme according to its option
     * @return the name of the breeding programme
     */
    public String toStringByLanguage() {
        return this.option.getSpecieBundle().getString("BP_" + this.toString().toUpperCase());
    }

    /**
     * List all of the breeding programmes
     * @return the list, each item corresponding to an element of the enumeration
     */
    public List<String> list() {
        List<String> list = new ArrayList<>();
        for (BreedingProgramme programme : BreedingProgramme.values()) {
            list.add(programme.id + " - "
                    + this.option.getSpecieBundle().getString("BP_" + programme.toString().toUpperCase() + "_DESCRIPTION"));
        }
        return list;
    }
}
