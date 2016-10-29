package utils;

import java.util.ArrayList;
import java.util.Set;

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
    
     public static ArrayList<Integer> convertToArrayListOfInteger(Set<String> strings) 
             throws java.lang.NumberFormatException{
        ArrayList<Integer> integers = new ArrayList<>();
        for (String str : strings) {
            integers.add(Integer.parseInt(str));
        }
        return integers;
    }
}
