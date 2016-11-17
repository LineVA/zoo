package commandLine;

import java.util.Arrays;
import launch.play.Play;
import lombok.Getter;
import utils.Constants;

/**
 *
 * @author doyenm
 */
public abstract class AbstractChangeZooCommand extends AbstractCommand {

    @Getter
    private String[] previousCmd;

    public AbstractChangeZooCommand(Play play) {
        super(play);
    }

    public abstract ReturnExec executeChanging(String[] cmd);

    public ReturnExec confirmChangingZoo(String[] cmd) {
        super.setChangingZoo(false);
        if (cmd.length == 1) {
            if (Arrays.asList(Constants.YES_OR_Y).contains(cmd[0])) {
                return this.executeChanging(cmd);
            }
        }
        return new ReturnExec(
                this.getPlay().getOption().getGeneralCmdBundle().getString("NOT_CHANGED_ZOO"),
                TypeReturn.ERROR);
    }

    public ReturnExec checkBeforeChangingZoo(String[] cmd) {
        this.previousCmd = cmd;
        super.setChangingZoo(true);
        super.setInitiate(true);
        return new ReturnExec(
                this.getPlay().getOption().getGeneralCmdBundle().getString("CONFIRM_CHANGE_ZOO"),
                TypeReturn.QUESTION);
    }

    @Override
    public ReturnExec execute(String[] cmd) {
        if (super.isInitiate()) {
            if (this.isChangingZoo()) {
                return executeChanging(cmd);
            } else {
                return this.checkBeforeChangingZoo(cmd);
            }
        } else {
            return executeChanging(cmd);
        }
    }

    @Override
    public abstract boolean canExecute(String[] cmd);

}
