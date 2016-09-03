package launch.play;

import commandLine.CommandManager;
import java.util.ResourceBundle;
import launch.options.Option;
import launch.play.Play;
import lombok.Getter;
import lombok.Setter;
import zoo.IZoo;
import zoo.Zoo;
import zoo.animal.feeding.Diet;
import zoo.animal.reproduction.Sex;

/**
 *
 * @author doyenm
 */
public class ScenarioPlayImpl implements Play {

     @Getter
    public ResourceBundle bundle;
     @Getter @Setter
    public Option option;
    
    @Getter @Setter
    public IZoo zoo;
    @Getter
    private CommandManager manager;

    public ScenarioPlayImpl(ResourceBundle bundle, Option opt) {
        this.zoo = new Zoo();
        this.zoo.setOption(opt);
        Diet.NONE.setOption(opt);
        Sex.UNKNOWN.setOption(opt);
        this.bundle = bundle;
        this.option = opt;
    }

}
