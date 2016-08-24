package launch.play;

import commandLine.CommandManager;
import commandLine.commandManagerImpl.FreeCommandManager;
import launch.play.Play;
import lombok.Getter;
import lombok.Setter;
import zoo.IZoo;
import zoo.Zoo;

/**
 *
 * @author doyenm
 */
public class FreePlayImpl implements Play {

    @Getter
    @Setter
    public IZoo zoo;
    @Getter
    private CommandManager manager;

    public FreePlayImpl() {
        this.zoo = new Zoo();
        this.manager = new FreeCommandManager(this);
    }

}
