package commandLine.commandImpl;

import basicGui.FormattingDisplay;
import commandLine.AbstractCommand;
import commandLine.Command;
import commandLine.ReturnExec;
import commandLine.TypeReturn;
import exception.name.EmptyNameException;
import exception.name.UnknownNameException;
import launch.play.Play;
import zoo.animalKeeper.AnimalKeeper;

/**
 *
 * @author doyenm
 */
public class DetailAnimalKeeper extends AbstractCommand implements Command {

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
    public ReturnExec execute(String[] cmd) {
        try {
            AnimalKeeper keeper = this.play.getZoo().findAnimalKeeperByName(cmd[1]);
            this.success = true;
            return new ReturnExec(FormattingDisplay.formattingArrayList(keeper.info()), TypeReturn.SUCCESS);
        } catch (UnknownNameException | EmptyNameException ex) {
            return new ReturnExec(ex.getMessage(), TypeReturn.ERROR);
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