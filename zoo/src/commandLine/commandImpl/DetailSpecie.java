package commandLine.commandImpl;

import basicGui.FormattingDisplay;
import commandLine.Command;
import commandLine.ReturnExec;
import commandLine.TypeReturn;
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
    public ReturnExec execute(String[] cmd) {
        try {
            Specie spec = this.play.getZoo().findSpecieByName(cmd[1]);
            this.success = true;
            return new ReturnExec(FormattingDisplay.formattingArrayList(spec.info(this.play.getOption())), TypeReturn.SUCCESS);
        } catch (UnknownNameException | EmptyNameException ex) {
            return new ReturnExec(ex.getMessage(), TypeReturn.ERROR);
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
