package commandLine.commandImpl;

import commandLine.Command;
import exception.IncorrectDataException;
import exception.name.EmptyNameException;
import exception.name.UnknownNameException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import launch.play.Play;
import zoo.animalKeeper.AnimalKeeper;
import zoo.paddock.IPaddock;

/**
 *
 * @author doyenm
 */
public class RemoveTimedPaddocks implements Command {

    Play play;
    boolean success = false;

    @Override
    public boolean isSuccess() {
        return this.success;
    }

    public RemoveTimedPaddocks(Play play) {
        this.play = play;
    }

    @Override
    public boolean hasInitiateAZoo() {
        return false;
    }

    @Override
    public String execute(String[] cmd) {
        try {
            ArrayList<IPaddock> paddocks = new ArrayList<>();
            int i = 4;
            while (i < cmd.length) {
                paddocks.add(this.play.getZoo().findPaddockByName(cmd[i]));
                i += 1;
            }
            AnimalKeeper keeper = this.play.getZoo().findAnimalKeeperByName(cmd[1]);
            keeper.removeTimedPaddocks(paddocks);
            this.success = true;
            return this.play.getOption().getGeneralCmdBundle().getString("TIMED_PADDOCKS_SUCCESS");
        } catch (UnknownNameException | EmptyNameException ex) {
            return ex.getMessage();
        }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length >= 5) {
            if (cmd[0].equalsIgnoreCase("ak") || cmd[0].equalsIgnoreCase("animalKeeper")) {
                if ("-r".equalsIgnoreCase(cmd[2]) || "--remove".equalsIgnoreCase(cmd[2])) {
                    if (cmd[3].equalsIgnoreCase("--timedPaddocks") || cmd[3].equalsIgnoreCase("-tP")) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
