package launch.play;

import java.util.ResourceBundle;
import launch.options.Option;
import commandLine.CommandManager;
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
abstract public class Play {
    
        @Getter
    public ResourceBundle bundle;
     @Getter @Setter
    public Option option;
    
    @Getter @Setter
    public IZoo zoo;
    @Getter @Setter
    private CommandManager manager;

    public Play(ResourceBundle bundle, Option opt){
          this.zoo = new Zoo();
        this.zoo.setOption(opt);
        Diet.NONE.setOption(opt);
        Sex.UNKNOWN.setOption(opt);
        Ecoregion.UNKNOWN.setOption(opt);
        ConservationStatus.UNKNOWN.setOption(opt);
        Family.UNKNOWN.setOption(opt);
        Biome.NONE.setOption(opt);
        Size.UNKNOWN.setOption(opt);
        this.bundle = bundle;
        this.option = opt;
    }
    
}
