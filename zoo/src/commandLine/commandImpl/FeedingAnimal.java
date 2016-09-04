package commandLine.commandImpl;

import commandLine.Command;
import exception.IncorrectDataException;
import exception.name.EmptyNameException;
import exception.name.UnknownNameException;
import launch.play.Play;
import zoo.animal.Animal;

/**
 *
 * @author doyenm
 */
public class FeedingAnimal implements Command {

    Play play;
    // args[0] : the argument after '-d'
    // args[1] : the argument after '-fq'
    String[] args;

    public FeedingAnimal(Play play) {
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
            Animal animal = this.play.getZoo().findAnimalByName(cmd[1]);
            Boolean changeDiet = false;
            if (args[0] != null) {
              animal.changeDiet(args[0]);
            }
            if (args[1] != null) {
                animal.changeFoodQuantity(Double.parseDouble(args[1]));
            }
            return this.play.getOption().getGeneralCmdBundle()
                    .getString("ANIMALS_DIET") + " " + args[0];
        } catch (EmptyNameException | UnknownNameException |
                IncorrectDataException | NumberFormatException ex) {
            return ex.getMessage();
        }
    }

    private boolean firstCmd(String[] cmd) {
        if (cmd.length >= 4) {
            if (cmd[0].equals("animal")) {
                return true;
            }
        }
        return false;
    }
    
    private boolean hasArgumentDiet(String cmd){
        return cmd.equals("--diet") || cmd.equals("-d");
    }
    
     private boolean hasArgumentFoodQuantity(String cmd){
        return cmd.equals("--foodQuantity") || cmd.equals("-fq");
    }

    @Override
    public boolean canExecute(String[] cmd) {
        this.args = new String[]{null, null};
        if (firstCmd(cmd)) {
            if (cmd.length == 4 && this.hasArgumentDiet(cmd[2])) {
                args[0] = cmd[3];
                return true;
            } else if (cmd.length == 4 && this.hasArgumentFoodQuantity(cmd[2])) {
                args[1] = cmd[3];
                return true;
            } else if (cmd.length == 6 && this.hasArgumentDiet(cmd[2]) 
                    && this.hasArgumentFoodQuantity(cmd[4])) {
                args[0] = cmd[3];
                args[1] = cmd[5];
                return true;
            } else if (cmd.length == 6 && this.hasArgumentFoodQuantity(cmd[2]) 
                    && this.hasArgumentDiet(cmd[4]) ) {
                args[0] = cmd[5];
                args[1] = cmd[3];
                return true;
            }
        }
        return false;
    }
}
