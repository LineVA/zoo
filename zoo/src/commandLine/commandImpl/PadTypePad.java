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
public class PadTypePad implements Command {

    Play play;

    public PadTypePad(Play play) {
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
            IPaddock pad = this.play.getZoo().findPaddockByName(cmd[1]);
            pad.setPaddockType(cmd[3]);
            this.success = true;
            return this.play.getOption().getGeneralCmdBundle().getString("TYPE_PAD") + cmd[3];
        } catch(UnknownNameException | EmptyNameException ex){
            return ex.getMessage();
        }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        return cmd.length == 4 && (cmd[0].equals("pad") || "paddock".equals(cmd[0]))
                && (cmd[2].equals("-pT") || "--padType".equals(cmd[2]) || "--paddockType".equals(cmd[2]));
    }
}
