package commandLine.commandImpl;

import commandLine.Command;
import launch.play.Play;

/**
 *
 * @author doyenm
 */
public class Options implements Command{
  Play play;

    public Options(Play play) {
        this.play = play;
    }
    
    boolean success = false;
    
      @Override
    public boolean hasInitiateAZoo() {
        return false;
    }

    @Override
    public String execute(String[] cmd) {
        this.play.getOption().setLanguage(cmd[2]);
        this.success = true;
        return this.play.getOption().getGeneralCmdBundle()
                .getString("OPTION_CHANGE_SUCCESS");
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length == 3) {
            if (cmd[0].equals("option") && cmd[1].equals("-l")) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isSuccess() {
        return this.success;
    }
}
