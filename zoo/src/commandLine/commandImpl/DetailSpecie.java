package commandLine.commandImpl;

import basicGui.FormattingDisplay;
import commandLine.AbstractCommand;
import commandLine.Command;
import commandLine.ReturnExec;
import commandLine.TypeReturn;
import exception.name.EmptyNameException;
import exception.name.UnknownNameException;
import java.util.Arrays;
import launch.play.Play;
import utils.Constants;
import zoo.animal.specie.Specie;

/**
 *
 * @author doyenm
 */
public class DetailSpecie extends AbstractCommand implements Command {

    public DetailSpecie(Play play) {
        super(play);
    }

    @Override
    public ReturnExec execute(String[] cmd) {
        try {
            Specie spec = super.getPlay().getZoo().findSpecieByName(cmd[1]);
           super.setSuccess(true);
            return new ReturnExec(FormattingDisplay.formattingList(
                    spec.toString(super.getPlay().getOption()), false),
                    TypeReturn.SUCCESS);
        } catch (UnknownNameException | EmptyNameException ex) {
            return new ReturnExec(ex.getMessage(), TypeReturn.ERROR);
        }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length == 2) {
            if (Arrays.asList(Constants.SPEC_OR_SPECIE).contains(cmd[0])) {
                return true;
            }
        }
        return false;
    }
}
