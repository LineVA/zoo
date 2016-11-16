package commandLine.commandImpl;

import basicGui.FormattingDisplay;
import commandLine.AbstractCommand;
import commandLine.ReturnExec;
import commandLine.TypeReturn;
import exception.name.EmptyNameException;
import exception.name.UnknownNameException;
import java.util.Arrays;
import launch.play.Play;
import utils.Constants;
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
            return new ReturnExec(FormattingDisplay.formattingListOfLists(keeper.info()), TypeReturn.SUCCESS);
//            return new ReturnExec(FormattingDisplay.formattingList(keeper.info()), TypeReturn.SUCCESS);
        } catch (UnknownNameException | EmptyNameException ex) {
            return new ReturnExec(ex.getMessage(), TypeReturn.ERROR);
        }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length == 2) {
            if (Arrays.asList(Constants.AK_OR_ANIMALKEEPER).contains(cmd[0])) {
                return true;
            }
        }
        return false;
    }

}
