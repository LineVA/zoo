package commandLine.commandImpl;

import basicGui.FormattingDisplay;
import commandLine.Command;
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
    public String execute(String[] cmd) {
        try {
            IPaddock pad = this.play.getZoo().findPaddockByName(cmd[1]);
            this.success= true;
            return (FormattingDisplay.formattingArrayList(pad.info()));
        } catch (UnknownNameException ex) {
            return "No paddock has this name.";
        } catch (EmptyNameException ex) {
            return "please, select a name.";
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
