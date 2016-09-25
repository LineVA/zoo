package zoo.animal.social;

import launch.options.Option;
import lombok.Getter;

/**
 *
 * @author doyenm
 */
public class SocialAttributes {

    @Getter
    private final int groupSize;

    public SocialAttributes(int group) {
        if (group > 0) {
            this.groupSize = group;
        } else {
            this.groupSize = 1;
        }
    }
    
    public String toStringByLanguage(Option option){
        String toStr = "";
        toStr += option.getSocialBundle().getString("GROUP_SIZE") + this.groupSize;
        return toStr;
    }
}
