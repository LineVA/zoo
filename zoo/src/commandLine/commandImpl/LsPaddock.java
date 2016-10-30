package commandLine.commandImpl;

import basicGui.FormattingDisplay;
import commandLine.AbstractCommand;
import commandLine.ReturnExec;
import commandLine.TypeReturn;
import exception.name.EmptyNameException;
import exception.name.UnknownNameException;
import java.util.ArrayList;
import java.util.Collections;
import launch.play.Play;
import zoo.animal.specie.Specie;

/**
 *
 * @author doyenm
 */
public class LsPaddock extends AbstractCommand  {

    String[] args;

    public LsPaddock(Play play) {
        super(play);
    }

    @Override
    public ReturnExec execute(String[] cmd) {
        Specie spec = null;
        if (args[0] != null) {
            try {
                spec = super.getPlay().getZoo().findSpecieByName(args[0]);
                super.setSuccess(true);
            } catch (EmptyNameException | UnknownNameException ex) {
                return new ReturnExec(ex.getMessage(), TypeReturn.ERROR);
            }
        }
        ArrayList<String> list = super.getPlay().getZoo().listPaddock(spec);
        Collections.sort(list);
        return new ReturnExec(FormattingDisplay.formattingList(list), TypeReturn.SUCCESS);
    }

    public boolean firstCmd(String[] cmd) {
        if (cmd.length >= 2) {
            if (cmd[0].equalsIgnoreCase("paddock") || cmd[0].equalsIgnoreCase("pad")) {
                if (cmd[1].equalsIgnoreCase("ls")) {
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
            if (cmd.length == 2) {
                return true;
            } else if (cmd.length == 4 && (cmd[2].equalsIgnoreCase("-s") || cmd[2].equalsIgnoreCase("--specie"))) {
                args[0] = cmd[3];
                return true;
            }
        }
        return false;
    }
}
