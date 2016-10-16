package commandLine.commandImpl;

import commandLine.Command;
import exception.IncorrectDataException;
import exception.name.EmptyNameException;
import exception.name.UnknownNameException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import launch.play.Play;
import static org.mockito.Mockito.times;
import zoo.animalKeeper.AnimalKeeper;
import zoo.paddock.IPaddock;

/**
 *
 * @author doyenm
 */
public class AddTimedPaddocks implements Command {

    Play play;
    boolean success = false;

    @Override
    public boolean isSuccess() {
        return this.success;
    }

    public AddTimedPaddocks(Play play) {
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
            ArrayList<Double> times = new ArrayList<>();
            int i = 3;
            while (i < cmd.length) {
                paddocks.add(this.play.getZoo().findPaddockByName(cmd[i]));
                times.add(Double.parseDouble(cmd[i + 1]));
                i += 2;
            }
            AnimalKeeper keeper = this.play.getZoo().findAnimalKeeperByName(cmd[1]);
            keeper.addTimedPaddocks(paddocks, times);
            this.success = true;
            return this.play.getOption().getGeneralCmdBundle().getString("TIMED_PADDOCKS_SUCCESS");
        } catch (UnknownNameException  | EmptyNameException | IncorrectDataException ex) {
            return ex.getMessage();
        }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length >= 5 && cmd.length % 2 == 1) {
            if (cmd[0].equals("ak") || cmd[0].equals("aK") || cmd[0].equals("animalKeeper")) {
                if (cmd[2].equals("--timedPaddocks") || cmd[2].equals("-tP")) {
                    return true;
                }
            }
        }
        return false;
    }
}
