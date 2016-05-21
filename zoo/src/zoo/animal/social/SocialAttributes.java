package zoo.animal.social;

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
    
    @Override
    public String toString(){
        String toStr = "";
        toStr += "group size = " + this.groupSize;
        return toStr;
    }
}
