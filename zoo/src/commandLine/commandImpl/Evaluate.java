package commandLine.commandImpl;

import commandLine.Command;
import exception.IncorrectDataException;
import exception.name.AlreadyUsedNameException;
import exception.name.UnknownNameException;

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
           zooEvaluation += zoo.wellBeing();
            // Special events :
            // ageing
            zoo.ageing();
            // birth
            zoo.birth();
            // death
            zoo.death();
            // Zoo evaluation
           zooEvaluation += zoo.evaluate();
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
