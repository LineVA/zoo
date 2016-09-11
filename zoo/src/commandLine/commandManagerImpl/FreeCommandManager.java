package commandLine.commandManagerImpl;

import commandLine.Command;
import commandLine.CommandManager;
import commandLine.SplitDoubleQuotes;
import commandLine.commandImpl.CreateZoo;
import commandLine.commandImpl.LoadZoo;
import static java.util.Arrays.asList;
import launch.options.Option;
import launch.play.Play;

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

    @Override
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
