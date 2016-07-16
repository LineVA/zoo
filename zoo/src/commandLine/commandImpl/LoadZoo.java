package commandLine.commandImpl;

import backup.load.Load;
import backup.load.LoadImpl;
import commandLine.Command;
import main.Play;

/**
 *
 * @author doyenm
 */
public class LoadZoo implements Command {

    Play play;

    public LoadZoo(Play play) {
        this.play = play;
    }

    @Override
    public String execute(String[] cmd) {
        try {
            Load load = new LoadImpl();
            this.play.zoo = load.loadZoo("gameBackUps/" + cmd[1] + ".xml");
            return "You are now in your new zoo.";
        } catch (Exception ex) {
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
