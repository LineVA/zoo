package commandLine.commandImpl;

import basicGui.FormattingDisplay;
import commandLine.Command;
import exception.name.EmptyNameException;
import exception.name.UnknownNameException;
import launch.play.Play;
import zoo.animalKeeper.AnimalKeeper;

/**
 *
 * @author doyenm
 */
public class DetailAnimalKeeper implements Command {

    Play play;

    public DetailAnimalKeeper(Play play) {
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
    public String execute(String[] cmd) {
        try {
            AnimalKeeper keeper = this.play.getZoo().findAnimalKeeperByName(cmd[1]);
            this.success = true;
            return (FormattingDisplay.formattingArrayList(keeper.info()));
        } catch (UnknownNameException | EmptyNameException ex) {
            return ex.getMessage();
        }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length == 2) {
            if (cmd[0].equals("animalKeeper") || "aK".equals(cmd[0]) || "ak".equals(cmd[0])) {
                return true;
            }
        }
        return false;
    }

}