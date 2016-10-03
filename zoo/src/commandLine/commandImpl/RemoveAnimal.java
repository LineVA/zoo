package commandLine.commandImpl;

import commandLine.Command;
import commandLine.ReturnExec;
import commandLine.TypeReturn;
import exception.name.EmptyNameException;
import exception.name.UnknownNameException;
import launch.play.Play;
import zoo.animal.Animal;

/**
 *
 * @author doyenm
 */
public class RemoveAnimal implements Command {

    Play play;

    public RemoveAnimal(Play play) {
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
    public ReturnExec execute(String[] cmd) {
        try {
            Animal animal = this.play.getZoo().findAnimalByName(cmd[2]);
            animal.getPaddock().removeAnimal(animal);
            this.success = true;
            return new ReturnExec(this.play.getOption().getGeneralCmdBundle()
                    .getString("ANIMAL_REMOVE_SUCCESS"), TypeReturn.SUCCESS);
        } catch (EmptyNameException | UnknownNameException ex) {
            return new ReturnExec(ex.getMessage(), TypeReturn.ERROR);
        }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length == 3) {
            if ("animal".equals(cmd[0]) && "remove".equals(cmd[1])) {
                return true;
            }
        }
        return false;
    }
}
