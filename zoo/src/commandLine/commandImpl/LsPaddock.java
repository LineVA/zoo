package commandLine.commandImpl;

import basicGui.FormattingDisplay;
import commandLine.Command;

/**
 *
 * @author doyenm
 */
public class LsPaddock implements Command {

    @Override
    public String execute(String[] cmd) {
        return FormattingDisplay.formattingArrayList(this.zoo.listPaddock());
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length == 2) {
            if (cmd[0].equals("paddock") || cmd[0].equals("pad")) {
                if (cmd[1].equals("ls")) {
                    return true;
                }
            }
        }
        return false;
    }

}
