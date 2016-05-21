package zoo.paddock;

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
    
    @Override 
    public String toString(){
        return "territory size = " + this.territorySize;
    }
}
