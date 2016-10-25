package commandLine;

import launch.play.Play;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author doyenm
 */
public abstract class AbstractCommand implements Command {

    @Getter
    @Setter
    private boolean isSaving = false;

    @Getter
    private Play play;
    @Getter
    @Setter
    private boolean success = false;

    @Override
    public boolean isSuccess() {
        return this.success;
    }
    
    public AbstractCommand(Play play){
        this.play = play;
    }
    
     @Override
    public boolean hasInitiateAZoo() {
        return false;
    }

}
