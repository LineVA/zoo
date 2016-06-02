package commandLine.commandImpl;

import commandLine.Command;
import exception.IncorrectDataException;
import exception.name.AlreadyUsedNameException;
import exception.name.EmptyNameException;
import exception.name.UnknownNameException;
import main.Play;
import zoo.animal.Animal;
import zoo.animal.reproduction.Sex;
import zoo.animal.specie.Specie;
import zoo.paddock.IPaddock;

/**
 *
 * @author doyenm
 */
public class CreateAnimal implements Command {

    Play play;

    public CreateAnimal(Play play) {
        this.play = play;
    }

    @Override
    public String execute(String[] cmd) {
        try {
            IPaddock pad = this.play.zoo.findPaddockByName(cmd[2]);
            Specie specie = this.play.zoo.findSpeciebyName(cmd[4]);
            Sex sex = Sex.MALE.findByName(cmd[5]);
            Animal animal = new Animal(specie, cmd[3], pad, sex);
            pad.addAnimal(animal);
            return "The animal has been created.";
        } catch (EmptyNameException | UnknownNameException | AlreadyUsedNameException | IncorrectDataException ex) {
            return ex.getMessage();
        }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length == 6) {
            if (cmd[0].equals("animal") && cmd[1].equals("create")) {
                return true;
            }
        }
        return false;
    }

}
