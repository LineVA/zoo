package commandLine.commandImpl;

import basicGui.FormattingDisplay;
import commandLine.AbstractCommand;
import commandLine.ReturnExec;
import commandLine.TypeReturn;
import exception.name.EmptyNameException;
import exception.name.UnknownNameException;
import java.util.Arrays;
import launch.play.Play;
import utils.Constants;
import zoo.paddock.IPaddock;

/**
 *
 * @author doyenm
 */
public class DetailPad extends AbstractCommand {
    
    public DetailPad(Play play) {
        super(play);
    }
    
    @Override
    public ReturnExec execute(String[] cmd) {
        try {
            IPaddock pad =super.getPlay().getZoo().findPaddockByName(cmd[1]);
            super.setSuccess(true);
            return new ReturnExec(FormattingDisplay.formattingList(pad.info(), false), TypeReturn.SUCCESS);
        } catch (UnknownNameException | EmptyNameException ex) {
            return new ReturnExec(ex.getMessage(), TypeReturn.ERROR);
        }        
    }
    
    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length == 2) {
            if (Arrays.asList(Constants.PAD_OR_PADDOCK).contains(cmd[0])) {
                return true;
            }
        }
        return false;
    }
    
}
