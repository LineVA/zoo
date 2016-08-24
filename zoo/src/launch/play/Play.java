package launch.play;

import commandLine.CommandManager;
import zoo.IZoo;

/**
 *
 * @author doyenm
 */
public interface Play {

    public IZoo getZoo();
    
    public void setZoo(IZoo zoo);
    
    public CommandManager getManager();
    
}
