package commandLine.commandImpl;

import commandLine.Command;
import commandLine.ReturnExec;
import commandLine.TypeReturn;
import exception.name.NameException;
import launch.play.Play;

/**
 *
 * @author doyenm
 */
public class CreateAnimalKeeper implements Command{

    Play play;

    public CreateAnimalKeeper(Play play) {
        this.play = play;
    }

    boolean success = false;

    @Override
    public boolean isSuccess() {
        return this.success;
    }

    @Override
    public boolean hasInitiateAZoo() {
        return false;
    }

    @Override
    public ReturnExec execute(String[] cmd) {
        try {
            this.play.getZoo().addKeeper(cmd[2]);
            this.success = true;
            return new ReturnExec(
                    this.play.getOption().getGeneralCmdBundle().getString("KEEPER_CREATION_SUCCESS"),
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
