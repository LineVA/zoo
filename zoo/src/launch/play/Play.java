package launch.play;

import java.util.ResourceBundle;
import launch.options.Option;
import zoo.IZoo;
import zoo.Zoo;

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

}
