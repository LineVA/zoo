package zoo.animal.feeding;

import exception.IncorrectLoadException;
import launch.options.Option;
import lombok.Getter;
import utils.Utils;

/**s
 *
 * @author doyenm
 */
public class FeedingAttributes {

    @Getter
    private double foodQuantity;
    
    public FeedingAttributes(double quantity) throws IncorrectLoadException{
          if (Utils.isPositivOrNull(quantity)) {
            this.foodQuantity = quantity;
        } else {
            throw new IncorrectLoadException("");
        }
    }
    
    public void setFoodQuantity(double quantity) throws IncorrectLoadException{
        if (Utils.isPositivOrNull(quantity)) {
            this.foodQuantity = quantity;
        } else {
            throw new IncorrectLoadException("");
        }
    }
    
    public String toStringByLanguage(Option option){
        return option.getDietBundle().getString("FOOD_QUANTITY") + Utils.truncate(this.foodQuantity);
    }
}
