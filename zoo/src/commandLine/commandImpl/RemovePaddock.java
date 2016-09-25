package commandLine.commandImpl;

import commandLine.Command;
import exception.name.EmptyNameException;
import exception.name.UnknownNameException;
import launch.play.Play;
import zoo.paddock.IPaddock;

/**
 *
 * @author doyenm
 */
public class RemovePaddock implements Command {

    Play play;

    public RemovePaddock(Play play) {
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
        try {
            IPaddock pad = this.play.getZoo().findPaddockByName(cmd[2]);
            this.play.getZoo().removePaddock(pad);
            this.success = true;
            return this.play.getOption().getGeneralCmdBundle()
                    .getString("ANIMAL_REMOVE_SUCCESS");
        } catch (EmptyNameException | UnknownNameException ex) {
            return ex.getMessage();
        }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length == 3) {
            if ((cmd[0].equals("paddock") || cmd[0].equals("pad")) && cmd[1].equals("remove")) {
                return true;
            }
        }
        return false;
    }
}
