package launch.play;

import java.util.ResourceBundle;
import launch.options.Option;
import commandLine.CommandManager;
import commandLine.commandManagerImpl.FreeCommandManager;
import lombok.Getter;
import lombok.Setter;
import zoo.IZoo;
import zoo.Zoo;
import zoo.animal.conservation.ConservationStatus;
import zoo.animal.feeding.Diet;
import zoo.animal.reproduction.Sex;
import zoo.paddock.biome.Ecoregion;

/**
 *
 * @author doyenm
 */
public class FreePlayImpl implements Play {

    @Getter
    public ResourceBundle bundle;
     @Getter @Setter
    public Option option;
    
    @Getter @Setter
    public IZoo zoo;
    @Getter
    private CommandManager manager;

    public FreePlayImpl(ResourceBundle bundle, Option opt) {
        this.zoo = new Zoo();
        this.manager = new FreeCommandManager(this);
        this.zoo.setOption(opt);
        Diet.NONE.setOption(opt);
        Sex.UNKNOWN.setOption(opt);
        Ecoregion.UNKNOWN.setOption(opt);
        ConservationStatus.UNKNOWN.setOption(opt); 
        this.bundle = bundle;
        this.option = opt;
    }
}
