package commandLine.commandImpl;

import backup.load.Load;
import backup.load.LoadImpl;
import commandLine.AbstractCommand;
import commandLine.Command;
import commandLine.ReturnExec;
import commandLine.TypeReturn;
import java.io.IOException;
import launch.play.Play;
import org.jdom2.JDOMException;

/**
 *
 * @author doyenm
 */
public class LoadTutorial extends AbstractCommand implements Command {

    Play play;

    boolean previousHasBeenSuccessfull = false;

    @Override
    public boolean hasInitiateAZoo() {
        return this.previousHasBeenSuccessfull;
    }

    public LoadTutorial(Play play) {
        this.play = play;
    }

    boolean success = false;

    @Override
    public boolean isSuccess() {
        return this.success;
    }

    @Override
    public ReturnExec execute(String[] cmd) {
            Load load = new LoadImpl();
        try {
            this.play.setZoo(load.loadZoo("gameBackUps/" + cmd[1] + ".xml"));
            this.previousHasBeenSuccessfull = true;
            this.success = true;
            return new ReturnExec("You are now in your new zoo.", TypeReturn.SUCCESS);
        } catch (IOException | JDOMException ex){
             this.previousHasBeenSuccessfull = false;
            return new ReturnExec(ex.getMessage(), TypeReturn.ERROR);
        }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        return false;
    }

}
