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

    public static String zooMap(int width, int height) {
        String upAndDown = "#";
        String finalStr = "";
        String interStr = "#";
        for (int i = 0; i < width; i++) {
            upAndDown += "#";
            interStr += " ";
        }
        upAndDown += "#";
        interStr += "#";
        finalStr += upAndDown + "\n";
        for (int j = 0; j < height; j++) {
            finalStr += interStr + "\n";
        }
        finalStr += upAndDown;
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
