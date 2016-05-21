package zoo.animal.social;

import lombok.Getter;
import zoo.statistics.Gaussian;

/**
 *
 * @author doyenm
 */
public class GaussianSocialAttributes {

    @Getter
    private Gaussian groupSize;
    
    public GaussianSocialAttributes(SocialAttributes social){
        this.groupSize = new Gaussian(social.getGroupSize(), social.getGroupSize()/10.0);
    }
    
}
