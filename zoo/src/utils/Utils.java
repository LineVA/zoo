package utils;

/**
 *
 * @author doyenm
 */
public class Utils {

    public static boolean isPositivOrNull(int test) {
        return test >= 0;
    }

    public static boolean isPositivOrNull(double test) {
        return test >= 0.0;
    }

    public static boolean isPositiv(int test) {
        return test > 0;
    }

    public static boolean isPositiv(double test) {
        return test > 0.0;
    }

    public static double truncate(double initial) {
        int point = Double.toString(initial).indexOf(".");
        if (point != -1) {
            int size = point + 3;
            if (Double.toString(initial).length() > size + 1) {
                return Double.parseDouble(Double.toString(initial).substring(0, size));
            }
        }
        return initial;
    }
}
