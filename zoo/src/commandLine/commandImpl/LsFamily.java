package commandLine.commandImpl;

import basicGui.FormattingDisplay;
import commandLine.Command;
import commandLine.ReturnExec;
import commandLine.TypeReturn;
import launch.play.Play;
import zoo.animal.specie.Family;
import zoo.paddock.biome.Continent;

/**
 *
 * @author doyenm
 */
public class LsFamily implements Command {

    Play play;

    public LsFamily(Play play) {
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
        return new ReturnExec(FormattingDisplay.formattingArrayList(Family.UNKNOWN.list()), TypeReturn.SUCCESS);
    }

    @Override
    public boolean canExecute(String[] cmd) {
        return cmd.length == 2 && cmd[0].equals("family") && cmd[1].equals("ls");
    }
}
