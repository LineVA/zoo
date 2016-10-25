package commandLine.commandManagerImpl;

import commandLine.AbstractCommand;
import commandLine.Command;
import commandLine.CommandManager;
import commandLine.ReturnExec;
import commandLine.SplitDoubleQuotes;
import commandLine.TypeReturn;
import commandLine.commandImpl.CreateZoo;
import commandLine.commandImpl.LoadZoo;
import commandLine.commandImpl.Options;
import commandLine.commandImpl.Man;
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

    public boolean isSaving = false;

    public FreeCommandManager(Play play, Option option) {
        super(play, option);
        super.setFirstLine(super.getOption().getGeneralCmdBundle().getString("WELCOME"));
        initialCommands = asList(new CreateZoo(play), new LoadZoo(play), new Options(play), new Man(play));
    }

    private ReturnExec isCurrentlySaving(String[] parse) {
        this.isSaving = false;
        return super.getSave().confirmSaving(parse);
    }

    private ReturnExec isNotCurrentlySaving(String[] parse) {
        for (AbstractCommand command : super.getPlayCommands()) {
            if (command.canExecute(parse)) {
                ReturnExec result = command.execute(parse);
                this.isInitiate |= command.isInitiate();
                this.isSaving = command.isSaving();
                return result;
            }
        }
        return new ReturnExec(
                super.getOption().getGeneralCmdBundle().getString("UNKNOWN_CMD"), TypeReturn.ERROR);
    }

    private ReturnExec hasBeenInitiate(String[] parse) {
        if (isSaving) {
            return isCurrentlySaving(parse);
        }
        return isNotCurrentlySaving(parse);
    }

    private ReturnExec hasNotBeenYetInitiate(String[] parse) {
        for (Command command : initialCommands) {
            if (command.canExecute(parse)) {
                ReturnExec result = command.execute(parse);
                this.isInitiate = command.isInitiate();
                return result;
            }
        }
        return new ReturnExec(
                super.getOption().getGeneralCmdBundle().getString("BEGINNING_CMD"), TypeReturn.ERROR);
    }

    @Override
    public ReturnExec run(String cmd) {
        String[] parse = SplitDoubleQuotes.split(cmd);
        if (isInitiate) {
            return hasBeenInitiate(parse);
        } 
        return hasNotBeenYetInitiate(parse);
    }
}
