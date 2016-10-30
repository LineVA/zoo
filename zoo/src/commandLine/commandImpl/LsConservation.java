package commandLine.commandImpl;

import basicGui.FormattingDisplay;
import commandLine.AbstractCommand;
import commandLine.ReturnExec;
import commandLine.TypeReturn;
import launch.play.Play;
import zoo.animal.conservation.ConservationStatus;

/**
 * Command 'conservation ls'
 * @author doyenm
 */
public class LsConservation extends AbstractCommand  {

    public LsConservation(Play play) {
        super(play);
    }

    @Override
    public ReturnExec execute(String[] cmd) {
        super.setSuccess(true);
        return new ReturnExec(FormattingDisplay.formattingList(ConservationStatus.UNKNOWN.list()), 
                TypeReturn.SUCCESS);
    }

    @Override
    public boolean canExecute(String[] cmd) {
        return cmd.length == 2 && cmd[0].equalsIgnoreCase("conservation") && cmd[1].equalsIgnoreCase("ls");
    }
}
