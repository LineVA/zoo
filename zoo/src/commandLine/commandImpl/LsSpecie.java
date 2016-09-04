package commandLine.commandImpl;

import basicGui.FormattingDisplay;
import commandLine.Command;
import exception.name.EmptyNameException;
import exception.name.UnknownNameException;
import launch.play.Play;
import zoo.paddock.IPaddock;

/**
 *
 * @author doyenm
 */
public class LsSpecie implements Command {

    Play play;
    String[] args;

    public LsSpecie(Play play) {
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
        IPaddock pad = null;
        try {
            if (args[0] != null) {
                pad = this.play.getZoo().findPaddockByName(args[0]);
            }
            this.success = true;
        } catch (EmptyNameException | UnknownNameException ex) {
            return ex.getMessage();
        }
        return FormattingDisplay.formattingArrayList(this.play.getZoo().listSpecie(pad));
    }

    public boolean firstCmd(String[] cmd) {
        if (cmd.length >= 2) {
            if (cmd[0].equals("specie") || cmd[0].equals("spec")) {
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
            } else if (cmd.length == 4 && (cmd[2].equals("-p") || cmd[2].equals("--paddock"))) {
                args[0] = cmd[3];
                return true;
            }
        }
        return false;
    }
}
