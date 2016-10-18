package commandLine.commandImpl;

import commandLine.Command;
import commandLine.ReturnExec;
import commandLine.TypeReturn;
import exception.name.EmptyNameException;
import exception.name.UnknownNameException;
import launch.play.Play;

/**
 *
 * @author doyenm
 */
public class RemoveAnimalKeeper implements Command{

    Play play;

    public RemoveAnimalKeeper(Play play) {
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
    public ReturnExec execute(String[] cmd) {
        try {
          this.play.getZoo().removeKeeper(this.play.getZoo().findAnimalKeeperByName(cmd[2]));
            this.success = true;
            return new ReturnExec(
                    this.play.getOption().getGeneralCmdBundle().getString("KEEPER_REMOVE_SUCCESS"),
                    TypeReturn.SUCCESS);
        } catch (EmptyNameException | UnknownNameException ex) {
            return new ReturnExec(ex.getMessage(), TypeReturn.ERROR);
        }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length == 3) {
            if (("animalKeeper".equals(cmd[0]) || "ak".equals(cmd[0]) || "aK".equals(cmd[0]))
                    && "remove".equals(cmd[1])) {
                return true;
            }
        }
        return false;
    }
}
