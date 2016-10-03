package commandLine.commandImpl;

import basicGui.FormattingDisplay;
import commandLine.Command;
import commandLine.ReturnExec;
import commandLine.TypeReturn;
import exception.name.EmptyNameException;
import exception.name.UnknownNameException;
import launch.play.Play;
import zoo.paddock.IPaddock;

/**
 *
 * @author doyenm
 */
public class DetailPad implements Command {

    Play play;

    public DetailPad(Play play) {
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
            IPaddock pad = this.play.getZoo().findPaddockByName(cmd[1]);
            this.success= true;
            return new ReturnExec(FormattingDisplay.formattingArrayList(pad.info()), TypeReturn.SUCCESS);
        } catch (UnknownNameException  | EmptyNameException ex) {
            return new ReturnExec(ex.getMessage(), TypeReturn.ERROR);
        } 
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length == 2) {
            if (cmd[0].equals("pad") || cmd[0].equals("paddock")) {
                return true;
            }
        }
        return false;
    }

}
