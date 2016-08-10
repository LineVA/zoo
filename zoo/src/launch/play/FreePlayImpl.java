package launch.play;

import java.util.ResourceBundle;
import launch.play.Play;
import lombok.Getter;
import lombok.Setter;
import zoo.IZoo;
import zoo.Zoo;

/**
 *
 * @author doyenm
 */
public class FreePlayImpl implements Play{

    @Getter
    public ResourceBundle bundle;
    
    @Getter @Setter
    public IZoo zoo;

    public FreePlayImpl(ResourceBundle bundle) {
        this.zoo = new Zoo();
        this.bundle = bundle;
    }
    
    
}
