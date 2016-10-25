package commandLine.commandImpl;

import basicGui.FormattingDisplay;
import commandLine.AbstractCommand;
import commandLine.Command;
import commandLine.ReturnExec;
import commandLine.TypeReturn;
import exception.name.EmptyNameException;
import exception.name.UnknownNameException;
import java.util.ArrayList;
import java.util.Collections;
import launch.play.Play;
import utils.Constants;
import zoo.animal.Animal;
import zoo.animal.feeding.Diet;
import zoo.animal.reproduction.Sex;
import zoo.animal.specie.LightSpecie;
import zoo.paddock.IPaddock;
import zoo.paddock.biome.Biome;

/**
 *
 * @author doyenm
 */
public class LsAnimal extends AbstractCommand  {

    // args[0] : the argument after '--specie'
    // args[1] : the argument after '--paddock'
    // args[2] : the argument after '--ecoregion'
    // args[3] : the argument after '--diet'
    // args[4] : the argument after '--sex'
    // args[5] : the argument after '--family'
    // args[6] : the argument after '--conservation'
    // args[7] : the argument after '--biome'
    // args[8] : the argument after '--size'
    // args[9] : the argument after '--continent'

    String[] args;

    public LsAnimal(Play play) {
        super(play);
    }

    @Override
    public ReturnExec execute(String[] cmd) {
        IPaddock pad = null;
        Diet diet = null;
        Biome biome = null;
        Sex sex = null;
        LightSpecie spec = new LightSpecie(null, Constants.UNDEFIND_ENUM, Constants.UNDEFIND_ENUM,
                Constants.UNDEFIND_ENUM, Constants.UNDEFIND_ENUM,
                Constants.UNDEFIND_ENUM, Constants.UNDEFIND_ENUM, Constants.UNDEFIND_ENUM);
        try {
            if (args[0] != null) {
                spec.setNames(super.getPlay().getZoo().findSpecieByName(args[0]).getNames());
            }
            if (args[1] != null) {
                pad = super.getPlay().getZoo().findPaddockByName(args[1]);
            }
            if (args[2] != null) {
                spec.setEcoregion(Integer.parseInt(args[2]));
            }
            if (args[3] != null) {
                diet = Diet.NONE.findDietById(Integer.parseInt(args[3]));
            }
            if (args[4] != null) {
                sex = Sex.UNKNOWN.findById(Integer.parseInt(args[4]));
            }
            if (args[5] != null) {
                spec.setFamily(Integer.parseInt(args[5]));
            }
            if (args[6] != null) {
                spec.setConservation(Integer.parseInt(args[6]));
            }
            if (args[7] != null) {
                biome = Biome.NONE.findById(Integer.parseInt(args[7]));
            }
            if (args[8] != null) {
                spec.setSize(Integer.parseInt(args[8]));
            }
            if (args[9] != null) {
                spec.setContinent(Integer.parseInt(args[9]));
            }
            super.setSuccess(true);
            ArrayList<String> names = new ArrayList<>();
            for (Animal animal : super.getPlay().getZoo().listAnimal(pad, spec, sex, diet, biome)) {
                names.add(animal.getName());
            }
            Collections.sort(names);
            return new ReturnExec(FormattingDisplay.formattingArrayList(names), TypeReturn.SUCCESS);
        } catch (EmptyNameException | UnknownNameException ex) {
            return new ReturnExec(ex.getMessage(), TypeReturn.ERROR);
        } catch (NumberFormatException ex) {
            return new ReturnExec("INTEGER ERROR", TypeReturn.ERROR);
        }
    }

    private boolean firstCmd(String[] cmd) {
        if (cmd.length >= 2) {
            if (cmd[0].equalsIgnoreCase("animal")) {
                if (cmd[1].equalsIgnoreCase("ls")) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkLength(String[] cmd) {
        return cmd.length >= 2 && cmd.length <= 20 && cmd.length % 2 == 0;
    }

    private boolean hasArgumentSpecie(String cmd) {
        return cmd.equalsIgnoreCase("--specie") || cmd.equalsIgnoreCase("-s");
    }

    private boolean hasArgumentPaddock(String cmd) {
        return cmd.equalsIgnoreCase("--paddock") || cmd.equalsIgnoreCase("-p");
    }

    private boolean hasArgumentEcoregion(String cmd) {
        return cmd.equalsIgnoreCase("--ecoregion") || cmd.equalsIgnoreCase("-e");
    }

    private boolean hasArgumentDiet(String cmd) {
        return cmd.equalsIgnoreCase("--diet") || cmd.equalsIgnoreCase("-d");
    }

    private boolean hasArgumentSex(String cmd) {
        return cmd.equalsIgnoreCase("--sex") || cmd.equalsIgnoreCase("-sx");
    }

    private boolean hasArgumentFamily(String cmd) {
        return cmd.equalsIgnoreCase("--family") || cmd.equalsIgnoreCase("-f");
    }

    private boolean hasArgumentConservation(String cmd) {
        return cmd.equalsIgnoreCase("--conservation") || cmd.equalsIgnoreCase("-cs");
    }

    private boolean hasArgumentBiome(String cmd) {
        return cmd.equalsIgnoreCase("--biome") || cmd.equalsIgnoreCase("-b");
    }

    private boolean hasArgumentSize(String cmd) {
        return cmd.equalsIgnoreCase("--size") || cmd.equalsIgnoreCase("-sz");
    }

    private boolean hasArgumentContinent(String cmd) {
        return cmd.equalsIgnoreCase("--continent") || cmd.equalsIgnoreCase("-ct");
    }

    private boolean saveArgument(String arg, String value) {
        if (this.hasArgumentBiome(arg)) {
            args[7] = value;
        } else if (this.hasArgumentConservation(arg)) {
            args[6] = value;
        } else if (this.hasArgumentDiet(arg)) {
            args[3] = value;
        } else if (this.hasArgumentEcoregion(arg)) {
            args[2] = value;
        } else if (this.hasArgumentFamily(arg)) {
            args[5] = value;
        } else if (this.hasArgumentPaddock(arg)) {
            args[1] = value;
        } else if (this.hasArgumentSex(arg)) {
            args[4] = value;
        } else if (this.hasArgumentSize(arg)) {
            args[8] = value;
        } else if (this.hasArgumentSpecie(arg)) {
            args[0] = value;
        } else if (this.hasArgumentContinent(arg)) {
            args[9] = value;
        } else {
            return false;
        }
        return true;
    }

    @Override
    public boolean canExecute(String[] cmd) {
        this.args = new String[]{null, null, null, null, null, null, null, null, null, null};
        if (firstCmd(cmd) && checkLength(cmd)) {
            int i = 2;
            while (i <= cmd.length - 2) {
                if (!saveArgument(cmd[i], cmd[i + 1])) {
                    return false;
                }
                i += 2;
            }
            return true;
        }
        return false;
    }

}
