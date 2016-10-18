package commandLine.commandImpl;

import commandLine.Command;
import commandLine.ReturnExec;
import commandLine.TypeReturn;
import exception.name.EmptyNameException;
import exception.name.UnknownNameException;
import java.util.ArrayList;
import launch.play.Play;
import zoo.animalKeeper.AnimalKeeper;
import zoo.animalKeeper.Task;
import zoo.animalKeeper.TaskPaddock;
import zoo.paddock.IPaddock;

/**
 *
 * @author doyenm
 */
public class RemoveTimedTasksPerPaddock implements Command {

    Play play;
    boolean success = false;

    @Override
    public boolean isSuccess() {
        return this.success;
    }

    public RemoveTimedTasksPerPaddock(Play play) {
        this.play = play;
    }

    @Override
    public boolean hasInitiateAZoo() {
        return false;
    }

    @Override
    public ReturnExec execute(String[] cmd) {
        try {
            IPaddock paddock = this.play.getZoo().findPaddockByName(cmd[4]);
            ArrayList<TaskPaddock> tasksPerPaddock = new ArrayList<>();
            int i = 5;
            while (i < cmd.length) {
                Task.UNKNOWN.findById(Integer.parseInt(cmd[i]));
               tasksPerPaddock.add(new TaskPaddock(paddock, Integer.parseInt(cmd[i])));
                i += 1;
            }
            AnimalKeeper keeper = this.play.getZoo().findAnimalKeeperByName(cmd[1]);
            keeper.removeTimedTasksPerPaddock(tasksPerPaddock);
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
        if (cmd.length >= 6) {
            if (cmd[0].equalsIgnoreCase("ak") || cmd[0].equalsIgnoreCase("animalKeeper")) {
                if ("-r".equalsIgnoreCase(cmd[2]) || "--remove".equalsIgnoreCase(cmd[2])) {
                    if (cmd[3].equalsIgnoreCase("--timedTaskPerPaddock") || 
                            cmd[3].equalsIgnoreCase("-tT") || "-tTPP".equalsIgnoreCase(cmd[3])) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
