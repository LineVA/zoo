package launch;

import commandLine.CommandManager;
import commandLine.commandManagerImpl.TutorialCommandLineManager;
import launch.play.Play;
import lombok.Getter;
import lombok.Setter;
import zoo.IZoo;
import zoo.Zoo;

/**
 *
 * @author doyenm
 */
public class ScenarioPlayImpl implements Play {

    @Getter
    @Setter
    public IZoo zoo;
    @Getter
    private CommandManager manager;

    public ScenarioPlayImpl() {
        this.zoo = new Zoo();
        this.manager = new TutorialCommandLineManager(this, null);
    }
}
