package commandLine.commandImpl;

import basicGui.FormattingDisplay;
import commandLine.Command;
import exception.IncorrectDataException;
import exception.name.AlreadyUsedNameException;
import exception.name.UnknownNameException;
import java.util.ArrayList;
import launch.Play;

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
        ArrayList<String> info = new ArrayList<>();
        int zooEvaluation = 0;
        try {
            // Well-beeing of each animal
           zooEvaluation += this.play.zoo.grade();
            // Special events :
            // ageing
            this.play.zoo.ageing();
            // birth
            info.addAll(this.play.zoo.birth());
            // death
            info.addAll(this.play.zoo.death());
            // Zoo evaluation
           zooEvaluation += this.play.zoo.evaluate();
        } catch (UnknownNameException | AlreadyUsedNameException | IncorrectDataException ex) {
            return ex.getMessage();
        }
        info.add("The evaluation of the zoo : " + zooEvaluation);
        return FormattingDisplay.formattingArrayList(info);
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
