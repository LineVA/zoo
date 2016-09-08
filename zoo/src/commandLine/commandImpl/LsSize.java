package commandLine.commandImpl;

import basicGui.FormattingDisplay;
import commandLine.Command;
import launch.play.Play;
import zoo.animal.feeding.Size;

/**
 *
 * @author doyenm
 */
public class LsSize implements Command{
  Play play;

    public LsSize(Play play) {
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
        return FormattingDisplay.formattingArrayList(Size.UNKNOWN.list());
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length == 2 && cmd[0].equals("size") && cmd[1].equals("ls")) {
            return true;
        }
        return false;
    }
}
