package commandLine.commandImpl;

import commandLine.AbstractCommand;
import commandLine.ReturnExec;
import commandLine.TypeReturn;
import java.io.IOException;
import launch.InstanciateSpecies;
import launch.play.Play;
import org.jdom2.JDOMException;

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
        super.getPlay().getZoo().setSpecies(InstanciateSpecies.instanciateSpecies("resources/species", super.getPlay().getOption()));
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
            if (cmd[0].equalsIgnoreCase("option") && cmd[1].equalsIgnoreCase("-l")) {
                return true;
            }
        }
        return false;
    }
}
