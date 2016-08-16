package commandLine.commandImpl;

import basicGui.FormattingDisplay;
import commandLine.Command;
import exception.IncorrectDataException;
import exception.name.AlreadyUsedNameException;
import exception.name.EmptyNameException;
import exception.name.UnknownNameException;
import java.util.ArrayList;
import launch.play.Play;

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
    public boolean hasInitiateAZoo() {
        return false;
    }
    
    @Override
    public String execute(String[] cmd) {
        ArrayList<String> info = new ArrayList<>();
        int zooEvaluation = 0;
        try {
            // Well-beeing of each animal
           zooEvaluation += this.play.getZoo().grade();
            // Special events :
            // ageing
            this.play.getZoo().ageing();
            // birth
            info.addAll(this.play.getZoo().birth());
            // death
            info.addAll(this.play.getZoo().death());
            // Zoo evaluation
           zooEvaluation += this.play.getZoo().evaluate();
        } catch (UnknownNameException | AlreadyUsedNameException |
                IncorrectDataException | EmptyNameException ex) {
            return ex.getMessage();
        }
        info.add(this.play.getOption().getGeneralCmdBundle()
                .getString("ZOO_EVALUATION") + zooEvaluation);
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
