package zoo.animal.death;

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

    @Override
    public String toString() {
        String info = "";
        info += "life span = " + this.lifeSpan + ", ";
        return info;
    }
}
