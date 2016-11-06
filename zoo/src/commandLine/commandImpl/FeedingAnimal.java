package commandLine.commandImpl;

import commandLine.AbstractCommand;
import commandLine.ReturnExec;
import commandLine.TypeReturn;
import exception.IncorrectLoadException;
import exception.name.EmptyNameException;
import exception.name.UnknownNameException;
import java.text.MessageFormat;
import java.util.Arrays;
import launch.play.Play;
import utils.Constants;
import zoo.animal.Animal;

/**
 *
 * @author doyenm
 */
public class FeedingAnimal extends AbstractCommand {

    // args[0] : the argument after '--diet'
    // args[1] : the argument after '--foodQuantity'
    String[] args;

    public FeedingAnimal(Play play) {
        super(play);
    }

    @Override
    public ReturnExec execute(String[] cmd) {
        try {
            String result = "";
            Animal animal = super.getPlay().getZoo().findAnimalByName(cmd[1]);
            if (args[0] != null) {
                animal.changeDiet(args[0]);
                result += MessageFormat.format(
                        super.getPlay().getOption().getGeneralCmdBundle().getString("ANIMALS_DIET"), 
                        cmd[1],args[0],"\n");
            }
            if (args[1] != null) {
                try {
                    animal.changeFoodQuantity(Double.parseDouble(args[1]));
                    result += MessageFormat.format(
                            super.getPlay().getOption().getGeneralCmdBundle().getString("ANIMALS_FOOD_QUANTITY"), cmd[1], args[1]);
                } catch (java.lang.NumberFormatException ex) {
                    return new ReturnExec(
                          super.getPlay().getOption().getGeneralCmdBundle().getString("NUMBER_FORMAT_EXCEPTION"), 
                            TypeReturn.ERROR);
                }
            }
            return new ReturnExec(result, TypeReturn.SUCCESS);
        } catch (EmptyNameException | UnknownNameException |
                IncorrectLoadException | NumberFormatException ex) {
            return new ReturnExec(ex.getMessage(), TypeReturn.ERROR);
        }
    }

    private boolean firstCmd(String[] cmd) {
        if (cmd.length >= 4 && cmd.length < 9 && cmd.length % 2 == 0) {
            if (Constants.ANIMAL.equalsIgnoreCase(cmd[0])) {
                return true;
            }
        }
        return false;
    }

    private boolean hasArgumentDiet(String cmd) {
        return Arrays.asList(Constants.DIET_ARG).contains(cmd);
    }

    private boolean hasArgumentFoodQuantity(String cmd) {
              return Arrays.asList(Constants.FOODQUANTITY_ARG).contains(cmd);
    }

    @Override
    public boolean canExecute(String[] cmd) {
        this.args = new String[]{null, null};
        if (firstCmd(cmd)) {
            if (cmd.length == 4 && this.hasArgumentDiet(cmd[2])) {
                args[0] = cmd[3];
                return true;
            } else if (cmd.length == 4 && this.hasArgumentFoodQuantity(cmd[2])) {
                args[1] = cmd[3];
                return true;
            } else if (cmd.length == 6 && this.hasArgumentDiet(cmd[2])
                    && this.hasArgumentFoodQuantity(cmd[4])) {
                args[0] = cmd[3];
                args[1] = cmd[5];
                return true;
            } else if (cmd.length == 6 && this.hasArgumentFoodQuantity(cmd[2])
                    && this.hasArgumentDiet(cmd[4])) {
                args[0] = cmd[5];
                args[1] = cmd[3];
                return true;
            }
        }
        return false;
    }
}
