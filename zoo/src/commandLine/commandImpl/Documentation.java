package commandLine.commandImpl;

import commandLine.AbstractCommand;
import commandLine.ReturnExec;
import commandLine.TypeReturn;
import documentation.DocumentationGeneration;
import java.io.IOException;
import java.net.URISyntaxException;
import launch.play.Play;
import zoo.animal.specie.Specie;

/**
 *
 * @author doyenm
 */
public class Documentation extends AbstractCommand {

    public Documentation(Play play) {
        super(play);
    }

    @Override
    public ReturnExec execute(String[] cmd) {
        try {
            Specie spec = super.getPlay().getZoo().findSpecieByName(cmd[2]);
            if ("wiki".equalsIgnoreCase(cmd[1]) || "wikipedia".equalsIgnoreCase(cmd[1])) {
                DocumentationGeneration.display(
                        spec.getDocumentation().getWikipediaAccordingLanguage(super.getPlay().getOption()));
            }
              if ("ad".equalsIgnoreCase(cmd[1]) || "animalDiversity".equalsIgnoreCase(cmd[1])) {
                DocumentationGeneration.display(
                        spec.getDocumentation().getAnimalDiversity());
            }
            super.setSuccess(true);
            return new ReturnExec("", TypeReturn.SUCCESS);
        } catch (IOException | URISyntaxException ex) {
            return new ReturnExec(ex.getMessage(), TypeReturn.ERROR);
        }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length == 3) {
            if ((cmd[0].equalsIgnoreCase("doc") || "documentation".equalsIgnoreCase(cmd[0])) 
                    && (("wiki".equalsIgnoreCase(cmd[1]) || "wikipedia".equalsIgnoreCase(cmd[1])) 
                    ||("animalDiversity".equalsIgnoreCase(cmd[1]) || "aD".equalsIgnoreCase(cmd[1])))) {
                return true;
            }
        }
        return false;
    }
}
