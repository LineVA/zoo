package launch.play;

import java.util.ResourceBundle;
import launch.options.Option;
import commandLine.commandManagerImpl.FreeCommandManager;

/**
 *
 * @author doyenm
 */
public class FreePlayImpl extends Play {

//    @Getter
//    public ResourceBundle bundle;
//    @Getter
//    @Setter
//    public Option option;
//
//    @Getter
//    @Setter
//    public IZoo zoo;
//    @Getter
//    private CommandManager manager;

    public FreePlayImpl(ResourceBundle bundle, Option opt) {
        super(bundle, opt);
        super.setManager(new FreeCommandManager(this, opt));
    }
}
