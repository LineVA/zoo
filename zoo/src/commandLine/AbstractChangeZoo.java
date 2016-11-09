package commandLine;

import launch.play.Play;
import lombok.Getter;

/**
 *
 * @author doyenm
 */
public abstract class AbstractChangeZoo extends AbstractCommand {

    @Getter
    private String[] previousCmd;

    public AbstractChangeZoo(Play play) {
        super(play);
    }

    public abstract ReturnExec executeChanging(String[] cmd);

    public ReturnExec confirmChangingZoo(String[] cmd) {
        super.setChangingZoo(false);
        if (cmd.length == 1) {
            if ("yes".equalsIgnoreCase(cmd[0]) || "y".equalsIgnoreCase(cmd[0])) {
                return this.executeChanging(cmd);
            }
        }
        return new ReturnExec("Vous n'avez pas changé de zoo", TypeReturn.ERROR);
    }

    public ReturnExec checkBeforeChangingZoo(String[] cmd) {
        this.previousCmd = cmd;
        super.setChangingZoo(true);
        super.setInitiate(true);
        return new ReturnExec("Vos actions non-sauvegardées vont être perdues. "
                + "Etes-vous sûr de vouloir quitter votre zoo actuel ?",
                TypeReturn.QUESTION);
    }

    @Override
    public ReturnExec execute(String[] cmd) {
        if (super.isInitiate()) {
            if (super.isChangingZoo()) {
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
