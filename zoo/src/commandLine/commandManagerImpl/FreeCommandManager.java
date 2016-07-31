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
import commandLine.commandImpl.LsFeeding;
import commandLine.commandImpl.LsPaddock;
import commandLine.commandImpl.LsSpecie;
import commandLine.commandImpl.MapZoo;
import commandLine.commandImpl.RemoveAnimal;
import commandLine.commandImpl.RemovePaddock;
import commandLine.commandImpl.SaveZoo;
import static java.util.Arrays.asList;
import launch.play.Play;

/**
 *
 * @author doyenm
 */
public class FreeCommandManager implements CommandManager{
  Play play;
    private final Iterable<Command> playCommands;
    private final Iterable<Command> initialCommands;

    public boolean isInitiate = false;

    public FreeCommandManager(Play play) {
        this.play = play;
        // For Paddock and Animal : Ls must be before Detail
        playCommands = asList(new CreateZoo(play), new DetailZoo(play),
                new CreatePaddock(play),
                new LsPaddock(play), new MapZoo(play), new DetailPad(play),
                new Evaluate(play), new BiomePad(play), new BiomeAttributesPaddock(play),
                new CreateAnimal(play), new LsAnimal(play), new DetailAnimal(play),
                new FeedingAnimal(play),
                new RemoveAnimal(play), new RemovePaddock(play),
                new LsSpecie(play), new DetailSpecie(play),
                new LsFeeding(play),
                new SaveZoo(play), new LoadZoo(play));
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
            return "Unknown command";
        } else {
            for (Command command : initialCommands) {
                if (command.canExecute(parse)) {
                    String result = command.execute(parse);
                    this.isInitiate = command.hasInitiateAZoo();
                    return result;
                }
            }
            return "There is only two command to create a zoo : "
                    + "see 'man zoo' and 'man load' for more information.";
        }

    }
}
