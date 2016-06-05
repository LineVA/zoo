package zoo.animal.feeding;

import exception.IncorrectDataException;
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
    
    @Override
    public String toString(){
        return "food quantity = " + this.foodQuantity;
    }
}
