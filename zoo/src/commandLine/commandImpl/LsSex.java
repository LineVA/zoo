package commandLine.commandImpl;

import basicGui.FormattingDisplay;
import commandLine.AbstractCommand;
import commandLine.ReturnExec;
import commandLine.TypeReturn;
import launch.play.Play;
import utils.Constants;
import zoo.animal.reproduction.Sex;

/**
 *
 * @author doyenm
 */
public class LsSex extends AbstractCommand  {

    public LsSex(Play play) {
        super(play);
    }

    @Override
    public ReturnExec execute(String[] cmd) {
        super.setSuccess(true);
        return new ReturnExec(FormattingDisplay.formattingList(Sex.UNKNOWN.list(), true),
                TypeReturn.SUCCESS);
    }

    @Override
    public boolean canExecute(String[] cmd) {
        return cmd.length == 2 && Constants.SEX.equalsIgnoreCase(cmd[0])
                && Constants.LS.equalsIgnoreCase(cmd[1]);
    }
}
