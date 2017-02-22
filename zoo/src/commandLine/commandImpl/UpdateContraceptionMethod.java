package commandLine.commandImpl;

import basicGui.FormattingDisplay;
import commandLine.AbstractCommand;
import commandLine.ReturnExec;
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
public class UpdateContraceptionMethod extends AbstractCommand {

    public UpdateContraceptionMethod(Play play) {
        super(play);
    }

    @Override
    public ReturnExec execute(String[] cmd) {
        try {
            List<String> result = new ArrayList<>();
            Animal animal = super.getPlay().getZoo().findAnimalByName(cmd[1]);
            animal.changeContraceptionMethod(cmd[3]);
            result.add(MessageFormat.format(
                    super.getPlay().getOption().getGeneralCmdBundle().getString("ANIMALS_CONTRACEPTION_METHOD"),
                    cmd[1], cmd[3]));
            return new ReturnExec(FormattingDisplay.formattingList(result, false), TypeReturn.SUCCESS);
        } catch (EmptyNameException | UnknownNameException ex) {
            return new ReturnExec(ex.getMessage(), TypeReturn.ERROR);
        }
    }

    private boolean firstCmd(String[] cmd) {
        if (cmd.length == 4) {
            if (Constants.ANIMAL.equalsIgnoreCase(cmd[0])) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (firstCmd(cmd)) {
            return Arrays.asList(Constants.CONTRACEPTIONMETHOD_ARG).contains(cmd[2]);
        }
        return false;
    }
}
