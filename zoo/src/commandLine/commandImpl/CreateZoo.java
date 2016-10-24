package commandLine.commandImpl;

import commandLine.AbstractCommand;
import commandLine.Command;
import commandLine.ReturnExec;
import commandLine.TypeReturn;
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
public class CreateZoo extends AbstractCommand  {

    Play play;
    boolean previousHasBeenSuccessfull = false;

    @Override
    public boolean hasInitiateAZoo() {
        return this.previousHasBeenSuccessfull;
    }

    boolean success = false;

    @Override
    public boolean isSuccess() {
        return this.success;
    }

    public CreateZoo(Play play) {
        super();
        this.play = play;
    }

    @Override
    public ReturnExec execute(String[] cmd) {
        try {
            int monthsPerEvaluation = 6;
            int horizon = 5;
            int age = 0;
            Map<String, Specie> species = InstanciateSpecies.instanciateSpecies("resources/species", this.play.getOption());
            this.play.getZoo().initiateZoo(cmd[2], Integer.parseInt(cmd[3]),
                    Integer.parseInt(cmd[4]), species, age, monthsPerEvaluation, horizon);
            this.previousHasBeenSuccessfull = true;
            this.success = true;
            return new ReturnExec(this.play.getOption().getGeneralCmdBundle()
                    .getString("ZOO_CREATION_SUCESS"), TypeReturn.SUCCESS);
        } catch (JDOMException |  IOException ex) {
            return new ReturnExec(ex.getMessage(), TypeReturn.ERROR);
        } catch (java.lang.NumberFormatException ex){
            return new ReturnExec(this.play.getOption().getGeneralCmdBundle().getString("NUMBER_FORMAT_EXCEPTION")
            , TypeReturn.ERROR);
        }
    }

    /**
     * zoo create 
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
