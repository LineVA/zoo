package commandLine.commandImpl;

import basicGui.FormattingDisplay;
import commandLine.AbstractCommand;
import commandLine.ReturnExec;
import commandLine.TypeReturn;
import launch.play.Play;
import zoo.animal.specie.Family;

/**
 *
 * @author doyenm
 */
public class LsFamily extends AbstractCommand  {

    public LsFamily(Play play) {
        super(play);
    }

    @Override
    public ReturnExec execute(String[] cmd) {
        super.setSuccess(true);
        return new ReturnExec(FormattingDisplay.formattingArrayList(Family.UNKNOWN.list()), TypeReturn.SUCCESS);
    }

    @Override
    public boolean canExecute(String[] cmd) {
        return cmd.length == 2 && cmd[0].equalsIgnoreCase("family") && cmd[1].equalsIgnoreCase("ls");
    }
}
