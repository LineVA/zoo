package commandLine.commandImpl;

import commandLine.Command;
import exception.IncorrectDataException;
import exception.name.AlreadyUsedNameException;
import exception.name.EmptyNameException;
import exception.name.UnknownNameException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author doyenm
 */
public class Evaluate implements Command {

    @Override
    public String execute(String[] cmd) {
        int zooEvaluation = 0;
        try {
            // Well-beeing of each animal
            // Special events :
            // birth
            zoo.birth();
            // death
            zoo.death();
            // Zoo evaluation
           zooEvaluation = zoo.evaluate();
        } catch (UnknownNameException | AlreadyUsedNameException | IncorrectDataException ex) {
            return ex.getMessage();
        }
        return "The evaluation of the zoo : " + zooEvaluation;
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length == 1) {
            if (cmd[0].equals("evaluate")) {
                return true;
            }
        }
        return false;
    }

}
