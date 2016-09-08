package launch.play;

import commandLine.CommandManager;
import java.util.ResourceBundle;
import launch.options.Option;
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
public class ScenarioPlayImpl extends Play {

    public ScenarioPlayImpl(ResourceBundle bundle, Option opt) {
        super(bundle, opt);
    }

}
