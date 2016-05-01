package gui;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author doyenm
 */
public class FormattingDisplay {

    public static String singleLine(String str) {
        return str;
    }
    
   private static String d2ArrayToString(String[][] array){
       String finalStr = "";
       for(int i = 0; i<array[0].length - 1; i++){
           for(int j = 0; j<array[1].length ; j++){
               finalStr += array[i][j];
           }
           finalStr += "\n";
       }
        for(int k = 0; k<array[1].length ; k++){
               finalStr += array[array[0].length - 1][k];
           }
        return finalStr;
   }

    private static String[][] contourMap(int width, int height){
        String[][] contour = new String[width + 2][height + 2];
        // The horizontal contours
        for(int i = 0; i<contour[0].length ; i++){
            contour[0][i] = "#";
            contour[contour[0].length - 1][i] = "#";
        }
        // The vertical contours
         for(int i = 0; i<contour[1].length ; i++){
            contour[i][0] = "#";
            contour[i][contour[1].length - 1] = "#";
        }
        return contour;
    }
    
    public static String zooMap(int width, int height) {
        String finalStr = d2ArrayToString(contourMap(width, height));
        System.out.println(finalStr);
        return finalStr;
    }
    
    public static String idDisplay(String name){
        return " coco";
    }
    
    public static String formattingArrayList(ArrayList<String> list){
        Iterator it = list.iterator();
        String str = "";
        String finalStr = "";
        while(it.hasNext()){
             str = (String)it.next();
             if(it.hasNext()){
                 finalStr += str + "\n";
             } else {
                 finalStr += str;
             }
        }
        return finalStr;
    }
}
