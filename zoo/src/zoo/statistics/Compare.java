package zoo.statistics;

/**
 *
 * @author doyenm
 */
public class Compare {

    private static final double max5 = 5;
    private static final double max4 = 4;
    private static final double max3 = 3;
    private static final double max2 = 2;
    private static final double max1 = 1;
    private static final double max0 = 0;

    public static double compare(double optimal, double actual, double width) {
        if ((actual >= (1.0 - width) * optimal) & (actual <= (1.0 + width) * optimal)) {
            return max5;
        } else if (actual >= (1.0 - 2 * width) * optimal && actual <= (1.0 + 2 * width) * optimal) {
            return max3;
        } else if (actual >= (1.0 - 3 * width) * optimal && actual <= (1.0 + 3 * width) * optimal) {
            return max2;
        } else if (actual >= (1.0 - 4 * width) * optimal && actual <= (1.0 + 4 * width) * optimal) {
            return max1;
        } else if (actual >= (1.0 - 5 * width) * optimal && actual <= (1.0 + 5 * width) * optimal) {
            return max0;
        } else if (actual >= (1.0 - 6 * width) * optimal && actual <= (1.0 + 6 * width) * optimal) {
            return -max1;
        } else if (actual >= (1.0 - 7 * width) * optimal && actual <= (1.0 + 7 * width) * optimal) {
            return -max2;
        } else if (actual >= (1.0 - 8 * width) * optimal && actual <= (1.0 + 8 * width) * optimal) {
            return -max3;
        } else if (actual >= (1.0 - 9 * width) * optimal && actual <= (1.0 + 9 * width) * optimal) {
            return -max4;
        } else {
            return -max5;
        }
    }

    public static double returnMin() {
        return -max5;
    }

    public static double returnNegativMean() {
        return -max2;
    }
    
    public static double returnPositivMean(){
        return max2;
    }
    
    public static double getMax(){
        return max5;
    }
}
