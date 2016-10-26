package commandLine.commandImpl;

import basicGui.FormattingDisplay;
import commandLine.AbstractCommand;
import commandLine.ReturnExec;
import commandLine.TypeReturn;
import launch.play.Play;
import zoo.animal.conservation.BreedingProgramme;
import zoo.animal.conservation.ConservationStatus;

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
        return new ReturnExec(FormattingDisplay.formattingArrayList(BreedingProgramme.NONE.list()), TypeReturn.SUCCESS);
    }

    @Override
    public boolean canExecute(String[] cmd) {
        return cmd.length == 2 && cmd[0].equalsIgnoreCase("breedingProgramme") && cmd[1].equalsIgnoreCase("ls");
    }
}
