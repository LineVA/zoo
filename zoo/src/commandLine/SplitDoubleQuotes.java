package commandLine;

import java.util.ArrayList;

/**
 *
 * @author doyenm
 */
public class SplitDoubleQuotes {

    public static String[] split(String str) {
        ArrayList<String> array = new ArrayList<>();
        String[] split = str.split(" ");
        boolean end = false;
        String current = "";
        for (int i = 0; i < split.length; i++) {
            if (end) {
                if (split[i].endsWith("\"")) {
                    if (current.equals("") || split[i].equals("\"")) {
                        current += split[i].substring(0, split[i].length() - 1);
                    } else {
                        current += " " + split[i].substring(0, split[i].length() - 1);
                    }
                    array.add(current);
                    current = "";
                    end = false;
                } else {
                    current += " " + split[i];
                }
            } else {
                if (split[i].startsWith("\"")) {
                    if (split[i].endsWith("\"") && split[i].length() > 1) {
                        array.add(split[i].substring(1, split[i].length() - 1));
                    } else {
                        current += split[i].substring(1);
                        end = true;
                    }
                } else {
                    array.add(split[i]);
                }
            }
        }
        String[] aux = new String[array.size() - 1];
        return array.toArray(aux);
    }
}
