package commandLine.commandImpl;

import commandLine.AbstractCommand;
import commandLine.ReturnExec;
import commandLine.TypeReturn;
import exception.IncorrectDimensionsException;
import exception.name.NameException;
import launch.play.Play;
import utils.Constants;

/**
 *
 * @author doyenm
 */
public class CreatePaddock extends AbstractCommand {

    public CreatePaddock(Play play) {
        super(play);
    }

    @Override
    public ReturnExec execute(String[] cmd) {
        try {
            super.getPlay().getZoo().addPaddock(cmd[2], Integer.parseInt(cmd[3]), Integer.parseInt(cmd[4]),
                    Integer.parseInt(cmd[5]), Integer.parseInt(cmd[6]));
            super.setSuccess(true);
            return new ReturnExec(super.getPlay().getOption().getGeneralCmdBundle()
                    .getString("PADDOCK_CREATION_SUCCESS"), TypeReturn.SUCCESS);
        } catch (NameException | IncorrectDimensionsException ex) {
                return new ReturnExec(ex.getMessage(), TypeReturn.ERROR);
        } catch (java.lang.NumberFormatException ex){
            return new ReturnExec(super.getPlay().getOption().getGeneralCmdBundle().getString("NUMBER_FORMAT_EXCEPTION")
            , TypeReturn.ERROR);
        }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length == 7) {
            if (cmd[0].equalsIgnoreCase("pad") || cmd[0].equalsIgnoreCase("paddock")) {
                if (Constants.CREATE.equalsIgnoreCase(cmd[1])) {
                    return true;
                }
            }
        }
        return false;
    }

}
