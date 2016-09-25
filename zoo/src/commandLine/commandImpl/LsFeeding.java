package commandLine.commandImpl;

import basicGui.FormattingDisplay;
import commandLine.Command;
import launch.play.Play;
import zoo.animal.feeding.Diet;

/**
 *
 * @author doyenm
 */
public class LsFeeding implements Command {
     Play play;

    public LsFeeding(Play play) {
        this.play = play;
    }

    @Override
    public String execute(String[] cmd) {
        this.success = true;
        return FormattingDisplay.formattingArrayList(Diet.NONE.list());
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
