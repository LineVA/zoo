package zoo.paddock;

import lombok.Getter;
import zoo.statistics.Gaussian;

/**
 *
 * @author doyenm
 */
public class GaussianTerritoryAttributes {

    @Getter
    private final Gaussian territorySize;
    
    public GaussianTerritoryAttributes(TerritoryAttributes territory){
        this.territorySize = new Gaussian(territory.getTerritorySize(), territory.getTerritorySize()/10.0);
    }
}
