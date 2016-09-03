package zoo.animal.reproduction;

import exception.name.UnknownNameException;
import launch.options.Option;
import lombok.Setter;

/**
 * Enum of the sex
 *
 * @author doyenm
 */
public enum Sex {
    UNKNOWN,
    FEMALE,
    MALE;
    
    public boolean isFemale() {
        return this == Sex.FEMALE;
    }
    
    public boolean isMale() {
        return this == Sex.MALE;
    }
    
    @Setter
    Option option;
    
    public Sex findByName(String name) throws UnknownNameException {
        for (Sex sex : Sex.values()) {
            if (sex.name().equalsIgnoreCase(name)) {
                return sex;
            }
        }
        throw new UnknownNameException(
                this.option.getReproductionBundle().getString("UNKNOWN_SEX"));
    }
}
