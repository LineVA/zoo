package commandLine.commandImpl;

import basicGui.FormattingDisplay;
import commandLine.AbstractCommand;
import commandLine.ReturnExec;
import commandLine.TypeReturn;
import java.util.List;
import launch.play.Play;
import utils.Constants;
import zoo.animal.conservation.BreedingProgramme;

/**
 *
 * @author doyenm
 */
public class LsBreedingProgramme extends AbstractCommand{

    public LsBreedingProgramme(Play play) {
        super(play);
    }

    @Override
    public ReturnExec execute(String[] cmd) {
        super.setSuccess(true);
        List<String> list = BreedingProgramme.NONE.list();
        return new ReturnExec(FormattingDisplay.formattingList(list), 
                TypeReturn.SUCCESS);
    }

    @Override
    public boolean canExecute(String[] cmd) {
        return cmd.length == 2 && cmd[0].equalsIgnoreCase("breedingProgramme") && Constants.LS.equalsIgnoreCase(cmd[1]);
    }
}
