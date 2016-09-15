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
import zoo.paddock.biome.Continent;
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
        Continent.UNKNOWN.setOption(opt);
        this.bundle = bundle;
        this.option = opt;
    }
    
    public void updateOption(){
         Diet.NONE.setOption(this.option);
        Sex.UNKNOWN.setOption(this.option);
        Ecoregion.UNKNOWN.setOption(this.option);
        ConservationStatus.UNKNOWN.setOption(this.option);
        Family.UNKNOWN.setOption(this.option);
        Biome.NONE.setOption(this.option);
        Size.UNKNOWN.setOption(this.option);
        Continent.UNKNOWN.setOption(this.option);
    }
}
