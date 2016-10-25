
package commandLine.commandImpl;

import basicGui.FormattingDisplay;
import commandLine.AbstractCommand;
import commandLine.ReturnExec;
import commandLine.TypeReturn;
import launch.play.Play;
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
        return new ReturnExec(FormattingDisplay.formattingArrayList(PaddockTypes.UNKNOWN.list()), TypeReturn.SUCCESS);
    }

    @Override
    public boolean canExecute(String[] cmd) {
        return cmd.length == 2 && (cmd[0].equals("paddockType") || "padType".equals(cmd[0])) && cmd[1].equals("ls");
    }
}
