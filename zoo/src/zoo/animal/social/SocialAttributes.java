package zoo.animal.social;

import exception.IncorrectLoadException;
import launch.options.Option;
import lombok.Getter;

/**
 *
 * @author doyenm
 */
public class SocialAttributes {

    @Getter
    private final int groupSize;

    public SocialAttributes(int group) throws IncorrectLoadException{
        if (group > 0) {
            this.groupSize = group;
        } else if(group == 0){
            this.groupSize = 1;
        } else {
            throw new IncorrectLoadException("");
        }
    }
    
    public String toStringByLanguage(Option option){
        String toStr = "";
        toStr += option.getAnimalBundle().getString("SOCIAL.GROUP_SIZE") + this.groupSize;
        return toStr;
    }
}
