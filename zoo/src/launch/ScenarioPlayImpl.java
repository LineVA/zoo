package launch;

import launch.play.Play;
import lombok.Getter;
import lombok.Setter;
import zoo.IZoo;
import zoo.Zoo;

/**
 *
 * @author doyenm
 */
public class ScenarioPlayImpl implements Play{

@Getter @Setter
    public IZoo zoo;

    public ScenarioPlayImpl() {
        this.zoo = new Zoo();
    }    
    
}
