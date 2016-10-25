package commandLine.commandImpl;

import basicGui.FormattingDisplay;
import commandLine.AbstractCommand;
import commandLine.ReturnExec;
import commandLine.TypeReturn;
import launch.play.Play;
import zoo.animalKeeper.Task;

/**
 *
 * @author doyenm
 */
public class LsTask extends AbstractCommand {

    public LsTask(Play play) {
        super(play);
    }

    @Override
    public ReturnExec execute(String[] cmd) {
        super.setSuccess(true);
        return new ReturnExec(
                FormattingDisplay.formattingArrayList(Task.UNKNOWN.list()), 
                TypeReturn.SUCCESS);
    }

    @Override
    public boolean canExecute(String[] cmd) {
        return cmd.length == 2 && cmd[0].equals("task") && cmd[1].equals("ls");
    }
}
