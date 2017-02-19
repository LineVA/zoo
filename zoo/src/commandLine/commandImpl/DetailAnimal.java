package commandLine.commandImpl;

import basicGui.FormattingDisplay;
import commandLine.AbstractCommand;
import commandLine.ReturnExec;
import commandLine.TypeReturn;
import exception.IncorrectDataException;
import exception.name.EmptyNameException;
import exception.name.UnknownNameException;
import launch.play.Play;
import utils.Constants;
import zoo.animal.Animal;

/**
 *
 * @author doyenm
 */
public class DetailAnimal extends AbstractCommand {

    public DetailAnimal(Play play) {
        super(play);
    }

    @Override
    public ReturnExec execute(String[] cmd) {
        try {
            Animal animal = super.getPlay().getZoo().findAnimalByName(cmd[1]);
           super.setSuccess(true);
            return new ReturnExec(FormattingDisplay.formattingList(animal.info(), false),TypeReturn.SUCCESS);
        } catch (UnknownNameException | EmptyNameException | IncorrectDataException ex) {
            return new ReturnExec(ex.getMessage(), TypeReturn.ERROR);
        }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length == 2) {
            if (Constants.ANIMAL.equalsIgnoreCase(cmd[0])) {
                return true;
            }
        }
        return false;
    }

}
