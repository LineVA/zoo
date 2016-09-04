package commandLine.commandImpl;

import basicGui.FormattingDisplay;
import commandLine.Command;
import launch.play.Play;
import zoo.animal.conservation.ConservationStatus;
import zoo.paddock.biome.Biome;

/**
 *
 * @author doyenm
 */
public class LsConservation implements Command {

    Play play;

    public LsConservation(Play play) {
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
        this.success = success;
        return FormattingDisplay.formattingArrayList(ConservationStatus.UNKNOWN.list());
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length == 2 && cmd[0].equals("conservation") && cmd[1].equals("ls")) {
            return true;
        }
        return false;
    }
}
