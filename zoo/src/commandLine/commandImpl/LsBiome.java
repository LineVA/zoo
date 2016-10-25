package commandLine.commandImpl;

import basicGui.FormattingDisplay;
import commandLine.AbstractCommand;
import commandLine.ReturnExec;
import commandLine.TypeReturn;
import launch.play.Play;
import zoo.paddock.biome.Biome;

/**
 *
 * @author doyenm
 */
public class LsBiome extends AbstractCommand  {

    public LsBiome(Play play) {
        super(play);
    }

    @Override
    public ReturnExec execute(String[] cmd) {
        super.setSuccess(true);
            return new ReturnExec(FormattingDisplay.formattingArrayList(Biome.NONE.list()), TypeReturn.SUCCESS);
    }

    @Override
    public boolean canExecute(String[] cmd) {
        return cmd.length == 2 && cmd[0].equals("biome") && cmd[1].equals("ls");
    }
}
