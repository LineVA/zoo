package commandLine.commandImpl;

import basicGui.FormattingDisplay;
import commandLine.AbstractCommand;
import commandLine.Command;
import commandLine.ReturnExec;
import commandLine.TypeReturn;
import launch.play.Play;
import zoo.animal.feeding.Diet;

/**
 *
 * @author doyenm
 */
public class LsFeeding extends AbstractCommand implements Command {

    Play play;

    public LsFeeding(Play play) {
        this.play = play;
    }

    @Override
    public ReturnExec execute(String[] cmd) {
        this.success = true;
        return new ReturnExec(FormattingDisplay.formattingArrayList(Diet.NONE.list()), TypeReturn.SUCCESS);
    }

    @Override
    public boolean hasInitiateAZoo() {
        return false;
    }

    boolean success = false;

    @Override
    public boolean isSuccess() {
        return this.success;
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length == 2) {
            if (cmd[0].equals("diet")) {
                if (cmd[1].equals("ls")) {
                    return true;
                }
            }
        }
        return false;
    }
}
