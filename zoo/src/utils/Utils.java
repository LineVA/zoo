package utils;

import exception.IncorrectDataException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import zoo.animal.Animal;

/**
 *
 * @author doyenm
 */
public class Utils {

    public static boolean isBetween(int test, int min, int max) {
        return (test >= min) && (test <= max);
    }

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

    public static Couple transformIntoYearsAndMonthsFromMonths(int initMonths)
            throws IncorrectDataException {
        if (initMonths < 0) {
            throw new IncorrectDataException("");
        }
        return new Couple(initMonths / 12, initMonths % 12);
    }

    public static String infoAge(int age, ResourceBundle bundle) throws IncorrectDataException {
        Couple ageInYears = Utils.transformIntoYearsAndMonthsFromMonths(age);
        String info = "";
        if (ageInYears.getA() > 1) {
            info += ageInYears.getA() + " " + bundle.getString("YEARS");
        } else {
            info += ageInYears.getA() + " " + bundle.getString("YEAR");
        }
        if (ageInYears.getB() > 1) {
            info += ageInYears.getB() + " " + bundle.getString("MONTHS");
        } else {
            info += ageInYears.getB() + " " + bundle.getString("MONTH");
        }
        return info;
    }

    public static String infoGenealogy(String mother, String father, ResourceBundle bundle) {
        String info = "";
        String motherName = (mother != null) ? mother : bundle.getString("UNKNOWN_MOTHER");
        String fatherName = (father != null) ? father : bundle.getString("UNKNOWN_FATHER");
        info += bundle.getString("MOTHER") + motherName
                + ", " + bundle.getString("FATHER") + fatherName;
        return info;
    }
}
