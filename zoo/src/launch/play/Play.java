package launch.play;

import java.util.ResourceBundle;
import launch.options.Option;
import commandLine.CommandManager;
import zoo.IZoo;

/**
 *
 * @author doyenm
 */
public interface Play {

    public ResourceBundle getBundle();

    public Option getOption();
    
    public void setOption(Option option);

    public IZoo getZoo();

    public void setZoo(IZoo zoo);

    public CommandManager getManager();
    
}
