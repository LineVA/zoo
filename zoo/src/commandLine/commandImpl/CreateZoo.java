package commandLine.commandImpl;

import commandLine.Command;
import exception.IncorrectDimensionsException;
import exception.name.EmptyNameException;
import java.io.IOException;
import java.util.HashMap;
import main.InstanciateSpecies;
import main.Play;
import org.jdom2.JDOMException;
import zoo.animal.specie.Specie;

/**
 *
 * @author doyenm
 */
public class CreateZoo implements Command {

    Play play;

    public CreateZoo(Play play) {
        this.play = play;
    }

    @Override
    public String execute(String[] cmd) {
        try {
            HashMap<String, Specie> species = InstanciateSpecies.instanciateSpecies("resources/species");
            this.play.zoo.initiateZoo(cmd[2], Integer.parseInt(cmd[3]), 
                    Integer.parseInt(cmd[4]), species, 0, 6);
            return "Your zoo has been sucessfully created";
        } catch (IncorrectDimensionsException | EmptyNameException ex) {
            return ex.getMessage();
        } catch (IOException ex) {
            return ex.getMessage();
        } catch (JDOMException ex) {
            return ex.getMessage();
        }
    }

    /**
     * zoo create <name> <width> <height>
     *
     * @param cmd
     * @return
     */
    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length == 5) {
            if (cmd[0].equals("zoo") && cmd[1].equals("create")) {
                return true;
            }
        }
        return false;
    }

}
