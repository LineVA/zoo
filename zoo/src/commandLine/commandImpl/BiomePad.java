package commandLine.commandImpl;

import commandLine.Command;
import exception.name.EmptyNameException;
import exception.name.UnknownNameException;
import main.Play;
import zoo.paddock.IPaddock;

/**
 *
 * @author doyenm
 */
public class BiomePad implements Command {

    Play play;

    public BiomePad(Play play) {
        this.play = play;
    }

    @Override
    public String execute(String[] cmd) {
        try {
            IPaddock pad = this.play.zoo.findPaddockByName(cmd[1]);
            pad.setBiome(cmd[3]);
            return "The biome of the paddock '" + cmd[1] + "' is now '" + cmd[3] + "'.";
        } catch (UnknownNameException ex) {
            return ex.getMessage();
        } catch (EmptyNameException ex) {
            return ex.getMessage();
        }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length == 4) {
            if (cmd[0].equals("pad") || cmd[0].equals("paddock")) {
                if (cmd[2].equals("biome")) {
                    return true;
                }
            }
        }
        return false;
    }

}
