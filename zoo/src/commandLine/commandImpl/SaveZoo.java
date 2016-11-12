package commandLine.commandImpl;

import exception.name.EmptyNameException;
import backup.save.Save;
import backup.save.SaveImpl;
import commandLine.AbstractCommand;
import commandLine.ReturnExec;
import commandLine.TypeReturn;
import java.util.Arrays;
import launch.play.Play;
import utils.Constants;

/**
 *
 * @author doyenm
 */
public class SaveZoo extends AbstractCommand {

    public SaveZoo(Play play) {
        super(play);
    }

    private String fileName = null;

    public ReturnExec confirmSaving(String[] cmd) {
        super.setSaving(false);
        if (cmd.length == 1) {
            if (Arrays.asList(Constants.YES_OR_Y).contains(cmd[0])) {
                return executeSaving(new SaveImpl(), cmd);
            }
        }
        return new ReturnExec("Le zoo n'a pas été sauvegardé", TypeReturn.ERROR);
    }

    private ReturnExec executeSaving(Save saveProcess, String[] cmd) {
        try {
            if (fileName == null) {
                saveProcess.saveZoo(super.getPlay().getZoo(), cmd[1]);
            } else {
                saveProcess.saveZoo(super.getPlay().getZoo(), fileName);
            }
            super.setSuccess(true);
            return new ReturnExec(super.getPlay().getOption().getGeneralCmdBundle()
                    .getString("SAVE_SUCCESS"), TypeReturn.SUCCESS);
        } catch (EmptyNameException ex) {
            return new ReturnExec(ex.getMessage(), TypeReturn.ERROR);
        }
    }

    private ReturnExec checkBeforeSaving(Save saveProcess, String[] cmd) {
        try {
            if (saveProcess.isFileAlreadyExisting(cmd[1])) {
                this.fileName = cmd[1];
                super.setSaving(true);
                return new ReturnExec(
                        "Un fichier portant ce nom existe déjà. Voulez-vous l'écraser définitivement ? ",
                        TypeReturn.QUESTION);
            } else {
                return executeSaving(saveProcess, cmd);
            }
        } catch (Exception ex) {
            System.out.println("COVO");
        }
        return null;
    }

    @Override
    public ReturnExec execute(String[] cmd) {
        Save saveProcess = new SaveImpl();
        if (super.isSaving()) {
            return executeSaving(saveProcess, cmd);
        } else {
            return checkBeforeSaving(saveProcess, cmd);
        }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        return cmd.length == 2 && Constants.SAVE.equalsIgnoreCase(cmd[0]);
    }
}
