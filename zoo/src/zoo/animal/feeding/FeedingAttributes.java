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
    @Getter
    private Integer fastDays;
    
    public FeedingAttributes(double quantity, int fastDays) throws IncorrectLoadException{
          if (Utils.isPositivOrNull(quantity) && Utils.isBetween(fastDays, 0, 7)) {
            this.foodQuantity = quantity;
            this.fastDays = fastDays;
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
    
    public void setFastDays(int fastDays) throws IncorrectLoadException {
        if (fastDays >= 0 && fastDays <= 7) {
            this.fastDays= fastDays;
        } else {
            throw new IncorrectLoadException("");
        }
    }
    
    public String toStringByLanguage(Option option){
        return option.getSpecieBundle().getString("DIET.FOOD_QUANTITY") + Utils.truncate(this.foodQuantity);
    }
}
