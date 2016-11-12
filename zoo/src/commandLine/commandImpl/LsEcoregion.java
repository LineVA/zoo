package commandLine.commandImpl;

import basicGui.FormattingDisplay;
import commandLine.AbstractCommand;
import commandLine.ReturnExec;
import commandLine.TypeReturn;
import launch.play.Play;
import utils.Constants;
import zoo.paddock.biome.Ecoregion;

/**
 *
 * @author doyenm
 */
public class LsEcoregion extends AbstractCommand  {

    public LsEcoregion(Play play) {
        super(play);
    }

    @Override
    public ReturnExec execute(String[] cmd) {
        super.setSuccess(true);
        return new ReturnExec(FormattingDisplay.formattingList(Ecoregion.UNKNOWN.list()), TypeReturn.SUCCESS);
    }

    @Override
    public boolean canExecute(String[] cmd) {
        return cmd.length == 2 && Constants.ECOREGION.equalsIgnoreCase(cmd[0]) 
                && Constants.LS.equalsIgnoreCase(cmd[1]);
    }
}
