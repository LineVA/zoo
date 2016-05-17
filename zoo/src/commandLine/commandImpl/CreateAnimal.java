package commandLine.commandImpl;

import commandLine.Command;
import exception.name.AlreadyUsedNameException;
import exception.name.EmptyNameException;
import exception.name.UnknownNameException;
import zoo.animal.Animal;
import zoo.animal.reproduction.Sex;
import zoo.animal.specie.Specie;
import zoo.paddock.Paddock;

/**
 *
 * @author doyenm
 */
public class CreateAnimal implements Command {

    @Override
    public String execute(String[] cmd) {
        try {
            Paddock pad = this.zoo.findPaddockByName(cmd[2]);
            Specie specie = this.zoo.findSpeciebyName(cmd[4]);
            Sex sex = Sex.MALE.findByName(cmd[5]);
            Animal animal = new Animal(specie, cmd[3], pad, sex, Integer.parseInt(cmd[6]));
            pad.addAnimal(animal);
            return "Yhe animal has been created.";
        } catch (EmptyNameException | UnknownNameException  | AlreadyUsedNameException ex) {
            return ex.getMessage();
        }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length == 7) {
            if (cmd[0].equals("animal") && cmd[1].equals("create")) {
                return true;
            }
        }
        return false;
    }

}
