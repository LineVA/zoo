package commandLine.commandManagerImpl;

import commandLine.AbstractCommand;
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

    private final String LOAD = "load";
    private final String CREATE = "create";

    private final Iterable<AbstractCommand> initialCommands;

    private String currentChangingZooCommand;

    public boolean isChanging = false;

    public boolean isInitiate = false;

    public boolean isSaving = false;

    public FreeCommandManager(Play play, Option option) {
        super(play, option);
        super.setFirstLine(super.getOption().getGeneralCmdBundle().getString("WELCOME"));
        initialCommands = asList(super.getCreateZoo(), super.getLoad(), new Options(play), new Man(play));
    }

    private ReturnExec isCurrentlySaving(String[] parse) {
        this.isSaving = false;
        return super.getSave().confirmSaving(parse);
    }

    private ReturnExec isCurrentlyChanging(String[] parse) {
        this.isChanging = false;
        if (LOAD.equals(currentChangingZooCommand)) {
            return super.getLoad().confirmChangingZoo(parse);
        } else if(CREATE.equals(this.currentChangingZooCommand)){
            return super.getCreateZoo().confirmChangingZoo(parse);
        } 
        return null;
    }

    private ReturnExec isNotCurrentlySaving(String[] parse) {
        if (this.isChanging) {
            return isCurrentlyChanging(parse);
        } else {
            for (AbstractCommand command : super.getPlayCommands()) {
                if (command.canExecute(parse)) {
                    command.setChangingZoo(isChanging);
                    command.setInitiate(isInitiate);
                    ReturnExec result = command.execute(parse);
                    this.isInitiate |= command.isInitiate();
                    this.isSaving = command.isSaving();
                    this.isChanging = command.isChangingZoo();
                    if (this.isChanging) {
                        if (command instanceof LoadZoo) {
                            this.currentChangingZooCommand = LOAD;
                        } else if (command instanceof CreateZoo) {
                            this.currentChangingZooCommand = CREATE;
                        }
                    }
                    AbstractCommand tmp = super.getCreateZoo();
                    super.getLoad().setChangingZoo(command.isChangingZoo());
                    tmp = super.getLoad();
                    super.getCreateZoo().setChangingZoo(command.isChangingZoo());
                    return result;
                }
            }
            return new ReturnExec(
                    super.getOption().getGeneralCmdBundle().getString("UNKNOWN_CMD"), TypeReturn.ERROR);
        }
    }

    private ReturnExec hasBeenInitiate(String[] parse) {
        if (isSaving) {
            return isCurrentlySaving(parse);
        }
        return isNotCurrentlySaving(parse);
    }

    private ReturnExec hasNotBeenYetInitiate(String[] parse) {
        for (AbstractCommand command : initialCommands) {
            if (command.canExecute(parse)) {
                ReturnExec result = command.execute(parse);
                this.isInitiate = command.isInitiate();
                this.isChanging = command.isChangingZoo();
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
