package commandLine.commandImpl;

import commandLine.Command;
import exception.IncorrectDataException;
import exception.name.AlreadyUsedNameException;
import exception.name.UnknownNameException;
import main.Play;

/**
 *
 * @author doyenm
 */
public class Evaluate implements Command {

     Play play;

    public Evaluate(Play play) {
        this.play = play;
    }
    
    @Override
    public String execute(String[] cmd) {
        int zooEvaluation = 0;
        try {
            // Well-beeing of each animal
           zooEvaluation += this.play.zoo.grade();
            // Special events :
            // ageing
            this.play.zoo.ageing();
            // birth
            this.play.zoo.birth();
            // death
            this.play.zoo.death();
            // Zoo evaluation
           zooEvaluation += this.play.zoo.evaluate();
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
