package launch.play;

import java.util.ResourceBundle;
import launch.options.Option;
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
    public Option option;
    
    @Getter @Setter
    public IZoo zoo;

    public FreePlayImpl(ResourceBundle bundle, Option opt) {
        this.zoo = new Zoo();
        this.zoo.setOption(opt);
        this.bundle = bundle;
        this.option = opt;
    }

    
    
    
}
