package commandLine;

import zoo.IZoo;
import zoo.Zoo;

/**
 *
 * @author doyenm
 */
public interface Command {
    
    public IZoo zoo = new Zoo();
    
    public String execute(String[] cmd);
    
    public boolean canExecute(String[] cmd);
    
}
