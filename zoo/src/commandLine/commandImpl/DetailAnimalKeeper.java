package commandLine.commandImpl;

import basicGui.FormattingDisplay;
import commandLine.AbstractCommand;
import commandLine.ReturnExec;
import commandLine.TypeReturn;
import exception.name.EmptyNameException;
import exception.name.UnknownNameException;
import launch.play.Play;
import zoo.animalKeeper.AnimalKeeper;

/**
 *
 * @author doyenm
 */
public class DetailAnimalKeeper extends AbstractCommand {

    public DetailAnimalKeeper(Play play) {
        super(play);
    }

    @Override
    public ReturnExec execute(String[] cmd) {
        try {
            AnimalKeeper keeper = super.getPlay().getZoo().findAnimalKeeperByName(cmd[1]);
            super.setSuccess(true);
            return new ReturnExec(FormattingDisplay.formattingArrayList(keeper.info()), TypeReturn.SUCCESS);
        } catch (UnknownNameException | EmptyNameException ex) {
            return new ReturnExec(ex.getMessage(), TypeReturn.ERROR);
        }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length == 2) {
            if (cmd[0].equals("animalKeeper") || "aK".equals(cmd[0]) || "ak".equals(cmd[0])) {
                return true;
            }
        }
        return false;
    }

}