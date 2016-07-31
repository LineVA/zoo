package commandLine.commandImpl;

import commandLine.Command;
import exception.name.EmptyNameException;
import backup.save.Save;
import backup.save.SaveImpl;
import launch.Play;

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

    @Override
    public String execute(String[] cmd) {
        Save saveProcess = new SaveImpl();
        try {
            saveProcess.saveZoo(this.play.getZoo(), cmd[1]);
            return "Your zoo has been saved.";
        } catch (EmptyNameException ex) {
            return ex.getMessage();
            //  Logger.getLogger(SaveZoo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length == 2) {
            if (cmd[0].equals("save")) {
                return true;
            }
        }
        return false;
    }
}
