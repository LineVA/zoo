package zoo.animal.reproduction;

import exception.name.UnknownNameException;
import launch.options.Option;

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
    
    Option option;

    public void setOption(Option option){
         for (Sex sex : Sex.values()) {
             sex.option = option;
         }
    }
    
    public Sex findByName(String name) throws UnknownNameException {
        for (Sex sex : Sex.values()) {
            if (sex.name().equalsIgnoreCase(name)) {
                return sex;
            }
        }
        throw new UnknownNameException(
                this.option.getReproductionBundle().getString("UNKNOWN_SEX"));
    }
    
    public String toStringByLanguage(){
        return this.option.getReproductionBundle().getString(this.toString().toUpperCase());
    }
}
