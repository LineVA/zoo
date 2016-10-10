package commandLine.commandImpl;

import basicGui.FormattingDisplay;
import commandLine.Command;
import commandLine.ReturnExec;
import commandLine.TypeReturn;
import exception.IncorrectDataException;
import exception.IncorrectLoadException;
import exception.name.NameException;
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
    
    boolean success = false;
    
     @Override
    public boolean isSuccess() {
        return this.success;
    }
    
    @Override
    public  ReturnExec execute(String[] cmd) {
        ArrayList<String> info = new ArrayList<>();
        int zooEvaluation = 0;
        try {
            // Well-beeing of each animal
           zooEvaluation += this.play.getZoo().grade();
            // Special events :
            // ageing, births and deaths
            info.addAll(this.play.getZoo().ageing());
            // Zoo evaluation
           zooEvaluation += this.play.getZoo().evaluate();
           this.success = true;
        } catch (
                IncorrectDataException | NameException | IncorrectLoadException ex) {
            return new ReturnExec(ex.getMessage(), TypeReturn.ERROR);
        }
        info.add(this.play.getOption().getGeneralCmdBundle()
                .getString("ZOO_EVALUATION") + zooEvaluation);
        return new ReturnExec(FormattingDisplay.formattingArrayList(info), TypeReturn.SUCCESS);
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
