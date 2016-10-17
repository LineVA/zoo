package commandLine.commandImpl;

import commandLine.Command;
import exception.IncorrectDataException;
import exception.name.EmptyNameException;
import exception.name.UnknownNameException;
import java.util.ArrayList;
import launch.play.Play;
import zoo.animalKeeper.AnimalKeeper;
import zoo.animalKeeper.Task;
import zoo.paddock.IPaddock;

/**
 *
 * @author doyenm
 */
public class AddTimedTasksPerPaddock implements Command{

    Play play;
    boolean success = false;

    @Override
    public boolean isSuccess() {
        return this.success;
    }

    public AddTimedTasksPerPaddock(Play play) {
        this.play = play;
    }

    @Override
    public boolean hasInitiateAZoo() {
        return false;
    }

    @Override
    public String execute(String[] cmd) {
        try {
            ArrayList<Task> tasks = new ArrayList<>();
            ArrayList<Double> times = new ArrayList<>();
            int i = 4;
            while (i < cmd.length) {
                tasks.add(Task.UNKNOWN.findById(Integer.parseInt(cmd[i])));
                times.add(Double.parseDouble(cmd[i + 1]));
                i += 2;
            }
            AnimalKeeper keeper = this.play.getZoo().findAnimalKeeperByName(cmd[1]);
            IPaddock paddock = this.play.getZoo().findPaddockByName(cmd[3]);
            keeper.addTaskToAPaddock(paddock, tasks, times);
            this.success = true;
            return this.play.getOption().getGeneralCmdBundle().getString("TIMED_TASKS_SUCCESS");
        } catch (UnknownNameException  | EmptyNameException | IncorrectDataException ex) {
            return ex.getMessage();
        }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length >= 6 && cmd.length % 2 == 0) {
            if (cmd[0].equals("ak") || cmd[0].equals("aK") || cmd[0].equals("animalKeeper")) {
                if (cmd[2].equals("--timedTaskPerPaddock") || cmd[2].equals("-tT") || "-tTPP".equals(cmd[2])) {
                    return true;
                }
            }
        }
        return false;
    }
}
