package zoo.paddock;

import exception.IncorrectLoadException;
import launch.options.Option;
import lombok.Getter;
import utils.Utils;

/**
 *
 * @author doyenm
 */
public class TerritoryAttributes {

    @Getter
    private double territorySize;

    public TerritoryAttributes(double size) throws IncorrectLoadException {
        if (Utils.isPositiv(size)) {
            this.territorySize = size;
        } else {
            throw new IncorrectLoadException("");
        }
    }

    public String toStringByLanguage(Option option) {
        return option.getTerritoryBundle().getString("TERRITORY_SIZE") + this.territorySize;
    }
}
