package commandLine.commandImpl;

import backup.load.Load;
import backup.load.LoadImpl;
import commandLine.Command;
import java.io.IOException;
import launch.play.Play;
import org.jdom2.JDOMException;
import zoo.IZoo;

/**
 *
 * @author doyenm
 */
public class LoadZoo implements Command {

    Play play;

    boolean previousHasBeenSuccessfull = false;

    @Override
    public boolean hasInitiateAZoo() {
        return this.previousHasBeenSuccessfull;
    }
    
    public LoadZoo(Play play) {
        this.play = play;
    }
    
    boolean success = false;
    
     @Override
    public boolean isSuccess() {
        return this.success;
    }

    @Override
    public String execute(String[] cmd) {
        try {
            Load load = new LoadImpl();
            IZoo zoo = load.loadZoo("gameBackUps/" + cmd[1] + ".xml");
            this.play.setZoo(zoo);
            this.play.setOption(zoo.getOption());
            this.previousHasBeenSuccessfull = true;
            this.success = true;
            return this.play.getOption().getGeneralCmdBundle().getString("ZOO_CREATION_SUCESS");
        } catch (IOException | JDOMException ex) {
            this.previousHasBeenSuccessfull = false;
            return ex.getMessage();
        }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length == 2) {
            if (cmd[0].equals("load")) {
                return true;
            }
        }
        return false;
    }
}
