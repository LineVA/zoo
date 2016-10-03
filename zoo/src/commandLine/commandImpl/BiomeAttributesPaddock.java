package commandLine.commandImpl;

import commandLine.Command;
import commandLine.ReturnExec;
import launch.play.Play;

/**
 *
 * @author doyenm
 */
public class BiomeAttributesPaddock implements Command {

    Play play;
    boolean success = false;
    
     @Override
    public boolean isSuccess() {
        return this.success;
    }

    public BiomeAttributesPaddock(Play play) {
        this.play = play;
    }

    @Override
    public boolean hasInitiateAZoo() {
        return false;
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
