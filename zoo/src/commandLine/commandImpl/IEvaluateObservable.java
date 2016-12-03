package commandLine.commandImpl;

import commandLine.Command;
import java.util.Observable;
import launch.play.Play;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author doyenm
 */
public abstract class IEvaluateObservable extends Observable implements Command {

    @Getter
    @Setter
    private boolean isSaving = false;

    @Getter
    @Setter
    private boolean isChangingZoo = false;

    @Getter
    private Play play;
    @Getter
    @Setter
    private boolean success = false;
    @Getter
    @Setter
    private boolean initiate = false;

    public IEvaluateObservable(Play play) {
        this.play = play;
    }
}
