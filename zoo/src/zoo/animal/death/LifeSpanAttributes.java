
package zoo.animal.death;

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
}
