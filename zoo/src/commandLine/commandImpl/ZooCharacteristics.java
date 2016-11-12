package commandLine.commandImpl;

import basicGui.FormattingDisplay;
import commandLine.AbstractCommand;
import commandLine.ReturnExec;
import commandLine.TypeReturn;
import exception.IncorrectDataException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import launch.play.Play;
import utils.Constants;

/**
 *
 * @author doyenm
 */
public class ZooCharacteristics extends AbstractCommand {

    String[] args = new String[]{null, null};

    public ZooCharacteristics(Play play) {
        super(play);
    }

    @Override
    public ReturnExec execute(String[] cmd) {
        int speed = -1;
        int horizon = -1;
        List<String> result = new ArrayList<>();
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
                    result.add(super.getPlay().getOption().getGeneralCmdBundle().getString("SPEED_CHANGE_SUCCESS") + cmd[2]);
                }
                if (args[1] != null) {
                    super.getPlay().getZoo().changeHorizon(horizon);
                    result.add(super.getPlay().getOption().getGeneralCmdBundle().getString("HORIZON_CHANGE_SUCCESS") + cmd[2]);
                }
                super.setSuccess(true);
            } catch (IncorrectDataException ex) {
                result.add(ex.getMessage());
            }
        } catch (NumberFormatException ex) {
            return new ReturnExec(
                    super.getPlay().getOption().getGeneralCmdBundle().getString("NUMBER_FORMAT_EXCEPTION"),
                    TypeReturn.SUCCESS);
        }
        return new ReturnExec(FormattingDisplay.formattingList(result), TypeReturn.SUCCESS);
    }

    @Override
    public boolean canExecute(String[] cmd
    ) {
        if (cmd.length == 3) {
            if (Constants.ZOO.equalsIgnoreCase(cmd[0])) {
                if (Arrays.asList(Constants.SPEED_ARG).contains(cmd[1])) {
                    args[0] = cmd[2];
                    return true;
                } else if (Arrays.asList(Constants.HORIZON_ARG).contains(cmd[1])) {
                    args[1] = cmd[2];
                    return true;
                }
                return false;
            }
        }

        if (cmd.length == 5) {
            if (Constants.ZOO.equalsIgnoreCase(cmd[0])) {
                if (Arrays.asList(Constants.SPEED_ARG).contains(cmd[1])
                        && Arrays.asList(Constants.HORIZON_ARG).contains(cmd[3])) {
                    args[0] = cmd[2];
                    args[1] = cmd[4];
                    return true;
                } else if (Arrays.asList(Constants.HORIZON_ARG).contains(cmd[3])
                        && Arrays.asList(Constants.HORIZON_ARG).contains(cmd[1])) {
                    args[0] = cmd[4];
                    args[1] = cmd[2];
                    return true;
                }
            }
        }
        return false;
    }

}
