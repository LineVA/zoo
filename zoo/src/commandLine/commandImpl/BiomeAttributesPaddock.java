package commandLine.commandImpl;

import commandLine.Command;
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
    public String execute(String[] cmd) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean canExecute(String[] cmd) {
        return false;
    }
}
