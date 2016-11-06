package commandLine.commandImpl;

import commandLine.AbstractCommand;
import commandLine.Command;
import commandLine.ReturnExec;
import commandLine.TypeReturn;
import exception.IncorrectLoadException;
import exception.name.EmptyNameException;
import exception.name.NameException;
import exception.name.UnknownNameException;
import launch.play.Play;
import utils.Constants;
import zoo.animal.Animal;
import zoo.animal.AnimalImpl;
import zoo.animal.reproduction.Sex;
import zoo.animal.specie.Specie;
import zoo.paddock.IPaddock;

/**
 * @author doyenm
 */
public class CreateAnimal extends AbstractCommand {

    public CreateAnimal(Play play) {
        super(play);
    }

    @Override
    public ReturnExec execute(String[] cmd) {
        try {
           super.getPlay().getZoo().findAnimalByName(cmd[2]);
            return new ReturnExec(super.getPlay().getOption().getGeneralCmdBundle()
                    .getString("ALREADY_EXISTING_ANIMAL_NAME"), TypeReturn.SUCCESS);
        } catch (UnknownNameException ex1) {
            try {
                IPaddock pad = super.getPlay().getZoo().findPaddockByName(cmd[3]);
                Specie specie = super.getPlay().getZoo().findSpecieByName(cmd[4]);
                Sex sex;
                try {
                    sex = Sex.UNKNOWN.findById(Integer.parseInt(cmd[5]));
                } catch (java.lang.NumberFormatException ex2) {
                    sex = Sex.UNKNOWN.findByNameAccordingToLanguage(cmd[5]);
                }
                Animal animal = new AnimalImpl(specie, cmd[2], pad,
                        sex, super.getPlay().getOption());
                pad.addAnimal(animal);
                super.setSuccess(true);
                return new ReturnExec(super.getPlay().getOption().getGeneralCmdBundle()
                        .getString("ANIMAL_CREATION_SUCCESS"), TypeReturn.SUCCESS);
            } catch (NameException | IncorrectLoadException ex) {
                return new ReturnExec(ex.getMessage(), TypeReturn.ERROR);
            }
        } catch (EmptyNameException ex) {
            return new ReturnExec(ex.getMessage(), TypeReturn.ERROR);
        }
    }

    @Override
    public boolean canExecute(String[] cmd
    ) {
        if (cmd.length == 6) {
            if (cmd[0].equalsIgnoreCase("animal") && Constants.CREATE.equalsIgnoreCase(cmd[1])) {
                return true;
            }
        }
        return false;
    }

}
