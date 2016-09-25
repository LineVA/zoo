package zoo.animal.death;

import launch.options.Option;
import lombok.Getter;

/**
 *
 * @author doyenm
 */
public class LifeSpanLightAttributes {
    @Getter
    private final int lifeSpan;

    public LifeSpanLightAttributes(int lifeSpan) {
        this.lifeSpan = lifeSpan;
    }

    public String toStringByLanguage(Option option) {
        String info = "";
        info += option.getLifespanBundle().getString("LIFESPAN") + this.lifeSpan;
        return info;
    }
}
