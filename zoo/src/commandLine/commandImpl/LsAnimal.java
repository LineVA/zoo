package commandLine.commandImpl;

import basicGui.FormattingDisplay;
import commandLine.Command;
import main.Play;

/**
 *
 * @author doyenm
 */
public class LsAnimal implements Command {

    Play play;

    public LsAnimal(Play play) {
        this.play = play;
    }

    @Override
    public String execute(String[] cmd) {
        return FormattingDisplay.formattingArrayList(this.play.zoo.listAnimal());
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length == 2) {
            if (cmd[0].equals("animal")) {
                if (cmd[1].equals("ls")) {
                    return true;
                }
            }
        }
        return false;
    }

}
