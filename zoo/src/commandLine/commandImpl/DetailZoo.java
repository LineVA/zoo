package commandLine.commandImpl;

import basicGui.FormattingDisplay;
import commandLine.AbstractCommand;
import commandLine.Command;
import commandLine.ReturnExec;
import commandLine.TypeReturn;
import launch.play.Play;
import utils.Constants;

/**
 *
 * @author doyenm
 */
public class DetailZoo extends AbstractCommand {

    public DetailZoo(Play play) {
        super(play);
    }
    
    @Override
    public ReturnExec execute(String[] cmd) {
       super.setSuccess(true);
        return new ReturnExec(FormattingDisplay.formattingList(super.getPlay().getZoo().info()), 
                TypeReturn.SUCCESS);
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length == 1) {
            if (Constants.ZOO.equalsIgnoreCase(cmd[0])) {
                return true;
            }
        }
        return false;
    }
}
