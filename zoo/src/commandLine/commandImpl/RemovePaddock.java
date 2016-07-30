
package commandLine.commandImpl;

import commandLine.Command;
import exception.name.EmptyNameException;
import exception.name.UnknownNameException;
import launch.Play;
import zoo.paddock.IPaddock;

/**
 *
 * @author doyenm
 */
public class RemovePaddock implements Command{

    Play play;

    public RemovePaddock(Play play) {
        this.play = play;
    }
    
      @Override
    public boolean hasInitiateAZoo() {
        return false;
    }

    @Override
    public String execute(String[] cmd) {
        try {
            IPaddock pad = this.play.zoo.findPaddockByName(cmd[2]);
            this.play.zoo.removePaddock(pad);
            return "This paddock has been removed.";
        } catch (EmptyNameException | UnknownNameException ex) {
            return ex.getMessage();
        }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length == 3) {
            if ((cmd[0].equals("paddock") || cmd[0].equals("pad"))&& cmd[1].equals("remove")) {
                return true;
            }
        }
        return false;
    }
}