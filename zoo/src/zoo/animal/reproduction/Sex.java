package zoo.animal.reproduction;

import exception.name.UnknownNameException;
import java.util.ArrayList;
import launch.options.Option;
import lombok.Getter;

/**
 * Enum of the sex
 *
 * @author doyenm
 */
public enum Sex {
    UNKNOWN (0),
    FEMALE (1),
    MALE (2);
    
    @Getter
    private int id;
    
    Sex(int id){
        this.id = id;
    }
    
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
    
    public Sex findById(int id) throws UnknownNameException {
        for (Sex sex : Sex.values()) {
            if (sex.getId() == id) {
                return sex;
            }
        }
        throw new UnknownNameException(
                this.option.getReproductionBundle().getString("UNKNOWN_SEX"));
    }
    
    public String toStringByLanguage(){
        return this.option.getReproductionBundle().getString(this.toString().toUpperCase());
    }
    
    public ArrayList<String> list(){
         ArrayList<String> list = new ArrayList<>();
        for (Sex sex : Sex.values()) {
            list.add(
                   sex.getId() + " - " +  this.option.getReproductionBundle().getString(sex.toString().toUpperCase()));
        }
        return list;
    }
}
