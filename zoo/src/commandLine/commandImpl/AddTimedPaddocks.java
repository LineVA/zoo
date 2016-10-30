package commandLine.commandImpl;

import commandLine.AbstractCommand;
import commandLine.ReturnExec;
import commandLine.TypeReturn;
import exception.IncorrectDataException;
import exception.name.EmptyNameException;
import exception.name.UnknownNameException;
import java.util.ArrayList;
import java.util.List;
import launch.play.Play;
import zoo.animalKeeper.AnimalKeeper;
import zoo.paddock.IPaddock;

/**
 *
 * @author doyenm
 */
public class AddTimedPaddocks extends AbstractCommand {
   
    public AddTimedPaddocks(Play play) {
        super(play);
    }

    @Override
    public ReturnExec execute(String[] cmd) {
        try {
            List<IPaddock> paddocks = new ArrayList<>();
            List<Double> times = new ArrayList<>();
            int i = 3;
            while (i < cmd.length) {
                paddocks.add(super.getPlay().getZoo().findPaddockByName(cmd[i]));
                times.add(Double.parseDouble(cmd[i + 1]));
                i += 2;
            }
            AnimalKeeper keeper = super.getPlay().getZoo().findAnimalKeeperByName(cmd[1]);
            keeper.addTimedPaddocks(paddocks, times);
            super.setSuccess(true);
            return new ReturnExec(
                   super.getPlay().getOption().getGeneralCmdBundle().getString("TIMED_PADDOCKS_SUCCESS"),
                    TypeReturn.SUCCESS);
        } catch (UnknownNameException  | EmptyNameException | IncorrectDataException ex) {
            return new ReturnExec(ex.getMessage(), TypeReturn.ERROR);
        } catch (java.lang.NumberFormatException ex){
            return new ReturnExec(
                    super.getPlay().getOption().getGeneralCmdBundle().getString("NUMBER_FORMAT_EXCEPTION")
            , TypeReturn.ERROR);
        }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length >= 5 && cmd.length % 2 == 1) {
            if (cmd[0].equalsIgnoreCase("ak") || cmd[0].equalsIgnoreCase("aK") || cmd[0].equalsIgnoreCase("animalKeeper")) {
                if (cmd[2].equalsIgnoreCase("--timedPaddocks") || cmd[2].equalsIgnoreCase("-tP")) {
                    return true;
                }
            }
        }
        return false;
    }
}
