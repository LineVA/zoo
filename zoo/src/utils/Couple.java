package utils;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author doyenm
 */
public class Couple {
    
    @Getter
    @Setter
    private int a;
    @Getter
    @Setter
    private int b;

    public Couple(int a, int b) {
        this.a = a;
        this.b = b;
    }
    
}
