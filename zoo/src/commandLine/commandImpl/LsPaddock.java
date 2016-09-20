package commandLine.commandImpl;

import basicGui.FormattingDisplay;
import commandLine.Command;
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
public class LsPaddock implements Command {

    Play play;

    String[] args;

    public LsPaddock(Play play) {
        this.play = play;
    }

    @Override
    public boolean hasInitiateAZoo() {
        return false;
    }

    boolean success = false;

    @Override
    public boolean isSuccess() {
        return this.success;
    }

    @Override
    public String execute(String[] cmd) {
        Specie spec = null;
        if (args[0] != null) {
            try {
                spec = this.play.getZoo().findSpecieByName(args[0]);
                this.success = true;
            } catch (EmptyNameException | UnknownNameException ex) {
                return ex.getMessage();
            }
        }
        ArrayList<String> list = this.play.getZoo().listPaddock(spec);
        Collections.sort(list);
        return FormattingDisplay.formattingArrayList(list);
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
