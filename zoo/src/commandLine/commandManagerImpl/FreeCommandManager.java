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

/**
 *
 * @author doyenm
 */
public class FreeCommandManager extends CommandManager {

    private final Iterable<Command> initialCommands;

    public boolean isInitiate = false;

    public FreeCommandManager(Play play, Option option) {
        super(play, option);
        super.setFirstLine(super.getOption().getGeneralCmdBundle().getString("WELCOME"));
        initialCommands = asList(new CreateZoo(play), new LoadZoo(play));
    }

    public String run(String cmd) {
        String[] parse = SplitDoubleQuotes.split(cmd);
        if (isInitiate) {
            for (Command command : super.getPlayCommands()) {
                if (command.canExecute(parse)) {
                    String result = command.execute(parse);
                    this.isInitiate |= command.hasInitiateAZoo();
                    return result;
                }
            }
            return super.getOption().getGeneralCmdBundle().getString("UNKNOWN_CMD");
        } else {
            for (Command command : initialCommands) {
                if (command.canExecute(parse)) {
                    String result = command.execute(parse);
                    this.isInitiate = command.hasInitiateAZoo();
                    return result;
                }
            }
            return super.getOption().getGeneralCmdBundle().getString("BEGINNING_CMD");
        }

    }
}
