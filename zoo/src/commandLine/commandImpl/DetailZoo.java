package commandLine.commandImpl;

import basicGui.FormattingDisplay;
import commandLine.Command;
import exception.name.EmptyNameException;
import exception.name.UnknownNameException;
import main.Play;
import zoo.animal.specie.Specie;

/**
 *
 * @author doyenm
 */
public class DetailZoo implements Command {

    Play play;

    public DetailZoo(Play play) {
        this.play = play;
    }

    @Override
    public String execute(String[] cmd) {
        return (FormattingDisplay.formattingArrayList(this.play.zoo.info()));
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length == 1) {
            if (cmd[0].equals("zoo")) {
                return true;
            }
        }
        return false;
    }
}
