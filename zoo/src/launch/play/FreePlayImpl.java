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
import zoo.animal.feeding.Size;
import zoo.animal.reproduction.Sex;
import zoo.animal.specie.Family;
import zoo.paddock.biome.Biome;
import zoo.paddock.biome.Ecoregion;

/**
 *
 * @author doyenm
 */
public class FreePlayImpl extends Play {

//    @Getter
//    public ResourceBundle bundle;
//    @Getter
//    @Setter
//    public Option option;
//
//    @Getter
//    @Setter
//    public IZoo zoo;
//    @Getter
//    private CommandManager manager;

    public FreePlayImpl(ResourceBundle bundle, Option opt) {
        super(bundle, opt);
        super.setManager(new FreeCommandManager(this, opt));
    }
}
