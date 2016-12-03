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
public class LsAnimalKeeper extends AbstractCommand  {

    String[] args;
    // args[0] : enclos

    public LsAnimalKeeper(Play play) {
        super(play);
    }

    @Override
    public ReturnExec execute(String[] cmd) {
        IPaddock paddock = null;
        try {
            if (args[0] != null) {
                paddock = super.getPlay().getZoo().findPaddockByName(args[0]);
            }
            super.setSuccess(true);
            return new ReturnExec(
                    FormattingDisplay.formattingList(super.getPlay().getZoo().listAnimalKeeper(paddock), true),
                    TypeReturn.SUCCESS);
        } catch (EmptyNameException | UnknownNameException ex) {
            return new ReturnExec(ex.getMessage(), TypeReturn.ERROR);
        }
    }

    private boolean firstCmd(String[] cmd) {
        if (cmd.length == 2) {
            if (Arrays.asList(Constants.AK_OR_ANIMALKEEPER).contains(cmd[0])) {
                if (Constants.LS.equalsIgnoreCase(cmd[1])) {
                    return true;
                }
            }
        }
        if (cmd.length == 4) {
            if (Arrays.asList(Constants.AK_OR_ANIMALKEEPER).contains(cmd[0])) {
                if (Constants.LS.equalsIgnoreCase(cmd[1]) && 
                        Arrays.asList(Constants.PADDOCKTYPE_ARG).contains(cmd[2])) {
                    args[0] = cmd[3];
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean canExecute(String[] cmd) {
        this.args = new String[]{null};
        if (firstCmd(cmd)) {
            return true;
        }
        return false;
    }

}
