package commandLine.commandImpl;

import commandLine.AbstractCommand;
import commandLine.ReturnExec;
import commandLine.TypeReturn;
import exception.name.EmptyNameException;
import exception.name.UnknownNameException;
import java.util.Arrays;
import launch.play.Play;
import utils.Constants;
import zoo.paddock.IPaddock;

/**
 *
 * @author doyenm
 */
public class RemovePaddock extends AbstractCommand  {

    public RemovePaddock(Play play) {
        super(play);
    }

    @Override
    public ReturnExec execute(String[] cmd) {
        try {
            IPaddock pad = super.getPlay().getZoo().findPaddockByName(cmd[2]);
            super.getPlay().getZoo().removePaddock(pad);
            super.setSuccess(true);
            return new ReturnExec(super.getPlay().getOption().getGeneralCmdBundle()
                    .getString("PADDOCK_REMOVE_SUCCESS"), TypeReturn.SUCCESS);
        } catch (EmptyNameException | UnknownNameException ex) {
            return new ReturnExec(ex.getMessage(), TypeReturn.ERROR);
        }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length == 3) {
            if ((Arrays.asList(Constants.PAD_OR_PADDOCK).contains(cmd[0])) && cmd[1].equalsIgnoreCase("remove")) {
                return true;
            }
        }
        return false;
    }
}
