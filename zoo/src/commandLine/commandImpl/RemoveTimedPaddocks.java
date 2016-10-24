package commandLine.commandImpl;

import commandLine.AbstractCommand;
import commandLine.Command;
import commandLine.ReturnExec;
import commandLine.TypeReturn;
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
public class RemoveTimedPaddocks extends AbstractCommand implements Command {

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
    public ReturnExec execute(String[] cmd) {
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
            return new ReturnExec(
                    this.play.getOption().getGeneralCmdBundle().getString("TIMED_PADDOCKS_SUCCESS"),
                    TypeReturn.SUCCESS);
        } catch (UnknownNameException | EmptyNameException ex) {
            return new ReturnExec(ex.getMessage(), TypeReturn.ERROR);
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
