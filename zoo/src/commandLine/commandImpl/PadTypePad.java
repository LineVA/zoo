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
public class PadTypePad extends AbstractCommand  {

    public PadTypePad(Play play) {
        super(play);
    }

    @Override
    public ReturnExec execute(String[] cmd) {
        try {
            IPaddock pad = super.getPlay().getZoo().findPaddockByName(cmd[1]);
            pad.setPaddockType(cmd[3]);
            super.setSuccess(true);
            return new ReturnExec(
                    super.getPlay().getOption().getGeneralCmdBundle().getString("TYPE_PAD") + cmd[3],
                    TypeReturn.SUCCESS);
        } catch(UnknownNameException | EmptyNameException ex){
            return new ReturnExec(ex.getMessage(), TypeReturn.ERROR);
        }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        return cmd.length == 4 && (Arrays.asList(Constants.PAD_OR_PADDOCK).contains(cmd[0]))
                && (cmd[2].equalsIgnoreCase("-pT") || "--padType".equalsIgnoreCase(cmd[2]) || "--paddockType".equalsIgnoreCase(cmd[2]));
    }
}
