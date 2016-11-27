package commandLine.commandImpl;

import commandLine.AbstractCommand;
import commandLine.ReturnExec;
import commandLine.TypeReturn;
import java.io.IOException;
import java.util.Arrays;
import launch.InstanciateSpecies;
import launch.play.Play;
import org.jdom2.JDOMException;
import utils.Config;
import utils.Constants;

/**
 *
 * @author doyenm
 */
public class Options extends AbstractCommand {
  
    public Options(Play play) {
        super(play);
    }

    @Override
    public ReturnExec execute(String[] cmd) {
        try { 
        super.getPlay().getOption().setLanguage(cmd[2]);
        super.getPlay().updateOption();
        super.getPlay().getZoo().setSpecies(InstanciateSpecies.instanciateSpecies(Config.SPECIES_PATH, super.getPlay().getOption()));
        super.setSuccess(true);
        return new ReturnExec(super.getPlay().getOption().getGeneralCmdBundle()
                .getString("OPTION_CHANGE_SUCCESS"), TypeReturn.SUCCESS);
         } catch (JDOMException | IOException ex) {
            return new ReturnExec(ex.getMessage(), TypeReturn.ERROR);
        }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length == 3) {
            if (cmd[0].equalsIgnoreCase(Constants.OPTION) && 
                    Arrays.asList(Constants.LANGUAGE_ARG).contains(cmd[1])) {
                return true;
            }
        }
        return false;
    }
}
