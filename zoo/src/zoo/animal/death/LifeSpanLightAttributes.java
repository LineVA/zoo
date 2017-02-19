package zoo.animal.death;

import exception.IncorrectDataException;
import exception.IncorrectLoadException;
import launch.options.Option;
import lombok.Getter;
import utils.Utils;

/**
 *
 * @author doyenm
 */
public class LifeSpanLightAttributes {

    @Getter
    private final int lifeSpan;

    public LifeSpanLightAttributes(int lifeSpan) throws IncorrectLoadException {
        if (Utils.isPositivOrNull(lifeSpan)) {
            this.lifeSpan = lifeSpan;
        } else {
            throw new IncorrectLoadException("");
        }
    }

    public String toStringByLanguage(Option option) throws IncorrectDataException {
        return option.getAnimalBundle().getString("LIFESPAN.LIFESPAN") + Utils.infoAge(this.lifeSpan, option.getAnimalBundle());
    }
}
