package commandLine.commandImpl;

import commandLine.Command;
import java.io.IOException;
import launch.InstanciateSpecies;
import launch.play.Play;
import org.jdom2.JDOMException;

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
        try { 
        this.play.getOption().setLanguage(cmd[2]);
        this.play.getZoo().setSpecies(InstanciateSpecies.instanciateSpecies("resources/species", this.play.getOption()));
        this.success = true;
        return this.play.getOption().getGeneralCmdBundle()
                .getString("OPTION_CHANGE_SUCCESS");
         } catch (JDOMException | IOException ex) {
            return ex.getMessage();
        }
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
