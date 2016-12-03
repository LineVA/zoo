package basicGui;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import zoo.paddock.PaddockCoordinates;

/**
 *
 * @author doyenm
 */
public class FormattingDisplay {

    /**
     * Thickness of the contour of the map
     */
    final static int thicknessContour = 1;
    /**
     * Characters used to symbolize the paddocks on the map
     */
    final static char[] signArray = {'*', '&', '~', '-', '=', '@', '+', '?', '§', 'µ', '£',
        '$', 'a', 'z', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', 'q', 's', 'd',
        'f', 'g', 'h', 'j', 'k', 'l', 'm', 'w', 'x', 'c', 'v', 'b', 'n'};

    private static char[][] d2ArrayCharInitialize(char[][] array) {
        for (int i = 0; i < array[0].length; i++) {
            Arrays.fill(array[0], ' ');
        }
        return array;
    }

    private static String d2ArrayToString(char[][] array) {
        String finalStr = "";
        for (int i = 0; i < array[0].length - 1; i++) {
            for (int j = 0; j < array.length; j++) {
                finalStr += array[j][i];
            }
            finalStr += "\n";
        }
        for (int j = 0; j < array.length; j++) {
            finalStr += array[j][array[0].length - 1];
        }
        return finalStr;
    }

    private static char[][] contourMap(char[][] array) {
        for (int i = 0; i < array[0].length; i++) {
            array[0][i] = '#';
            array[array.length - 1][i] = '#';
        }
        for (int j = 0; j < array.length; j++) {
            array[j][0] = '#';
            array[j][array[0].length - 1] = '#';
        }
        return array;
    }

    private static char[][] paddockMap(PaddockCoordinates coor, char[][] array, char sign) {
        for (int i = coor.getX() + thicknessContour; i < coor.getX() + coor.getWidth() + thicknessContour; i++) {
            for (int j = coor.getY() + thicknessContour; j < coor.getY() + coor.getHeight() + thicknessContour; j++) {
                array[i][j] = sign;
            }
        }
        return array;
    }

    /**
     * Formating of the map
     *
     * @param list List of PaddockCoordinates ; the first must represent the
     * dimensions of the zoo
     * @return
     */
    public static String zooMap(List<PaddockCoordinates> list) {
        char[][] array = new char[list.get(0).getWidth() + 2][list.get(0).getHeight() + 2];
        array = d2ArrayCharInitialize(array);
        array = contourMap(array);
        Iterator it = list.iterator();
        PaddockCoordinates next;
        // We do not want to use the first PaddockCoordinates : they represent 
        // the dimensions of the zoo, not a paddock.
        it.next();
        int cpt = 0;
        while (it.hasNext()) {
            next = (PaddockCoordinates) it.next();
            array = paddockMap(next, array, signArray[cpt % signArray.length]);
            cpt++;
        }
        return d2ArrayToString(array);
    }

    /**
     * Layout for a list of Strings
     *
     * @param list List of strings we need to display
     * @return a formatting String corresponding to the parameter
     */
    public static String formattingList(List<String> list) {
        String finalStr = "";
        for (String str : list) {
            finalStr += str + "\n";
        }
        return finalStr;
    }
}
