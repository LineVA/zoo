package commandLine.commandImpl;

import commandLine.Command;
import documentation.DocumentationGeneration;
import exception.name.EmptyNameException;
import exception.name.UnknownNameException;
import java.io.IOException;
import java.net.URISyntaxException;
import launch.play.Play;
import zoo.animal.specie.Specie;

/**
 *
 * @author doyenm
 */
public class Documentation implements Command {

    Play play;

    public Documentation(Play play) {
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
            Specie spec = this.play.getZoo().findSpecieByName(cmd[2]);
            if ("-wiki".equals(cmd[1]) || "--wikipedia".equals(cmd[1]) || "wiki".equals(cmd[1])) {
                DocumentationGeneration.displayWikipedia(
                        spec.getDocumentation().getWikipediaAccordingLanguage(this.play.getOption()));
            }
            return "";
        } catch (IOException | URISyntaxException ex) {
            return ex.getMessage();
        }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length == 3) {
            if ((cmd[0].equals("doc") || "documentation".equals(cmd[0])) 
                    && ("-wiki".equals(cmd[1]) || "--wikipedia".equals(cmd[1]) || "wiki".equals(cmd[1]))) {
                return true;
            }
        }
        return false;
    }
}
