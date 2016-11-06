package commandLine.commandImpl;

import basicGui.FormattingDisplay;
import commandLine.AbstractCommand;
import commandLine.ReturnExec;
import commandLine.TypeReturn;
import launch.play.Play;
import utils.Constants;
import zoo.animal.specie.Size;

/**
 *
 * @author doyenm
 */
public class LsSize extends AbstractCommand  {

    public LsSize(Play play) {
        super(play);
    }

    @Override
    public ReturnExec execute(String[] cmd) {
        super.setSuccess(true);
        return new ReturnExec(FormattingDisplay.formattingList(Size.UNKNOWN.list()), TypeReturn.SUCCESS);
    }

    @Override
    public boolean canExecute(String[] cmd) {
        return cmd.length == 2 && Constants.SIZE.equalsIgnoreCase(cmd[0]) && Constants.LS.equalsIgnoreCase(cmd[1]);
    }
}
