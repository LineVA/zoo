package commandLine.commandImpl;

import backup.load.Load;
import backup.load.LoadImpl;
import commandLine.AbstractCommand;
import commandLine.ReturnExec;
import commandLine.TypeReturn;
import java.io.IOException;
import launch.play.Play;
import org.jdom2.JDOMException;
import zoo.IZoo;

/**
 *
 * @author doyenm
 */
public class LoadZoo extends AbstractCommand {

    public LoadZoo(Play play) {
        super(play);
    }

    @Override
    public ReturnExec execute(String[] cmd) {
        try {
            Load load = new LoadImpl();
            IZoo zoo = load.loadZoo("gameBackUps/" + cmd[1] + ".xml");
            super.getPlay().setZoo(zoo);
            super.getPlay().setOption(zoo.getOption());
            super.setInitiate(true);
            super.setSuccess(true);
            return new ReturnExec(
                    super.getPlay().getOption().getGeneralCmdBundle().getString("ZOO_CREATION_SUCESS"), TypeReturn.SUCCESS);
        } catch (JDOMException ex) {
            return new ReturnExec(
                    super.getPlay().getOption().getGeneralCmdBundle().getString("FAIL_LOAD"), TypeReturn.ERROR);
        } catch (IOException ex) {
            return new ReturnExec(
                  super.getPlay().getOption().getGeneralCmdBundle().getString("MISSING_FILE"), TypeReturn.ERROR);
        } catch(Exception ex){
             return new ReturnExec(
                  super.getPlay().getOption().getGeneralCmdBundle().getString("CORRUPTED_FILE"), TypeReturn.ERROR);
        }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length == 2) {
            if (cmd[0].equalsIgnoreCase("load")) {
                return true;
            }
        }
        return false;
    }
}
