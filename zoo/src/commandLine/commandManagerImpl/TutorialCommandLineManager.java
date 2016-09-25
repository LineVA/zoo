package commandLine.commandManagerImpl;

import commandLine.Command;
import commandLine.CommandManager;
import commandLine.SplitDoubleQuotes;
import java.util.ArrayList;
import launch.play.Play;
import launch.play.Step;

/**
 *
 * @author doyenm
 */
public class TutorialCommandLineManager extends CommandManager {

    ArrayList<Step> steps;
    int i = 0;

    public TutorialCommandLineManager(Play play, ArrayList<Step> steps) {
        super(play, null);
        this.steps = steps;
        super.setFirstLine(steps.get(0).getPrevious());
    }

    @Override
    public String run(String cmd) {
        if (!steps.isEmpty() && i < steps.size()) {
            String[] parse = SplitDoubleQuotes.split(cmd);
            for (Command command : super.getPlayCommands()) {
                if (command.canExecute(parse)) {
                    String result = command.execute(parse);
                    // If the player uses the correct command line
                    // && no execution has been thrown 
                    if (steps.get(i).check() && command.isSuccess()) {
                        if (i < steps.size() - 1) {
                            i += 1;
                            return result + "\n" + steps.get(i).getPrevious();
                        } else {
                            return result + steps.get(i).getSuccess();
                        }
                        // If an exception has been thrown (expected or unexpected command line)
                    } else {
                        return result + steps.get(i).getFail();
                    }
                }
            }
        }
        return steps.get(i).getFail();
    }

}
