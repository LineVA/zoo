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

    String[] args = new String[]{null, null};

    public ZooCharacteristics(Play play) {
        this.play = play;
    }

    @Override
    public String execute(String[] cmd) {
        int speed = -1;
        int horizon = -1;
        String result = "";
        try {
            if (args[0] != null) {
                speed = Integer.parseInt(args[0]);
            }
            if (args[1] != null) {
                horizon = Integer.parseInt(args[1]);
            }
            try {
                if (args[0] != null) {
                    this.play.zoo.changeSpeed(speed);
                    result += this.play.option.getGeneralCmdBundle().getString("SPEED_CHANGE_SUCCESS") + cmd[2];
                }
                if (args[1] != null) {
                    this.play.zoo.changeHorizon(horizon);
                    result += this.play.option.getGeneralCmdBundle().getString("HORIZON_CHANGE_SUCCESS") + cmd[2];
                }
                this.success = success;
            } catch (IncorrectDataException ex) {
                result += ex.getMessage();
            }
        } catch (NumberFormatException ex) {
            return this.play.option.getGeneralCmdBundle().getString("SPEED_HORIZON_NUMBER");
        }
        return result;
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
                    args[0] = cmd[2];
                    return true;
                } else if ((cmd[1].equals("-h") || cmd[1].equals("--horizon"))) {
                    args[1] = cmd[2];
                    return true;
                }
            }
        }

        if (cmd.length == 5) {
            if (cmd[0].equals("zoo")) {
                if ((cmd[1].equals("-s") || cmd[1].equals("--speed")) && (cmd[3].equals("-h") || cmd[3].equals("--horizon"))) {
                    args[0] = cmd[2];
                    args[1] = cmd[4];
                    return true;
                } else if ((cmd[3].equals("-h") || cmd[3].equals("--horizon")) && (cmd[1].equals("-h") || cmd[1].equals("--horizon"))) {
                    args[0] = cmd[4];
                    args[1] = cmd[2];
                    return true;
                }
            }
        }
        return false;
    }

}
