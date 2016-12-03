package commandLine.commandImpl;

import basicGui.FormattingDisplay;
import commandLine.AbstractCommand;
import commandLine.ReturnExec;
import commandLine.TypeReturn;
import launch.play.Play;
import utils.Constants;
import zoo.animal.feeding.Diet;

/**
 *
 * @author doyenm
 */
public class LsDiet extends AbstractCommand  {

    public LsDiet(Play play) {
        super(play);
    }

    @Override
    public ReturnExec execute(String[] cmd) {
        super.setSuccess(true);
        return new ReturnExec(FormattingDisplay.formattingList(Diet.NONE.list(), true),
                TypeReturn.SUCCESS);
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length == 2) {
            if (Constants.DIET.equalsIgnoreCase(cmd[0])) {
                if (Constants.LS.equalsIgnoreCase(cmd[1])) {
                    return true;
                }
            }
        }
        return false;
    }
}
