package commandLine.commandImpl;

import commandLine.AbstractCommand;
import commandLine.ReturnExec;
import launch.play.Play;

/**
 *
 * @author doyenm
 */
public class BiomeAttributesPaddock extends AbstractCommand {

    public BiomeAttributesPaddock(Play play) {
        super(play);
    }

    @Override
    public ReturnExec execute(String[] cmd) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean canExecute(String[] cmd) {
        return false;
    }
}
