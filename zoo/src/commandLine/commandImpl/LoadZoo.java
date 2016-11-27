package commandLine.commandImpl;

import backup.load.Load;
import backup.load.LoadImpl;
import commandLine.AbstractChangeZooCommand;
import commandLine.ReturnExec;
import commandLine.TypeReturn;
import java.io.IOException;
import launch.play.Play;
import org.jdom2.JDOMException;
import utils.Config;
import utils.Constants;
import zoo.IZoo;

/**
 *
 * @author doyenm
 */
public class LoadZoo extends AbstractChangeZooCommand {

    public LoadZoo(Play play) {
        super(play);
    }

    public ReturnExec executeChanging(String[] cmd) {
        try {
            Load load = new LoadImpl();
            IZoo zoo;
            if (super.getPreviousCmd() != null) {
                zoo = load.loadZoo(Config.BACKUP_PATH + super.getPreviousCmd()[1] + Config.BACKUP_EXTENSION);
            } else {
                zoo = load.loadZoo(Config.BACKUP_PATH+ cmd[1] + Config.BACKUP_EXTENSION);
            }
            super.getPlay().setZoo(zoo);
            super.getPlay().setOption(zoo.getOption());
            super.setChangingZoo(false);
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
        } catch (Exception ex) {
            return new ReturnExec(
                    super.getPlay().getOption().getGeneralCmdBundle().getString("CORRUPTED_FILE"), TypeReturn.ERROR);
        }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length == 2) {
            if (Constants.LOAD.equalsIgnoreCase(cmd[0])) {
                return true;
            }
        }
        return false;
    }
}
