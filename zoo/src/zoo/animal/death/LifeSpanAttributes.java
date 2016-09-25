
package zoo.animal.death;

import java.util.ResourceBundle;
import launch.options.Option;
import lombok.Getter;

/**
 *
 * @author doyenm
 */
public class LifeSpanAttributes {
    @Getter
    private final int femaleLifeSpan;
    @Getter
    private final int maleLifeSpan;

    public LifeSpanAttributes(int femaleLifeSpan, int maleLifeSpan) {
        this.femaleLifeSpan = femaleLifeSpan;
        this.maleLifeSpan = maleLifeSpan;
    }
    
    public String toStringByLanguage(Option option) {
         ResourceBundle bundle = option.getLifespanBundle();
        String info = "";
        info += bundle.getString("FEMALE_LIFESPAN") + this.femaleLifeSpan + ", ";
        info += bundle.getString("MALE_LIFESPAN") + this.maleLifeSpan;
        return info;
    }
}
