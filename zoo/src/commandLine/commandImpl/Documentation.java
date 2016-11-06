package commandLine.commandImpl;

import commandLine.AbstractCommand;
import commandLine.ReturnExec;
import commandLine.TypeReturn;
import documentation.DocumentationGeneration;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import launch.play.Play;
import utils.Constants;
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
            if (Arrays.asList(Constants.WIKI_OR_WIKIPEDIA).contains(cmd[1])) {
                DocumentationGeneration.display(
                        spec.getDocumentation().getWikipediaAccordingToLanguage(super.getPlay().getOption()));
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
            if (Arrays.asList(Constants.DOC_OR_DOCUMENTATION).contains(cmd[0])
                    && Arrays.asList(Constants.WIKI_OR_WIKIPEDIA).contains(cmd[1])) {
                return true;
            }
        }
        return false;
    }
}
