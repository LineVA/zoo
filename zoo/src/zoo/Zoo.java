package zoo;

import exception.IncorrectDimensionsException;
import exception.name.AlreadyUsedNameException;
import exception.name.UnknownNameException;
import gui.FormattingDisplay;
import java.util.HashMap;
import lombok.Getter;

/**
 *
 * @author doyenm
 */
public class Zoo {

    @Getter
    private String name;
    @Getter
    private int width;
    @Getter
    private int height;
    @Getter
    private HashMap<String, Paddock> paddocks;

    public Zoo(String name, int width, int height) throws IncorrectDimensionsException {
        this.name = name;
        if (width < 1 || height < 1) {
            throw new IncorrectDimensionsException("A zoo must have a width and "
                    + "an height greater or equals to 1 each.");
        } else {
            this.width = width;
            this.height = height;
        }
        paddocks = new HashMap<>();
    }

    public int addPaddock(String paddockName, int x, int y, int width, int height)
            throws AlreadyUsedNameException {
        if (paddocks.containsKey(paddockName)) {
            throw new AlreadyUsedNameException("A paddock with this name is already existing. Please choose another one.");
        } else {
            Paddock paddock = new Paddock(paddockName, x, y, width, height);
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
    
   

    public String detailedPaddock(String name) throws UnknownNameException {
        boolean found = false;
        final Paddock detailed;
          for (HashMap.Entry<String, Paddock> entry : paddocks.entrySet()) {
              if(entry.getKey().equals(name)){
                  found = true;
                  detailed = entry.getValue();
                  break;
              }
          }
        if(found){
            return FormattingDisplay.idDisplay(name);
        } else {
            throw new UnknownNameException("This paddock does not exist.");
        }
    }

}
