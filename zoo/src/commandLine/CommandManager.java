package commandLine;

import commandLine.commandImpl.*;
import static java.util.Arrays.asList;
import launch.options.Option;
import launch.play.Play;
import lombok.Getter;
import lombok.Setter;

/**
 * The abstract command manager : determine how the command lines are checked 
 * @author doyenm
 */
public abstract class CommandManager {

    Play play;
    @Getter
    Iterable<AbstractCommand> playCommands;
      @Getter
      @Setter
    private String firstLine;
      @Getter
      private Option option;
      @Getter
      private SaveZoo save;
      
    
    public CommandManager(Play play, Option option){
        this.option = option;
          this.play = play;
          this.save = new SaveZoo(play);
            // For Paddock, Animal, Specie and Animal Keeper : Ls must be before Detail
          // and Create & remove before Ls
        playCommands = asList(new CreateZoo(play), new DetailZoo(play),
                new CreatePaddock(play), new RemovePaddock(play), 
                new LsPaddock(play), new MapZoo(play), new DetailPad(play),
                new Evaluate(play), new BiomePad(play), new BiomeAttributesPaddock(play),
                new CreateAnimal(play),  new RemoveAnimal(play), 
                new LsAnimal(play), new DetailAnimal(play),
                new FeedingAnimal(play),
                new LsSex(play), new LsConservation(play),
                new LsBiome(play), new LsEcoregion(play), new LsContinent(play),
                new LsPaddockType(play), new PadTypePad(play),
                new LsSpecie(play), new DetailSpecie(play), new LsFamily(play),
                new LsFeeding(play), new LsSize(play),
                 save, new LoadZoo(play),
                new Options(play), new ZooCharacteristics(play),
                new Documentation(play),
                new LsTask(play),
                new LsAnimalKeeper(play), new DetailAnimalKeeper(play),
                new AddTimedPaddocks(play), new CreateAnimalKeeper(play), 
                new AddTimedTasksPerPaddock(play), new RemoveAnimalKeeper(play),
                new RemoveTimedPaddocks(play), new RemoveTimedTasksPerPaddock(play),
                new Man(play));
    }

    public abstract ReturnExec run(String cmd);

}
