package commandLine.commandImpl;

import basicGui.FormattingDisplay;
import commandLine.AbstractCommand;
import commandLine.ReturnExec;
import commandLine.TypeReturn;
import exception.name.EmptyNameException;
import exception.name.UnknownNameException;
import java.util.ArrayList;
import java.util.Collections;
import launch.play.Play;
import utils.Constants;
import zoo.animal.specie.LightSpecie;
import zoo.paddock.IPaddock;

/**
 *
 * @author doyenm
 */
public class LsSpecie extends AbstractCommand {

    // args[0] : the argument after '--paddock'
    // args[1] : the argument after '--biome'
    // args[2] : the argument after '--ecoregion'
    // args[3] : the argument after '--diet'
    // args[4] : the argument after '--family'
    // args[5] : the argument after '--conservation'
    // args[6] : the argument after '--size'
    // args[7] : the argument after '--continent'
    // args[8 : the argument after '--breedingProgramme'
    String[] args;

    public LsSpecie(Play play) {
        super(play);
    }

    @Override
    public ReturnExec execute(String[] cmd) {
        LightSpecie light = new LightSpecie(null, Constants.UNDEFIND_ENUM, Constants.UNDEFIND_ENUM,
                Constants.UNDEFIND_ENUM, Constants.UNDEFIND_ENUM, Constants.UNDEFIND_ENUM,
                Constants.UNDEFIND_ENUM, Constants.UNDEFIND_ENUM, Constants.UNDEFIND_ENUM);
        IPaddock pad = null;
        try {
            if (args[0] != null) {
                pad = super.getPlay().getZoo().findPaddockByName(args[0]);
            }
            if (args[1] != null) {
                light.setBiome(Integer.parseInt(args[1]));
            }
            if (args[2] != null) {
                light.setEcoregion(Integer.parseInt(args[2]));
            }
            if (args[3] != null) {
                light.setDiet(Integer.parseInt(args[3]));
            }
            if (args[4] != null) {
                light.setFamily(Integer.parseInt(args[4]));
            }
            if (args[5] != null) {
                light.setConservation(Integer.parseInt(args[5]));
            }
            if (args[6] != null) {
                light.setSize(Integer.parseInt(args[6]));
            }
            if (args[7] != null) {
                light.setContinent(Integer.parseInt(args[7]));
            }
            if (args[8] != null) {
                light.setBreedingProgramme(Integer.parseInt(args[8]));
            }
            super.setSuccess(true);
        } catch (EmptyNameException | UnknownNameException ex) {
            return new ReturnExec(ex.getMessage(), TypeReturn.ERROR);
        }
        ArrayList<String> list = super.getPlay().getZoo().listSpecie(light, pad);
        Collections.sort(list);
        return new ReturnExec(FormattingDisplay.formattingArrayList(list), TypeReturn.SUCCESS);
    }

    public boolean firstCmd(String[] cmd) {
        if (cmd.length >= 2) {
            if (cmd[0].equalsIgnoreCase("specie") || cmd[0].equalsIgnoreCase("spec")) {
                if (cmd[1].equalsIgnoreCase("ls")) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkLength(String[] cmd) {
        return cmd.length >= 2 && cmd.length <= 16 && cmd.length % 2 == 0;
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
            args[1] = value;
        } else if (this.hasArgumentConservation(arg)) {
            args[5] = value;
        } else if (this.hasArgumentDiet(arg)) {
            args[3] = value;
        } else if (this.hasArgumentEcoregion(arg)) {
            args[2] = value;
        } else if (this.hasArgumentFamily(arg)) {
            args[4] = value;
        } else if (this.hasArgumentPaddock(arg)) {
            args[0] = value;
        } else if (this.hasArgumentSize(arg)) {
            args[6] = value;
        } else if (this.hasArgumentContinent(arg)) {
            args[7] = value;
        } else {
            return false;
        }
        return true;
    }

    @Override
    public boolean canExecute(String[] cmd) {
        this.args = new String[]{null, null, null, null, null, null, null, null};
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
