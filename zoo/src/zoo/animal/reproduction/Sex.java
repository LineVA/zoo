package zoo.animal.reproduction;

import exception.name.UnknownNameException;

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
    
     public Sex findByName(String name) throws UnknownNameException{
        for(Sex sex : Sex.values()){
            if(sex.name().equals(name)){
                return sex;
            }
        }
        throw new UnknownNameException("This sex does not exist.");
    }
}
