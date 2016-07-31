package commandLine.commandImpl;

import basicGui.FormattingDisplay;
import commandLine.Command;
import exception.IncorrectDimensionsException;
import java.util.ArrayList;
import launch.play.Play;
import zoo.paddock.PaddockCoordinates;

/**
 *
 * @author doyenm
 */
public class MapZoo implements Command {

    Play play;

    public MapZoo(Play play) {
        this.play = play;
    }
    
      @Override
    public boolean hasInitiateAZoo() {
        return false;
    }

    @Override
    public String execute(String[] cmd) {
        try {
            ArrayList<PaddockCoordinates> map = this.play.getZoo().map();
            return FormattingDisplay.zooMap(map);
        } catch (IncorrectDimensionsException ex) {
            return "Fail !";
        }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length == 2) {
            if (cmd[0].equals("zoo") && cmd[1].equals("map")) {
                return true;
            }
        }
        return false;
    }

}
