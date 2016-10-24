package commandLine.commandImpl;

import commandLine.AbstractCommand;
import commandLine.Command;
import commandLine.ReturnExec;
import commandLine.TypeReturn;
import exception.IncorrectDataException;
import exception.name.EmptyNameException;
import exception.name.UnknownNameException;
import java.util.ArrayList;
import launch.play.Play;
import zoo.animalKeeper.AnimalKeeper;
import zoo.paddock.IPaddock;

/**
 *
 * @author doyenm
 */
public class AddTimedPaddocks extends AbstractCommand implements Command {

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
    public ReturnExec execute(String[] cmd) {
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
            return new ReturnExec(
                    this.play.getOption().getGeneralCmdBundle().getString("TIMED_PADDOCKS_SUCCESS"),TypeReturn.SUCCESS);
        } catch (UnknownNameException  | EmptyNameException | IncorrectDataException ex) {
            return new ReturnExec(ex.getMessage(), TypeReturn.ERROR);
        } catch (java.lang.NumberFormatException ex){
            return new ReturnExec(this.play.getOption().getGeneralCmdBundle().getString("NUMBER_FORMAT_EXCEPTION")
            , TypeReturn.ERROR);
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
