package commandLine.commandImpl;

import basicGui.FormattingDisplay;
import commandLine.Command;
import exception.name.EmptyNameException;
import exception.name.UnknownNameException;
import java.util.ArrayList;
import launch.play.Play;
import zoo.animal.Animal;
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
    
    boolean success = false;
    
     @Override
    public boolean isSuccess() {
        return this.success;
    }
    
    @Override
    public String execute(String[] cmd) {
        Specie spec = null;
        IPaddock pad = null;
        try {
            if (args[0] != null) {
                spec = this.play.getZoo().findSpecieByName(args[0]);
            }
            if (args[1] != null) {
                pad = this.play.getZoo().findPaddockByName(args[1]);
            }
            this.success = true;
            ArrayList<String> names = new ArrayList<>();
            for(Animal animal : this.play.getZoo().listAnimal(pad, spec, null, null)){
                names.add(animal.getName());
            }
            return FormattingDisplay.formattingArrayList(names);
        } catch (EmptyNameException | UnknownNameException ex) {
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

    private boolean hasArgumentSpecie(String cmd){
        return cmd.equals("--specie") || cmd.equals("-s");
    }
    
     private boolean hasArgumentPaddock(String cmd){
        return cmd.equals("--paddock") || cmd.equals("-p");
    }
    
    @Override
    public boolean canExecute(String[] cmd) {
        this.args = new String[] {null, null};
        if (firstCmd(cmd)) {
            if(cmd.length == 2){
                return true;
            } else if (cmd.length == 4 && this.hasArgumentSpecie(cmd[2])) {
                args[0] = cmd[3];
                return true;
            } else if (cmd.length == 4 && this.hasArgumentPaddock(cmd[2])) {
                args[1] = cmd[3];
                return true;
            } else if (cmd.length == 6 && this.hasArgumentSpecie(cmd[2]) && this.hasArgumentPaddock(cmd[4])) {
                args[0] = cmd[3];
                args[1] = cmd[5];
                return true;
            } else if (cmd.length == 6 && this.hasArgumentSpecie(cmd[4]) && this.hasArgumentPaddock(cmd[2])) {
                args[0] = cmd[5];
                args[1] = cmd[3];
                return true;
            }
        }
        return false;
    }

}
