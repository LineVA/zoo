package utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

    public static List<Integer> convertToListOfInteger(Set<String> strings)
            throws java.lang.NumberFormatException {
        List<Integer> integers = new ArrayList<>();
        for (String str : strings) {
            integers.add(Integer.parseInt(str));
        }
        return integers;
    }

    public static Set<String> toUpperCase(Set<String> strings) {
        Set<String> uppers = new HashSet<>();
        for (String str : strings) {
            uppers.add(str.toUpperCase());
        }
        return uppers;
    }

    public static Set<Integer> convertToSetOfInteger(Set<String> strings)
            throws java.lang.NumberFormatException {
        Set<Integer> integers = new HashSet<>();
        for (String str : strings) {
            integers.add(Integer.parseInt(str));
        }
        return integers;
    }
}
