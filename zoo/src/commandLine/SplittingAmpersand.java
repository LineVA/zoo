package commandLine;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author doyenm
 */
public class SplittingAmpersand {
 public static Set<String> split(String str) {
        String[] aux = str.split("&+");
        Set<String> auxList = new HashSet<>();
        String[] aux2;
        for(int j=0 ; j<aux.length ; j++){
           aux2 = SplitDoubleQuotes.split(aux[j]);
        for(int i = 0 ; i<aux2.length ; i++){
            auxList.add(aux2[i]);
        }
 }
        return auxList;
    }
}
