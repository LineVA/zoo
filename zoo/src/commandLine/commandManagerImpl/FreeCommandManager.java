package commandLine.commandManagerImpl;

import commandLine.Command;
import commandLine.CommandManager;
import commandLine.SplitDoubleQuotes;
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
import commandLine.commandImpl.LsFeeding;
import commandLine.commandImpl.LsPaddock;
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

/**
 *
 * @author doyenm
 */
public class FreeCommandManager implements CommandManager{
  Play play;
    private final Iterable<Command> playCommands;
    private final Iterable<Command> initialCommands;

    public boolean isInitiate = false;

    @Getter
    private String firstLine;
    private Option option;
    
    public FreeCommandManager(Play play, Option option) {
        this.play = play;
        this.option = option;
        this.firstLine = this.option.getGeneralCmdBundle().getString("WELCOME");
        // For Paddock and Animal : Ls must be before Detail
        playCommands = asList(new CreateZoo(play), new DetailZoo(play),
                new CreatePaddock(play),
                new LsPaddock(play), new MapZoo(play), new DetailPad(play),
                new Evaluate(play), new BiomePad(play), new BiomeAttributesPaddock(play),
                new CreateAnimal(play), new LsAnimal(play), new DetailAnimal(play),
                new FeedingAnimal(play),
                new RemoveAnimal(play), new RemovePaddock(play), new LsBiome(play),
                new LsSpecie(play), new DetailSpecie(play),
                new LsFeeding(play),
                new SaveZoo(play), new LoadZoo(play), new Options(play));
        initialCommands = asList(new CreateZoo(play), new LoadZoo(play));
    }

    public String run(String cmd) {
        // String[] parse = cmd.split(" ");
        String[] parse = SplitDoubleQuotes.split(cmd);
        if (isInitiate) {
            for (Command command : playCommands) {
                if (command.canExecute(parse)) {
                    String result = command.execute(parse);
                    this.isInitiate |= command.hasInitiateAZoo();
                    return result;
                }
            }
            return this.option.getGeneralCmdBundle().getString("UNKNOWN_CMD");
        } else {
            for (Command command : initialCommands) {
                if (command.canExecute(parse)) {
                    String result = command.execute(parse);
                    this.isInitiate = command.hasInitiateAZoo();
                    return result;
                }
            }
            return this.option.getGeneralCmdBundle().getString("BEGINNING_CMD");
        }

    }
}
