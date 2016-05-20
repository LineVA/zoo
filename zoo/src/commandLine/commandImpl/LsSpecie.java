package commandLine.commandImpl;

import basicGui.FormattingDisplay;
import commandLine.Command;

/**
 *
 * @author doyenm
 */
public class LsSpecie implements Command{

    @Override
    public String execute(String[] cmd) {
        return FormattingDisplay.formattingArrayList(this.zoo.listSpecie());
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length == 2) {
            if (cmd[0].equals("specie") || cmd[0].equals("spec")) {
                if (cmd[1].equals("ls")) {
                    return true;
                }
            }
        }
        return false;
    }
}
