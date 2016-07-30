package commandLine.commandImpl;

import basicGui.FormattingDisplay;
import commandLine.Command;
import exception.name.EmptyNameException;
import exception.name.UnknownNameException;
import launch.Play;
import zoo.animal.Animal;

/**
 *
 * @author doyenm
 */
public class DetailAnimal implements Command {

    Play play;

    public DetailAnimal(Play play) {
        this.play = play;
    }
    
      @Override
    public boolean hasInitiateAZoo() {
        return false;
    }

    @Override
    public String execute(String[] cmd) {
        try {
            Animal animal = this.play.zoo.findAnimalByName(cmd[1]);
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
