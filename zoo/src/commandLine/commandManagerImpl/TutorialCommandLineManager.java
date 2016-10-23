package commandLine.commandManagerImpl;

import basicGui.FormattingDisplay;
import commandLine.Command;
import commandLine.CommandManager;
import commandLine.ReturnExec;
import commandLine.SplitDoubleQuotes;
import commandLine.TypeReturn;
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
        super.setFirstLine(steps.get(0).getInitial());
    }

    @Override
    public ReturnExec run(String cmd) {
        if (!steps.isEmpty() && i < steps.size()) {
            String[] parse = SplitDoubleQuotes.split(cmd);
            ArrayList<String> listStr = new ArrayList<>();
            for (Command command : super.getPlayCommands()) {
                if (command.canExecute(parse)) {
                    ReturnExec result = command.execute(parse);
                    listStr.clear();
                    listStr.add(result.getMessage());
                    // If the player uses the correct command line
                    // && no execution has been thrown 
                    if (steps.get(i).check() && command.isSuccess()) {
                        if (i < steps.size() - 1) {
                            i += 1;
                            listStr.add(steps.get(i).getInitial());
                            return new ReturnExec(FormattingDisplay.formattingArrayList(listStr), TypeReturn.SUCCESS);
                        } else {
                            listStr.add(steps.get(i).getSuccess());
                            return new ReturnExec(FormattingDisplay.formattingArrayList(listStr), TypeReturn.SUCCESS);
                        }
                        // If an exception has been thrown (expected or unexpected command line)
                    } else {
                        listStr.add(steps.get(i).getHelp());
                        return new ReturnExec(FormattingDisplay.formattingArrayList(listStr), TypeReturn.ERROR);
                    }
                }
            }
        }
        return new ReturnExec(steps.get(i).getHelp(), TypeReturn.ERROR);
    }

}
