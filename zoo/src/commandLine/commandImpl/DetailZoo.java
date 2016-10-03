package commandLine.commandImpl;

import basicGui.FormattingDisplay;
import commandLine.Command;
import commandLine.ReturnExec;
import commandLine.TypeReturn;
import launch.play.Play;

/**
 *
 * @author doyenm
 */
public class DetailZoo implements Command {

    Play play;

    public DetailZoo(Play play) {
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
    public ReturnExec execute(String[] cmd) {
        this.success = true;
        return new ReturnExec(FormattingDisplay.formattingArrayList(this.play.getZoo().info()), TypeReturn.SUCCESS);
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length == 1) {
            if (cmd[0].equals("zoo")) {
                return true;
            }
        }
        return false;
    }
}
