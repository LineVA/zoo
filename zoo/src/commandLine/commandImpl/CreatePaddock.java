package commandLine.commandImpl;

import commandLine.AbstractCommand;
import commandLine.Command;
import commandLine.ReturnExec;
import commandLine.TypeReturn;
import exception.IncorrectDimensionsException;
import exception.name.NameException;
import launch.play.Play;

/**
 *
 * @author doyenm
 */
public class CreatePaddock extends AbstractCommand implements Command {

    Play play;

    public CreatePaddock(Play play) {
        this.play = play;
    }

    boolean success = false;

    @Override
    public boolean isSuccess() {
        return this.success;
    }

    @Override
    public boolean hasInitiateAZoo() {
        return false;
    }

    @Override
    public ReturnExec execute(String[] cmd) {
        try {
            this.play.getZoo().addPaddock(cmd[2], Integer.parseInt(cmd[3]), Integer.parseInt(cmd[4]),
                    Integer.parseInt(cmd[5]), Integer.parseInt(cmd[6]));
            this.success = true;
            return new ReturnExec(this.play.getOption().getGeneralCmdBundle()
                    .getString("PADDOCK_CREATION_SUCCESS"), TypeReturn.SUCCESS);
        } catch (NameException | IncorrectDimensionsException ex) {
                return new ReturnExec(ex.getMessage(), TypeReturn.ERROR);
        } catch (java.lang.NumberFormatException ex){
            return new ReturnExec(this.play.getOption().getGeneralCmdBundle().getString("NUMBER_FORMAT_EXCEPTION")
            , TypeReturn.ERROR);
        }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length == 7) {
            if (cmd[0].equals("pad") || cmd[0].equals("paddock")) {
                if (cmd[1].equals("create")) {
                    return true;
                }
            }
        }
        return false;
    }

}
