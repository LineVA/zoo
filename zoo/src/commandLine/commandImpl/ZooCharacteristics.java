package commandLine.commandImpl;

import commandLine.AbstractCommand;
import commandLine.ReturnExec;
import commandLine.TypeReturn;
import exception.IncorrectDataException;
import launch.play.Play;

/**
 *
 * @author doyenm
 */
public class ZooCharacteristics extends AbstractCommand  {

    String[] args = new String[]{null, null};

    public ZooCharacteristics(Play play) {
        super(play);
    }

    @Override
    public ReturnExec execute(String[] cmd) {
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
                    super.getPlay().getZoo().changeSpeed(speed);
                    result += super.getPlay().getOption().getGeneralCmdBundle().getString("SPEED_CHANGE_SUCCESS") + cmd[2];
                }
                if (args[1] != null) {
                    super.getPlay().getZoo().changeHorizon(horizon);
                    result += super.getPlay().getOption().getGeneralCmdBundle().getString("HORIZON_CHANGE_SUCCESS") + cmd[2];
                }
                super.setSuccess(true);
            } catch (IncorrectDataException ex) {
                result += ex.getMessage();
            }
        } catch (NumberFormatException ex) {
            return new ReturnExec(
                    super.getPlay().getOption().getGeneralCmdBundle().getString("NUMBER_FORMAT_EXCEPTION"),
                    TypeReturn.SUCCESS);
        }
        return new ReturnExec(result, TypeReturn.SUCCESS);
    }

    @Override
    public boolean canExecute(String[] cmd
    ) {
        if (cmd.length == 3) {
            if (cmd[0].equalsIgnoreCase("zoo")) {
                switch (cmd[1]) {
                    case "-s":
                    case "--speed":
                        args[0] = cmd[2];
                        return true;
                    case "-h":
                    case "--horizon":
                        args[1] = cmd[2];
                        return true;
                }
            }
        }

        if (cmd.length == 5) {
            if (cmd[0].equalsIgnoreCase("zoo")) {
                if ((cmd[1].equalsIgnoreCase("-s") || cmd[1].equalsIgnoreCase("--speed")) && (cmd[3].equalsIgnoreCase("-h") || cmd[3].equalsIgnoreCase("--horizon"))) {
                    args[0] = cmd[2];
                    args[1] = cmd[4];
                    return true;
                } else if ((cmd[3].equalsIgnoreCase("-h") || cmd[3].equalsIgnoreCase("--horizon")) && (cmd[1].equalsIgnoreCase("-h") || cmd[1].equalsIgnoreCase("--horizon"))) {
                    args[0] = cmd[4];
                    args[1] = cmd[2];
                    return true;
                }
            }
        }
        return false;
    }

}
