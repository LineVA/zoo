package commandLine.commandImpl;

import basicGui.FormattingDisplay;
import commandLine.Command;
import exception.name.EmptyNameException;
import exception.name.UnknownNameException;
import java.util.logging.Level;
import java.util.logging.Logger;
import launch.play.Play;
import zoo.animal.specie.Specie;
import zoo.paddock.IPaddock;

/**
 *
 * @author doyenm
 */
public class LsAnimal implements Command {

    Play play;
    // args[0] : the argument after '-s'
    // args[1] : the argument after '-p'
    String[] args; 

    public LsAnimal(Play play) {
        this.play = play;
    }

      @Override
    public boolean hasInitiateAZoo() {
        return false;
    }
    
    @Override
    public String execute(String[] cmd) {
        Specie spec = null;
        IPaddock pad = null;
        try {
            if (args[0] != null) {
                spec = this.play.getZoo().findSpeciebyName(args[0]);
            }
            if (args[1] != null) {
                pad = this.play.getZoo().findPaddockByName(args[1]);
            }
            return FormattingDisplay.formattingArrayList(this.play.getZoo().listAnimal(spec, pad));
        } catch (EmptyNameException ex) {
            return ex.getMessage();
        } catch (UnknownNameException ex) {
            return ex.getMessage();
        }
    }

    private boolean firstCmd(String[] cmd) {
        if (cmd.length >= 2) {
            if (cmd[0].equals("animal")) {
                if (cmd[1].equals("ls")) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean canExecute(String[] cmd) {
        this.args = new String[] {null, null};
        if (firstCmd(cmd)) {
            if(cmd.length == 2){
                return true;
            } else if (cmd.length == 4 && cmd[2].equals("-s")) {
                args[0] = cmd[3];
                return true;
            } else if (cmd.length == 4 && cmd[2].equals("-p")) {
                args[1] = cmd[3];
                return true;
            } else if (cmd.length == 6 && cmd[2].equals("-s") && cmd[4].equals("-p")) {
                args[0] = cmd[3];
                args[1] = cmd[5];
                return true;
            } else if (cmd.length == 6 && cmd[4].equals("-s") && cmd[2].equals("-p")) {
                args[0] = cmd[5];
                args[1] = cmd[3];
                return true;
            }
        }
        return false;
    }

}
