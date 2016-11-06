package commandLine.commandImpl;

import basicGui.FormattingDisplay;
import commandLine.AbstractCommand;
import commandLine.ReturnExec;
import commandLine.TypeReturn;
import exception.IncorrectDimensionsException;
import java.util.List;
import launch.play.Play;
import utils.Constants;
import zoo.paddock.PaddockCoordinates;

/**
 *
 * @author doyenm
 */
public class MapZoo extends AbstractCommand{

    public MapZoo(Play play) {
        super(play);
    }

    @Override
    public ReturnExec execute(String[] cmd) {
        try {
            List<PaddockCoordinates> map = super.getPlay().getZoo().map();
            super.setSuccess(true);
            return new ReturnExec(FormattingDisplay.zooMap(map), TypeReturn.SUCCESS);
        } catch (IncorrectDimensionsException ex) {
            return new ReturnExec(ex.getMessage(), TypeReturn.ERROR);
        }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length == 2) {
            if (Constants.ZOO.equalsIgnoreCase(cmd[0]) && Constants.MAP.equalsIgnoreCase(cmd[1])) {
                return true;
            }
        }
        return false;
    }

}
