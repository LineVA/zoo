
package commandLine.commandImpl;

import basicGui.FormattingDisplay;
import commandLine.Command;
import launch.play.Play;
import zoo.animal.specie.Family;

/**
 *
 * @author doyenm
 */
public class LsFamily implements Command{
 Play play;

    public LsFamily(Play play) {
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
        return FormattingDisplay.formattingArrayList(Family.UNKNOWN.list());
    }
    
    @Override
    public boolean canExecute(String[] cmd) {
        return cmd.length == 2 && cmd[0].equals("family") && cmd[1].equals("ls");
    }
}
