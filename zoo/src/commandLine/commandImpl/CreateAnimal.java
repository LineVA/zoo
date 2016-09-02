package commandLine.commandImpl;

import commandLine.Command;
import exception.IncorrectDataException;
import exception.name.AlreadyUsedNameException;
import exception.name.EmptyNameException;
import exception.name.UnknownNameException;
import launch.play.Play;
import zoo.animal.Animal;
import zoo.animal.AnimalImpl;
import zoo.animal.reproduction.Sex;
import zoo.animal.specie.Specie;
import zoo.paddock.IPaddock;

/**
 *
 * @author doyenm
 */
public class CreateAnimal implements Command {

    Play play;

    boolean success = false;

    @Override
    public boolean isSuccess() {
        return this.success;
    }

    public CreateAnimal(Play play) {
        this.play = play;
    }

    @Override
    public boolean hasInitiateAZoo() {
        return false;
    }

    @Override
    public String execute(String[] cmd) {
        try {
            this.play.getZoo().findAnimalByName(cmd[2]);
            return this.play.getOption().getGeneralCmdBundle()
                    .getString("ALREADY_EXISTING_ANIMAL_NAME");
        } catch (UnknownNameException ex1) {
            try {
                IPaddock pad = this.play.getZoo().findPaddockByName(cmd[3]);
                Specie specie = this.play.getZoo().findSpeciebyName(cmd[4]);
                Sex sex = Sex.MALE.findByName(cmd[5]);
                Animal animal = new AnimalImpl(specie, cmd[2], pad,
                        sex, this.play.getOption());
                pad.addAnimal(animal);
                this.success = true;
                return this.play.getOption().getGeneralCmdBundle()
                        .getString("ANIMAL_CREATION_SUCCESS");
            } catch (EmptyNameException | UnknownNameException | AlreadyUsedNameException | IncorrectDataException ex) {
                return ex.getMessage();
            }
        } catch (EmptyNameException ex) {
            return ex.getMessage();
        }
    }

    @Override
    public boolean canExecute(String[] cmd
    ) {
        if (cmd.length == 6) {
            if (cmd[0].equals("animal") && cmd[1].equals("create")) {
                return true;
            }
        }
        return false;
    }

}
