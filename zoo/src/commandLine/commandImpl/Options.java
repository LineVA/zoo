package commandLine.commandImpl;

import commandLine.Command;
import exception.name.EmptyNameException;
import exception.name.UnknownNameException;
import launch.play.Play;
import zoo.animal.Animal;

/**
 *
 * @author doyenm
 */
public class Options implements Command{
  Play play;

    public Options(Play play) {
        this.play = play;
    }
    
      @Override
    public boolean hasInitiateAZoo() {
        return false;
    }

    @Override
    public String execute(String[] cmd) {
        this.play.getOption().setLanguage(cmd[2]);
        return "OK";
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
}
