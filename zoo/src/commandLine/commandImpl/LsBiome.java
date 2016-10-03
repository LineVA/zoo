package commandLine.commandImpl;

import basicGui.FormattingDisplay;
import commandLine.Command;
import commandLine.ReturnExec;
import commandLine.TypeReturn;
import launch.play.Play;
import zoo.paddock.biome.Biome;

/**
 *
 * @author doyenm
 */
public class LsBiome implements Command {

    Play play;

    public LsBiome(Play play) {
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
            return new ReturnExec(FormattingDisplay.formattingArrayList(Biome.NONE.list()), TypeReturn.SUCCESS);
    }

    @Override
    public boolean canExecute(String[] cmd) {
        return cmd.length == 2 && cmd[0].equals("biome") && cmd[1].equals("ls");
    }
}
