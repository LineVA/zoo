package zoo.paddock;

import launch.options.Option;
import lombok.Getter;

/**
 *
 * @author doyenm
 */
public class TerritoryAttributes {
    
    @Getter
    private double territorySize;
    
    public TerritoryAttributes(double size){
        this.territorySize = size;
    }
    
    public String toStringByLanguage(Option option){
        return option.getTerritoryBundle().getString("TERRITORY_SIZE") + this.territorySize;
    }
}
