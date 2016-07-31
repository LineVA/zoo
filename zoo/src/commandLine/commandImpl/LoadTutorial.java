package commandLine.commandImpl;

import backup.load.Load;
import backup.load.LoadImpl;
import commandLine.Command;
import launch.play.Play;

/**
 *
 * @author doyenm
 */
public class LoadTutorial implements Command{

   Play play;

    boolean previousHasBeenSuccessfull = false;

    @Override
    public boolean hasInitiateAZoo() {
        return this.previousHasBeenSuccessfull;
    }
    
    public LoadTutorial(Play play) {
        this.play = play;
    }

    @Override
    public String execute(String[] cmd) {
        try {
            Load load = new LoadImpl();
            this.play.setZoo(load.loadZoo("gameBackUps/" + cmd[1] + ".xml"));
            this.previousHasBeenSuccessfull = true;
            return "You are now in your new zoo.";
        } catch (Exception ex) {
            this.previousHasBeenSuccessfull = false;
            return ex.getMessage();
        }
    }

    @Override
    public boolean canExecute(String[] cmd) {
//        if (cmd.length == 2) {
//            if (cmd[0].equals("tutorial")) {
//                return true;
//            }
//        }
        return false;
    }

}
