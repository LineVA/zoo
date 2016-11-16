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
//        for(int j=0 ; j<aux.length ; j++){
//           aux2 = SplitDoubleQuotes.split(aux[j]);
        for (int i = 0; i < aux.length; i++) {
            if (aux[i].substring(0, 1).equals("\"") && aux[i].endsWith("\"")) {
                auxList.add(aux[i].substring(1, aux[i].length() - 1));
            } else if (aux[i].substring(0, 1).equals("\"")) {
                auxList.add(aux[i].substring(1));
            } else if (aux[i].endsWith("\"")) {
                auxList.add(aux[i].substring(0, aux[i].length() - 1));
            } else {
                auxList.add(aux[i]);
            }
        }
        return auxList;
    }
}
