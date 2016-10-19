package commandLine.commandImpl;

import basicGui.FormattingDisplay;
import commandLine.Command;
import commandLine.ReturnExec;
import commandLine.TypeReturn;
import exception.name.EmptyNameException;
import exception.name.UnknownNameException;
import launch.play.Play;
import zoo.paddock.IPaddock;

/**
 *
 * @author doyenm
 */
public class LsAnimalKeeper implements Command {

    Play play;

    String[] args;
    // args[0] : enclos

    public LsAnimalKeeper(Play play) {
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
    public ReturnExec execute(String[] cmd) {
        IPaddock paddock = null;
        try {
            if (args[0] != null) {
                paddock = this.play.getZoo().findPaddockByName(args[0]);
            }
            this.success = true;
            return new ReturnExec(
                    FormattingDisplay.formattingArrayList(this.play.getZoo().listAnimalKeeper(paddock)),
                    TypeReturn.SUCCESS);
        } catch (EmptyNameException | UnknownNameException ex) {
            return new ReturnExec(ex.getMessage(), TypeReturn.ERROR);
        }
    }

    private boolean firstCmd(String[] cmd) {
        if (cmd.length == 2) {
            if (cmd[0].equals("animalKeeper") || "aK".equals(cmd[0]) || "ak".equals(cmd[0])) {
                if (cmd[1].equals("ls")) {
                    return true;
                }
            }
        }
        if (cmd.length == 4) {
            if (cmd[0].equals("animalKeeper") || "aK".equals(cmd[0]) || "ak".equals(cmd[0])) {
                if (cmd[1].equals("ls") && ("-p".equalsIgnoreCase(cmd[2]) || "--paddock".equalsIgnoreCase(cmd[2]))) {
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
