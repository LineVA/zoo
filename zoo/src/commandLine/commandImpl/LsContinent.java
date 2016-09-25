package commandLine.commandImpl;

import basicGui.FormattingDisplay;
import commandLine.Command;
import launch.play.Play;
import zoo.paddock.biome.Continent;

/**
 *
 * @author doyenm
 */
public class LsContinent implements Command{

    Play play;

    public LsContinent(Play play) {
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
        this.success = true;
        return FormattingDisplay.formattingArrayList(Continent.UNKNOWN.list());
    }

    @Override
    public boolean canExecute(String[] cmd) {
        return cmd.length == 2 && cmd[0].equals("continent") && cmd[1].equals("ls");
    }
}
