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

    private String fileNameToLoad = null;

    public LoadZoo(Play play) {
        super(play);
    }

    private ReturnExec executeChanging(String[] cmd) {
        try {
            Load load = new LoadImpl();
            IZoo zoo;
            if (this.fileNameToLoad != null) {
                zoo = load.loadZoo("gameBackUps/" + this.fileNameToLoad + ".xml");
            } else {
                zoo = load.loadZoo("gameBackUps/" + cmd[1] + ".xml");
            }
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
        } catch (Exception ex) {
            return new ReturnExec(
                    super.getPlay().getOption().getGeneralCmdBundle().getString("CORRUPTED_FILE"), TypeReturn.ERROR);
        }
    }

    public ReturnExec confirmChangingZoo(String[] cmd) {
        super.setChangingZoo(false);
        if (cmd.length == 1) {
            if ("no".equalsIgnoreCase(cmd[0]) || "n".equalsIgnoreCase(cmd[0])) {
                return executeChanging(cmd);
            }
        }
        return new ReturnExec("Le zoo n'a pas été sauvegardé", TypeReturn.ERROR);
    }

    private ReturnExec checkBeforeChangingZoo(String[] cmd) {
        this.fileNameToLoad = cmd[1];
        super.setChangingZoo(true);
        super.setInitiate(true);
        return new ReturnExec("Voulez-vous sauvegarder avant de quitter ce zoo ?",
                TypeReturn.QUESTION);
    }

    @Override
    public ReturnExec execute(String[] cmd) {
        if (super.isChangingZoo()) {
            return executeChanging(cmd);
        } else {
            return checkBeforeChangingZoo(cmd);
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
