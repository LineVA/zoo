package commandLine.commandImpl;

import basicGui.FormattingDisplay;
import commandLine.AbstractCommand;
import commandLine.ReturnExec;
import commandLine.SplittingAmpersand;
import commandLine.TypeReturn;
import exception.IncorrectLoadException;
import exception.name.EmptyNameException;
import exception.name.UnknownNameException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
    // args[2] : the argument after "--fastDay"
    String[] args;

    public FeedingAnimal(Play play) {
        super(play);
    }

    @Override
    public ReturnExec execute(String[] cmd) {
        try {
            List<String> result = new ArrayList<>();
            Animal animal = super.getPlay().getZoo().findAnimalByName(cmd[1]);
            if (args[0] != null) {
                animal.changeDiet(args[0]);
                result.add(MessageFormat.format(
                        super.getPlay().getOption().getGeneralCmdBundle().getString("ANIMALS_DIET"),
                        cmd[1], args[0]));
            }
            if (args[1] != null) {
                try {
                    animal.changeFoodQuantity(Double.parseDouble(args[1]));
                    result.add(MessageFormat.format(
                            super.getPlay().getOption().getGeneralCmdBundle().getString("ANIMALS_FOOD_QUANTITY"),
                            cmd[1], args[1]));
                } catch (java.lang.NumberFormatException ex) {
                    return new ReturnExec(
                            super.getPlay().getOption().getGeneralCmdBundle().getString("NUMBER_FORMAT_EXCEPTION"),
                            TypeReturn.ERROR);
                }
            }
              if (args[2] != null) {
                try {
                    animal.changeFastDays(Integer.parseInt(args[2]));
                    result.add(MessageFormat.format(
                            super.getPlay().getOption().getGeneralCmdBundle().getString("ANIMALS_FAST_DAYS"),
                            cmd[1], args[2]));
                } catch (java.lang.NumberFormatException ex) {
                    return new ReturnExec(
                            super.getPlay().getOption().getGeneralCmdBundle().getString("NUMBER_FORMAT_EXCEPTION"),
                            TypeReturn.ERROR);
                }
            }
            return new ReturnExec(FormattingDisplay.formattingList(result), TypeReturn.SUCCESS);
        } catch (EmptyNameException | UnknownNameException |
                IncorrectLoadException | NumberFormatException ex) {
            return new ReturnExec(ex.getMessage(), TypeReturn.ERROR);
        }
    }

    private boolean firstCmd(String[] cmd) {
        if (cmd.length >= 4 && cmd.length < 8 && cmd.length % 2 == 0) {
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
    
       private boolean hasArgumentFastDay(String cmd) {
        return Arrays.asList(Constants.FASTDAY_ARG).contains(cmd);
    }

   private boolean saveArgument(String arg, String value) {
     if (this.hasArgumentFastDay(arg)) {
            this.args[2] = value;
        } else if(this.hasArgumentDiet(arg)){
            this.args[0] = value;
        } else if(this.hasArgumentFoodQuantity(arg)){
            this.args[1] = value;
         } else {
            return false;
        }
        return true;
    }

    @Override
    public boolean canExecute(String[] cmd) {
        this.args = new String[]{null, null, null};
        if (firstCmd(cmd)) {
            int i = 2;
            while (i <= cmd.length - 2) {
                if (!saveArgument(cmd[i], cmd[i + 1])) {
                    return false;
                }
                i += 2;
            }
            return true;
        }
        return false;
    }
}
