package commandLine.commandImpl;

import basicGui.FormattingDisplay;
import commandLine.Command;
import exception.IncorrectDataException;
import launch.play.Play;
import zoo.animal.feeding.Diet;

/**
 *
 * @author doyenm
 */
public class ZooCharacteristics implements Command {

    Play play;

    public ZooCharacteristics(Play play) {
        this.play = play;
    }

    @Override
    public String execute(String[] cmd) {
        try {
            this.play.getZoo().changeSpeed(Integer.parseInt(cmd[2]));
            this.success = success;
            return this.play.option.getGeneralCmdBundle().getString("SPEED_CHANGE_SUCCESS") + cmd[2];
        } catch (IncorrectDataException ex)  {
            return ex.getMessage();
        } catch(NumberFormatException ex) {
            return this.play.option.getGeneralCmdBundle().getString("SPEED_NUMBER");
        }
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
    public boolean canExecute(String[] cmd
    ) {
        if (cmd.length == 3) {
            if (cmd[0].equals("zoo")) {
                if ((cmd[1].equals("-s") || cmd[1].equals("--speed"))) {
                    return true;
                }
            }
        }
        return false;
    }

}
