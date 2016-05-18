
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
    
     @Override
    public String toString() {
        String info = "";
        info += "life span (female) = " + this.femaleLifeSpan + ", ";
        info += "life span (male) = " + this.maleLifeSpan;
        return info;
    }
}
