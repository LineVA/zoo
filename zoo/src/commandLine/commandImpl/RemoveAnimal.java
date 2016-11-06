package commandLine.commandImpl;

import commandLine.AbstractCommand;
import commandLine.ReturnExec;
import commandLine.TypeReturn;
import exception.name.EmptyNameException;
import exception.name.UnknownNameException;
import launch.play.Play;
import utils.Constants;
import zoo.animal.Animal;

/**
 *
 * @author doyenm
 */
public class RemoveAnimal extends AbstractCommand  {

    public RemoveAnimal(Play play) {
        super(play);
    }

    @Override
    public ReturnExec execute(String[] cmd) {
        try {
            Animal animal = super.getPlay().getZoo().findAnimalByName(cmd[2]);
            animal.getPaddock().removeAnimal(animal);
            super.setSuccess(true);
            return new ReturnExec(super.getPlay().getOption().getGeneralCmdBundle()
                    .getString("ANIMAL_REMOVE_SUCCESS"), TypeReturn.SUCCESS);
        } catch (EmptyNameException | UnknownNameException ex) {
            return new ReturnExec(ex.getMessage(), TypeReturn.ERROR);
        }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length == 3) {
            if (Constants.ANIMAL.equalsIgnoreCase(cmd[0]) && "remove".equalsIgnoreCase(cmd[1])) {
                return true;
            }
        }
        return false;
    }
}
