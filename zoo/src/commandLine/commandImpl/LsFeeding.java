package commandLine.commandImpl;

import basicGui.FormattingDisplay;
import commandLine.Command;
import exception.name.EmptyNameException;
import exception.name.UnknownNameException;
import launch.Play;
import zoo.animal.feeding.Diet;
import zoo.animal.specie.Specie;

/**
 *
 * @author doyenm
 */
public class LsFeeding implements Command {
     Play play;

    public LsFeeding(Play play) {
        this.play = play;
    }

    @Override
    public String execute(String[] cmd) {
        return FormattingDisplay.formattingArrayList(Diet.NONE.list());
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length == 2) {
            if (cmd[0].equals("feeding") || cmd[0].equals("feed")) {
                if (cmd[1].equals("ls")) {
                    return true;
                }
            }
        }
        return false;
    }
}
