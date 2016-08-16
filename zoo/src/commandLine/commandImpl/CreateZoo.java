package commandLine.commandImpl;

import commandLine.Command;
import exception.IncorrectDimensionsException;
import exception.name.EmptyNameException;
import java.io.IOException;
import java.util.Map;
import launch.InstanciateSpecies;
import launch.play.Play;
import org.jdom2.JDOMException;
import zoo.animal.specie.Specie;

/**
 *
 * @author doyenm
 */
public class CreateZoo implements Command {

    Play play;
    boolean previousHasBeenSuccessfull = false;

    @Override
    public boolean hasInitiateAZoo() {
        return this.previousHasBeenSuccessfull;
    }

    public CreateZoo(Play play) {
        this.play = play;
    }

    @Override
    public String execute(String[] cmd) {
        try {
            int monthsPerEvaluation = 6;
            int horizon = 5;
            int age = 0;
            Map<String, Specie> species = InstanciateSpecies.instanciateSpecies("resources/species");
            this.play.getZoo().initiateZoo(cmd[2], Integer.parseInt(cmd[3]),
                    Integer.parseInt(cmd[4]), species, age, monthsPerEvaluation, horizon);
            this.previousHasBeenSuccessfull = true;
            return this.play.getOption().getGeneralCmdBundle()
                    .getString("ZOO_CREATION_SUCESS");
        } catch (EmptyNameException ex){
            return ex.getMessage();
        } catch (JDOMException | IOException ex) {
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
