package zoo.animal.feeding;

import lombok.Getter;
import zoo.Statistics.Gaussian;

/**
 *
 * @author doyenm
 */
public class GaussianFeedingAttributes {

    @Getter
    private Gaussian foodQuantity;
    
    public GaussianFeedingAttributes(FeedingAttributes average){
        this.foodQuantity = new Gaussian(average.getFoodQuantity(), average.getFoodQuantity()/10);
    }
}
