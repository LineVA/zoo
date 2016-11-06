package commandLine.commandImpl;

import commandLine.AbstractCommand;
import commandLine.ReturnExec;
import commandLine.TypeReturn;
import exception.name.EmptyNameException;
import exception.name.UnknownNameException;
import launch.play.Play;
import utils.Constants;

/**
 *
 * @author doyenm
 */
public class RemoveAnimalKeeper extends AbstractCommand {

    public RemoveAnimalKeeper(Play play) {
        super(play);
    }

    @Override
    public ReturnExec execute(String[] cmd) {
        try {
          super.getPlay().getZoo().removeKeeper(super.getPlay().getZoo().findAnimalKeeperByName(cmd[2]));
            super.setSuccess(true);
            return new ReturnExec(
                    super.getPlay().getOption().getGeneralCmdBundle().getString("KEEPER_REMOVE_SUCCESS"),
                    TypeReturn.SUCCESS);
        } catch (EmptyNameException | UnknownNameException ex) {
            return new ReturnExec(ex.getMessage(), TypeReturn.ERROR);
        }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length == 3) {
            if (("animalKeeper".equalsIgnoreCase(cmd[0]) || "ak".equalsIgnoreCase(cmd[0]) || "aK".equalsIgnoreCase(cmd[0]))
                    && Constants.REMOVE.equalsIgnoreCase(cmd[1])) {
                return true;
            }
        }
        return false;
    }
}
