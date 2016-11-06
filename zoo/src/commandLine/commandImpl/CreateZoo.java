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
import utils.Constants;
import zoo.animal.specie.Specie;

/**
 *
 * @author doyenm
 */
public class CreateZoo extends AbstractCommand  {

    public CreateZoo(Play play) {
        super(play);
    }

    @Override
    public ReturnExec execute(String[] cmd) {
        try {
            int monthsPerEvaluation = 6;
            int horizon = 5;
            int age = 0;
            Map<String, Specie> species = InstanciateSpecies.instanciateSpecies("resources/species", 
                    super.getPlay().getOption());
          super.getPlay().getZoo().initiateZoo(cmd[2], Integer.parseInt(cmd[3]),
                    Integer.parseInt(cmd[4]), species, age, monthsPerEvaluation, horizon);
          super.setInitiate(true);
          super.setSuccess(true);
            return new ReturnExec(super.getPlay().getOption().getGeneralCmdBundle()
                    .getString("ZOO_CREATION_SUCESS"), TypeReturn.SUCCESS);
        } catch (JDOMException |  IOException ex) {
            return new ReturnExec(ex.getMessage(), TypeReturn.ERROR);
        } catch (java.lang.NumberFormatException ex){
            return new ReturnExec(super.getPlay().getOption().getGeneralCmdBundle().getString("NUMBER_FORMAT_EXCEPTION")
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
            if (Constants.ZOO.equalsIgnoreCase(cmd[0]) && Constants.CREATE.equalsIgnoreCase(cmd[1])) {
                return true;
            }
        }
        return false;
    }

}
