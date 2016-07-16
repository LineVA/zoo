package commandLine.commandImpl;

import commandLine.Command;
import exception.IncorrectDimensionsException;
import exception.name.NameException;
import main.Play;

/**
 *
 * @author doyenm
 */
public class CreatePaddock implements Command {

            Play play;
    
    public CreatePaddock(Play play){
        this.play = play;
    }
    
    @Override
    public String execute(String[] cmd) {
        // TO DO : if zoo has not been yet initiate
        try {
            this.play.zoo.addPaddock(cmd[2], Integer.parseInt(cmd[3]), Integer.parseInt(cmd[4]),
                    Integer.parseInt(cmd[5]), Integer.parseInt(cmd[6]));
            return "Your paddock has been sucessfully created.";
        } catch (NameException | IncorrectDimensionsException ex) {
            return ex.getMessage();
        }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length == 7) {
            if (cmd[0].equals("pad") || cmd[0].equals("paddock")) {
                if (cmd[1].equals("create")) {
                    return true;
                }
            }
        }
        return false;
    }

}
