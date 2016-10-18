package commandLine.commandImpl;

import basicGui.FormattingDisplay;
import commandLine.Command;
import commandLine.ReturnExec;
import commandLine.TypeReturn;
import launch.play.Play;

/**
 *
 * @author doyenm
 */
public class LsAnimalKeeper implements Command {

    Play play;

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
        this.success = true;
        return new ReturnExec(
                FormattingDisplay.formattingArrayList(this.play.getZoo().listAnimalKeeper()), 
                TypeReturn.SUCCESS);
    }

    private boolean firstCmd(String[] cmd) {
        if (cmd.length == 2) {
            if (cmd[0].equals("animalKeeper")|| "aK".equals(cmd[0]) || "ak".equals(cmd[0])) {
                if (cmd[1].equals("ls")) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (firstCmd(cmd)) {
            return true;
        }
        return false;
    }

}
