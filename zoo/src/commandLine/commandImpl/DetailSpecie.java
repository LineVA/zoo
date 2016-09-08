package commandLine.commandImpl;

import basicGui.FormattingDisplay;
import commandLine.Command;
import exception.name.EmptyNameException;
import exception.name.UnknownNameException;
import launch.play.Play;
import zoo.animal.specie.Specie;

/**
 *
 * @author doyenm
 */
public class DetailSpecie implements Command {

    Play play;

    public DetailSpecie(Play play) {
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
            Specie spec = this.play.getZoo().findSpecieByName(cmd[1]);
            this.success = true;
            return (FormattingDisplay.formattingArrayList(spec.info(this.play.getOption())));
        } catch (UnknownNameException | EmptyNameException ex) {
          return ex.getMessage();
        }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length == 2) {
            if (cmd[0].equals("spec") || cmd[0].equals("specie")) {
                return true;
            }
        }
        return false;
    }
}
