package zoo;

import java.util.HashMap;

/**
 *
 * @author doyenm
 */
public class Zoo {

    private String name;
    private HashMap<String, Paddock> paddocks;

    public String getName() {
        return name;
    }

    public HashMap<String, Paddock> getPaddocks() {
        return paddocks;
    }

    public Zoo(String name) {
        this.name = name;
        paddocks = new HashMap<>();
    }

    public int addPaddock(String paddockName) throws AlreadyUsedNameException {
        if (paddocks.containsKey(paddockName)) {
            throw new AlreadyUsedNameException("A paddock with this name is already existing. Please choose another one.");
        } else {
            Paddock paddock = new Paddock(paddockName);
            this.paddocks.put(paddockName, paddock);
            return 0;
        }
    }

    public String listPaddock() {
        String list = "";
        for (HashMap.Entry<String, Paddock> entry : paddocks.entrySet()) {
            list += entry.getKey() + "\n";
        }
        return list;
    }

}
