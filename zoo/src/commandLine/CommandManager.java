package commandLine;

import commandLine.commandImpl.BiomeAttributesPaddock;
import commandLine.commandImpl.BiomePad;
import commandLine.commandImpl.CreateAnimal;
import commandLine.commandImpl.CreatePaddock;
import commandLine.commandImpl.CreateZoo;
import commandLine.commandImpl.DetailAnimal;
import commandLine.commandImpl.DetailPad;
import commandLine.commandImpl.DetailSpecie;
import commandLine.commandImpl.DetailZoo;
import commandLine.commandImpl.Evaluate;
import commandLine.commandImpl.FeedingAnimal;
import commandLine.commandImpl.LoadZoo;
import commandLine.commandImpl.LsAnimal;
import commandLine.commandImpl.LsBiome;
import commandLine.commandImpl.LsConservation;
import commandLine.commandImpl.LsEcoregion;
import commandLine.commandImpl.LsFamily;
import commandLine.commandImpl.LsFeeding;
import commandLine.commandImpl.LsPaddock;
import commandLine.commandImpl.LsSex;
import commandLine.commandImpl.LsSpecie;
import commandLine.commandImpl.MapZoo;
import commandLine.commandImpl.Options;
import commandLine.commandImpl.RemoveAnimal;
import commandLine.commandImpl.RemovePaddock;
import commandLine.commandImpl.SaveZoo;
import static java.util.Arrays.asList;
import launch.options.Option;
import launch.play.Play;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author doyenm
 */
public abstract class CommandManager {

    Play play;
    @Getter
    Iterable<Command> playCommands;
      @Getter
      @Setter
    private String firstLine;
      @Getter
      private Option option;
    
    public CommandManager(Play play, Option option){
        this.option = option;
          this.play = play;
            // For Paddock and Animal : Ls must be before Detail
        playCommands = asList(new CreateZoo(play), new DetailZoo(play),
                new CreatePaddock(play),
                new LsPaddock(play), new MapZoo(play), new DetailPad(play),
                new Evaluate(play), new BiomePad(play), new BiomeAttributesPaddock(play),
                new CreateAnimal(play), new LsAnimal(play), new DetailAnimal(play),
                new FeedingAnimal(play),
                new LsSex(play), new LsConservation(play),
                new RemoveAnimal(play), new RemovePaddock(play), 
                new LsBiome(play), new LsEcoregion(play),
                new LsSpecie(play), new DetailSpecie(play), new LsFamily(play),
                new LsFeeding(play),
                new SaveZoo(play), new LoadZoo(play), new Options(play));
    }

    public abstract String run(String cmd);

}
