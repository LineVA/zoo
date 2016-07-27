package commandLine.commandImpl;

import basicGui.FormattingDisplay;
import commandLine.Command;
import exception.name.EmptyNameException;
import exception.name.UnknownNameException;
import launch.Play;
import zoo.animal.specie.Specie;

/**
 *
 * @author doyenm
 */
public class DetailSpecie implements Command {

    Play play;

    public DetailSpecie(Play play) {
        this.play = play;
    }

    @Override
    public String execute(String[] cmd) {
        try {
            Specie spec = this.play.zoo.findSpeciebyName(cmd[1]);
            return (FormattingDisplay.formattingArrayList(spec.info()));
        } catch (UnknownNameException ex) {
            return "No paddock has this name.";
        } catch (EmptyNameException ex) {
            return "Please, select a name.";
        }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length == 2) {
            if (cmd[0].equals("spec") || cmd[0].equals("specie")) {
                return true;
            }
        }
        return false;
    }
}
