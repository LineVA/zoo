package commandLine.commandImpl;

import backup.load.Load;
import backup.load.LoadImpl;
import commandLine.AbstractCommand;
import commandLine.ReturnExec;
import commandLine.TypeReturn;
import java.io.IOException;
import launch.play.Play;
import org.jdom2.JDOMException;

/**
 *
 * @author doyenm
 */
public class LoadTutorial extends AbstractCommand{

    public LoadTutorial(Play play) {
        super(play);
    }

    @Override
    public ReturnExec execute(String[] cmd) {
            Load load = new LoadImpl();
        try {
            super.getPlay().setZoo(load.loadZoo("gameBackUps/" + cmd[1] + ".xml"));
          super.setSuccess(true);
            return new ReturnExec("You are now in your new zoo.", TypeReturn.SUCCESS);
        } catch (IOException | JDOMException ex){
            return new ReturnExec(ex.getMessage(), TypeReturn.ERROR);
        }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        return false;
    }

}
