package launch.play;

import java.util.ResourceBundle;
import zoo.IZoo;
import zoo.Zoo;

/**
 *
 * @author doyenm
 */
public interface Play {
    
    public ResourceBundle getBundle();

    public IZoo getZoo();
    
    public void setZoo(IZoo zoo);
    
}
