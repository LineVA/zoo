package commandLine.commandImpl;

import commandLine.AbstractCommand;
import commandLine.ReturnExec;
import commandLine.TypeReturn;
import exception.name.EmptyNameException;
import exception.name.UnknownNameException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import launch.play.Play;
import utils.Constants;
import zoo.animalKeeper.AnimalKeeper;
import zoo.paddock.IPaddock;

/**
 *
 * @author doyenm
 */
public class RemoveTimedPaddocks extends AbstractCommand {

    public RemoveTimedPaddocks(Play play) {
        super(play);
    }

    @Override
    public ReturnExec execute(String[] cmd) {
        try {
            List<IPaddock> paddocks = new ArrayList<>();
            int i = 4;
            while (i < cmd.length) {
                paddocks.add(super.getPlay().getZoo().findPaddockByName(cmd[i]));
                i += 1;
            }
            AnimalKeeper keeper = super.getPlay().getZoo().findAnimalKeeperByName(cmd[1]);
            keeper.removeTimedPaddocks(paddocks);
            super.setSuccess(true);
            return new ReturnExec(
                    super.getPlay().getOption().getGeneralCmdBundle().getString("TIMED_PADDOCKS_SUCCESS"),
                    TypeReturn.SUCCESS);
        } catch (UnknownNameException | EmptyNameException ex) {
            return new ReturnExec(ex.getMessage(), TypeReturn.ERROR);
        }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length >= 5) {
            if (Arrays.asList(Constants.AK_OR_ANIMALKEEPER).contains(cmd[0])) {
                if (Constants.REMOVE.equalsIgnoreCase(cmd[2])) {
                    if (cmd[3].equalsIgnoreCase("--timedPaddocks") || cmd[3].equalsIgnoreCase("-tP")) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
