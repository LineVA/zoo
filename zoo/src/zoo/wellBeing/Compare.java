package zoo.wellBeing;

/**
 *
 * @author doyenm
 */
public class Compare {

    public static double compare(double optimal, double actual) {
        if ((actual >= 0.9 * optimal) & (actual <= 1.1 * optimal)) {
            return 10;
        } else if (actual >= 0.8 * optimal && actual <= 1.2 * optimal) {
            return 9;
        } else if (actual >= 0.7 * optimal && actual <= 1.3 * optimal) {
            return 8;
        } else if (actual >= 0.6 * optimal && actual <= 1.4 * optimal) {
            return 7;
        } else if (actual >= 0.5 * optimal && actual <= 1.5 * optimal) {
            return 6;
        } else if (actual >= 0.4 * optimal && actual <= 1.6 * optimal) {
            return 5;
        } else if (actual >= 0.3 * optimal && actual <= 1.7 * optimal) {
            return 4;
        } else if (actual >= 0.2 * optimal && actual <= 1.8 * optimal) {
            return 3;
        } else if (actual >= 0.1 * optimal && actual <= 1.9 * optimal) {
            return 2;
        } else {
            return 1;
        }
    }
}
