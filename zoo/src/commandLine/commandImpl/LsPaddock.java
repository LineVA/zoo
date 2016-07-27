package commandLine.commandImpl;

import basicGui.FormattingDisplay;
import commandLine.Command;
import exception.name.EmptyNameException;
import exception.name.UnknownNameException;
import launch.Play;
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
    public String execute(String[] cmd) {
        Specie spec = null;
        if (args[0] != null) {
            try {
                spec = this.play.zoo.findSpeciebyName(args[0]);
            } catch (EmptyNameException | UnknownNameException ex) {
                return ex.getMessage();
            }
        }
        return FormattingDisplay.formattingArrayList(this.play.zoo.listPaddock(spec));
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
            } else if (cmd.length == 4 && cmd[2].equals("-s")) {
                args[0] = cmd[3];
                return true;
            }
        }
        return false;
    }
}
