package commandLine.commandImpl;

import basicGui.FormattingDisplay;
import commandLine.AbstractCommand;
import commandLine.ReturnExec;
import commandLine.TypeReturn;
import exception.IncorrectDataException;
import exception.IncorrectLoadException;
import exception.name.NameException;
import java.util.ArrayList;
import java.util.List;
import launch.play.Play;
import utils.Constants;
import utils.Utils;

/**
 *
 * @author doyenm
 */
public class Evaluate extends AbstractCommand {

    public Evaluate(Play play) {
        super(play);
    }

    @Override
    public ReturnExec execute(String[] cmd) {
        List<String> info = new ArrayList<>();
        double zooEvaluation = 0;
        try {
            // Evolution of the animalKeepers 
            super.getPlay().getZoo().evolveAnimalKeepers();
            // Well-beeing of each animal
            zooEvaluation += super.getPlay().getZoo().grade();
            // Special events :
            // ageing, births and deaths
            info.addAll(super.getPlay().getZoo().ageing());
            // Zoo evaluation
            zooEvaluation += super.getPlay().getZoo().evaluate();
            super.setSuccess(true);
        } catch (IncorrectDataException | NameException | IncorrectLoadException ex) {
            return new ReturnExec(ex.getMessage(), TypeReturn.ERROR);
        }
        info.add(super.getPlay().getOption().getGeneralCmdBundle()
                .getString("ZOO_EVALUATION") + Utils.truncate(zooEvaluation));
        return new ReturnExec(FormattingDisplay.formattingList(info, false), TypeReturn.SUCCESS);
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length == 1) {
            if (Constants.EVALUATE.equalsIgnoreCase(cmd[0])) {
                return true;
            }
        }
        return false;
    }

}
