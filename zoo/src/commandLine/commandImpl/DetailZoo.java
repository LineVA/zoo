package commandLine.commandImpl;

import basicGui.FormattingDisplay;
import commandLine.Command;
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
    public String execute(String[] cmd) {
        this.success = true;
        return (FormattingDisplay.formattingArrayList(this.play.getZoo().info()));
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
