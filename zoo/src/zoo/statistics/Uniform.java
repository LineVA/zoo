package zoo.statistics;

import java.util.Random;

/**
 *
 * @author doyenm
 */
public class Uniform extends Random {

    public int intAverage(int average) {
        int max = 3 * average;
        int actual = super.nextInt(max);
        if (actual >= 1) {
            return actual;
        } else {
            return 1;
        }
    }

    public boolean isGreaterOrEquals(double lim) {
        return super.nextDouble() >= lim;
    }

    public boolean isLowerOrEquals(double lim) {
        return super.nextDouble() <= lim;
    }

}
