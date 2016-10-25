package commandLine.commandImpl;

import basicGui.FormattingDisplay;
import commandLine.AbstractCommand;
import commandLine.ReturnExec;
import commandLine.TypeReturn;
import launch.play.Play;
import zoo.animal.feeding.Diet;

/**
 *
 * @author doyenm
 */
public class LsFeeding extends AbstractCommand  {

    public LsFeeding(Play play) {
        super(play);
    }

    @Override
    public ReturnExec execute(String[] cmd) {
        super.setSuccess(true);
        return new ReturnExec(FormattingDisplay.formattingArrayList(Diet.NONE.list()), TypeReturn.SUCCESS);
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length == 2) {
            if (cmd[0].equalsIgnoreCase("diet")) {
                if (cmd[1].equalsIgnoreCase("ls")) {
                    return true;
                }
            }
        }
        return false;
    }
}
