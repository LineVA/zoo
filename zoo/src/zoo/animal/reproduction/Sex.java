package zoo.animal.reproduction;

/**
 * Enum of the sex
 * @author doyenm
 */
public enum Sex {

    FEMALE,
    MALE;
    
    public boolean isFemale(){
        return this == Sex.FEMALE;
    }
    
    public boolean isMale(){
        return this == Sex.MALE;
    }
}
