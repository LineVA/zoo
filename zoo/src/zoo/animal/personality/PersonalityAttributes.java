package zoo.animal.personality;

import launch.options.Option;
import lombok.Getter;

/**
 *
 * @author doyenm
 */
public class PersonalityAttributes {
    
    @Getter
    Double bravery;
    
    public PersonalityAttributes(Double bravery){
        this.bravery = bravery;
    }
    
     public String toStringByLanguage(Option option){
        String toStr = "";
        toStr += option.getPersonalityBundle().getString("BRAVERY") + this.bravery;
        return toStr;
    }
}
