package commandLine.commandImpl;

import commandLine.Command;
import exception.name.EmptyNameException;
import exception.name.UnknownNameException;
import launch.play.Play;
import zoo.animal.Animal;
import zoo.animalKeeper.AnimalKeeper;

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
    public String execute(String[] cmd) {
        try {
          this.play.getZoo().removeKeeper(this.play.getZoo().findAnimalKeeperByName(cmd[2]));
            this.success = true;
            return this.play.getOption().getGeneralCmdBundle()
                    .getString("KEEPER_REMOVE_SUCCESS");
        } catch (EmptyNameException | UnknownNameException ex) {
            return ex.getMessage();
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
