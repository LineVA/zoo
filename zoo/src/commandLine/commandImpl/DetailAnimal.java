package commandLine.commandImpl;

import basicGui.FormattingDisplay;
import commandLine.Command;
import exception.name.EmptyNameException;
import exception.name.UnknownNameException;
import zoo.animal.Animal;

/**
 *
 * @author doyenm
 */
public class DetailAnimal implements Command {

    @Override
    public String execute(String[] cmd) {
        try {
            Animal animal = this.zoo.findAnimalByName(cmd[1]);
            return (FormattingDisplay.formattingArrayList(animal.info()));
        } catch (UnknownNameException | EmptyNameException ex) {
            return ex.getMessage();
        }
    }

@Override
        public boolean canExecute(String[] cmd) {
        if (cmd.length == 2) {
            if (cmd[0].equals("animal")) {
                return true;
            }
        }
        return false;
    }

}
