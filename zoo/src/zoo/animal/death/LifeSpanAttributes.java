package zoo.animal.death;

import exception.IncorrectLoadException;
import java.util.ResourceBundle;
import launch.options.Option;
import lombok.Getter;
import utils.Utils;

/**
 *
 * @author doyenm
 */
public class LifeSpanAttributes {

    @Getter
    private final int femaleLifeSpan;
    @Getter
    private final int maleLifeSpan;

    public LifeSpanAttributes(int femaleLifeSpan, int maleLifeSpan) throws IncorrectLoadException {
        if (Utils.isPositivOrNull(femaleLifeSpan)) {
            this.femaleLifeSpan = femaleLifeSpan;
        } else {
            throw new IncorrectLoadException("");
        }
        if (Utils.isPositivOrNull(maleLifeSpan)) {
            this.maleLifeSpan = maleLifeSpan;
        } else {
            throw new IncorrectLoadException("");
        }
    }

    public String toStringByLanguage(Option option) {
        ResourceBundle bundle = option.getLifespanBundle();
        String info = "";
        info += bundle.getString("FEMALE_LIFESPAN") + this.femaleLifeSpan + ", ";
        info += bundle.getString("MALE_LIFESPAN") + this.maleLifeSpan;
        return info;
    }
}
