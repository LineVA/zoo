package launch.play;

import java.util.ResourceBundle;
import launch.options.Option;
import commandLine.commandManagerImpl.FreeCommandManager;

/**
 *
 * @author doyenm
 */
public class FreePlayImpl extends Play {

    public FreePlayImpl(ResourceBundle bundle, Option opt) {
        super(bundle, opt);
        super.setManager(new FreeCommandManager(this, opt));
    }
}
