package zoo.animal.feeding;

import lombok.Getter;

/**
 *
 * @author doyenm
 */
public class FeedingAttributes {

    @Getter
    private double foodQuantity;
    
    public FeedingAttributes(double quantity){
        this.foodQuantity = quantity;
    }
}
