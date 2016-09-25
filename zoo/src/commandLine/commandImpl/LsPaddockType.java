
package commandLine.commandImpl;

import basicGui.FormattingDisplay;
import commandLine.Command;
import launch.play.Play;
import zoo.paddock.PaddockTypes;
import zoo.paddock.biome.Biome;

/**
 *
 * @author doyenm
 */
public class LsPaddockType implements Command{

    Play play;

    public LsPaddockType(Play play) {
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
        return FormattingDisplay.formattingArrayList(PaddockTypes.UNKNOWN.list());
    }

    @Override
    public boolean canExecute(String[] cmd) {
        return cmd.length == 2 && (cmd[0].equals("paddockType") || "padType".equals(cmd[0])) && cmd[1].equals("ls");
    }
}
