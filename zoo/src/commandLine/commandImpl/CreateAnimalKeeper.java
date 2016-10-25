package commandLine.commandImpl;

import commandLine.AbstractCommand;
import commandLine.Command;
import commandLine.ReturnExec;
import commandLine.TypeReturn;
import exception.name.NameException;
import launch.play.Play;

/**
 *
 * @author doyenm
 */
public class CreateAnimalKeeper  extends AbstractCommand {

    public CreateAnimalKeeper(Play play) {
       super(play);
    }

    @Override
    public ReturnExec execute(String[] cmd) {
        try {
            super.getPlay().getZoo().addKeeper(cmd[2]);
            super.setSuccess(true);
            return new ReturnExec(
                   super.getPlay().getOption().getGeneralCmdBundle().getString("KEEPER_CREATION_SUCCESS"),
                    TypeReturn.SUCCESS);
        } catch (NameException ex) {
            return new ReturnExec(ex.getMessage(), TypeReturn.ERROR);
        }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length == 3) {
            if (cmd[0].equals("ak") || cmd[0].equals("animalKeeper") || "aK".equals(cmd[0])) {
                if (cmd[1].equals("create")) {
                    return true;
                }
            }
        }
        return false;
    }

}
