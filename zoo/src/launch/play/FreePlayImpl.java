package launch.play;

import launch.options.Option;
import commandLine.commandManagerImpl.FreeCommandManager;

/**
 *
 * @author doyenm
 */
public class FreePlayImpl extends Play {

    public FreePlayImpl(Option opt) {
        super(opt);
        super.setManager(new FreeCommandManager(this, opt));
    }
}
