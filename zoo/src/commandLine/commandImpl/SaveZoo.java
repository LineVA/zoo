package commandLine.commandImpl;

import commandLine.Command;
import exception.name.EmptyNameException;
import backup.save.Save;
import backup.save.SaveImpl;
import commandLine.ReturnExec;
import commandLine.TypeReturn;
import launch.play.Play;

/**
 *
 * @author doyenm
 */
public class SaveZoo implements Command {

    Play play;

    public SaveZoo(Play play) {
        this.play = play;
    }

    @Override
    public boolean hasInitiateAZoo() {
        return false;
    }

    boolean success = false;

    @Override
    public boolean isSuccess() {
        return this.success;
    }

    @Override
    public ReturnExec execute(String[] cmd) {
        Save saveProcess = new SaveImpl();
        try {
            saveProcess.saveZoo(this.play.getZoo(), cmd[1]);
            this.success = true;
            return new ReturnExec(this.play.getOption().getGeneralCmdBundle()
                    .getString("SAVE_SUCCESS"), TypeReturn.SUCCESS);
        } catch (EmptyNameException ex) {
            return new ReturnExec(ex.getMessage(), TypeReturn.ERROR);
        }
    }

    @Override
    public boolean canExecute(String[] cmd) {
       return cmd.length==2 && "save".equals(cmd[0]);
    }
}
