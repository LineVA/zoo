package commandLine.commandImpl;

import basicGui.FormattingDisplay;
import commandLine.AbstractCommand;
import commandLine.Command;
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
        return new ReturnExec(FormattingDisplay.formattingArrayList(list), TypeReturn.SUCCESS);
    }

    public boolean firstCmd(String[] cmd) {
        if (cmd.length >= 2) {
            if (cmd[0].equals("paddock") || cmd[0].equals("pad")) {
                if (cmd[1].equals("ls")) {
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
            } else if (cmd.length == 4 && (cmd[2].equals("-s") || cmd[2].equals("--specie"))) {
                args[0] = cmd[3];
                return true;
            }
        }
        return false;
    }
}
