
package commandLine.commandImpl;

import basicGui.FormattingDisplay;
import commandLine.AbstractCommand;
import commandLine.ReturnExec;
import commandLine.TypeReturn;
import java.util.Arrays;
import launch.play.Play;
import utils.Constants;
import zoo.paddock.PaddockTypes;

/**
 *
 * @author doyenm
 */
public class LsPaddockType  extends AbstractCommand {

    public LsPaddockType(Play play) {
        super(play);
    }

    @Override
    public ReturnExec execute(String[] cmd) {
        super.setSuccess(true);
        return new ReturnExec(FormattingDisplay.formattingList(PaddockTypes.UNKNOWN.list()), TypeReturn.SUCCESS);
    }

    @Override
    public boolean canExecute(String[] cmd) {
        return cmd.length == 2 &&
               Arrays.asList(Constants.PADDOCKTYPE_OR_PADTYPE).contains(cmd[0]) && 
               Constants.LS.equalsIgnoreCase(cmd[1]);
    }
}
