package commandLine.commandImpl;

import backup.load.Load;
import backup.load.LoadImpl;
import commandLine.Command;
import exception.name.EmptyNameException;
import java.io.IOException;
import launch.play.Play;
import org.jdom2.JDOMException;

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

    @Override
    public String execute(String[] cmd) {
        try {
            Load load = new LoadImpl();
            this.play.setZoo(load.loadZoo("gameBackUps/" + cmd[1] + ".xml"));
            this.previousHasBeenSuccessfull = true;
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
