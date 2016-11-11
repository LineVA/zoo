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
import zoo.animal.reproduction.Sex;
import zoo.animal.conservation.BreedingProgramme;
import zoo.animal.specie.Family;
import zoo.animal.specie.Size;
import zoo.animalKeeper.Task;
import zoo.paddock.PaddockTypes;
import zoo.paddock.biome.Biome;
import zoo.paddock.biome.Continent;
import zoo.paddock.biome.Ecoregion;

/**
 *
 * @author doyenm
 */
abstract public class Play {

    @Getter
    @Setter
    private Option option;

    @Getter
    @Setter
    private IZoo zoo;
    @Getter
    @Setter
    private CommandManager manager;

    public Play(Option opt) {
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
        PaddockTypes.UNKNOWN.setOption(opt);
        Task.UNKNOWN.setOption(opt);
        BreedingProgramme.NONE.setOption(opt);
        this.option = opt;
    }

    public void updateOption() {
        Diet.NONE.setOption(this.option);
        Sex.UNKNOWN.setOption(this.option);
        Ecoregion.UNKNOWN.setOption(this.option);
        ConservationStatus.UNKNOWN.setOption(this.option);
        Family.UNKNOWN.setOption(this.option);
        Biome.NONE.setOption(this.option);
        Size.UNKNOWN.setOption(this.option);
        Continent.UNKNOWN.setOption(this.option);
        PaddockTypes.UNKNOWN.setOption(this.option);
        Task.UNKNOWN.setOption(this.option);
    }
}
