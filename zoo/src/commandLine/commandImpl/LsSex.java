package commandLine.commandImpl;

import basicGui.FormattingDisplay;
import commandLine.Command;
import launch.play.Play;
import zoo.animal.reproduction.Sex;

/**
 *
 * @author doyenm
 */
public class LsSex implements Command{
     Play play;

    public LsSex(Play play) {
        this.play = play;
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
    public String execute(String[] cmd) {
        this.success = success;
        return FormattingDisplay.formattingArrayList(Sex.UNKNOWN.list());
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length == 2 && cmd[0].equals("sex") && cmd[1].equals("ls")) {
            return true;
        }
        return false;
    }
}
