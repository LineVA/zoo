package zoo.animal.feeding;

import exception.IncorrectDataException;
import launch.options.Option;
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
    
    public void setFoodQuantity(double quantity) throws IncorrectDataException{
        if(quantity >= 0.0){
            this.foodQuantity = quantity;
        } else {
            throw new IncorrectDataException("The food quantity cannot be negativ");
        }
    }
    
    public String toStringByLanguage(Option option){
        return option.getDietBundle().getString("FOOD_QUANTITY") + this.foodQuantity;
    }
}
