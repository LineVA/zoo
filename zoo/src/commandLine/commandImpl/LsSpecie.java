package commandLine.commandImpl;

import basicGui.FormattingDisplay;
import commandLine.Command;
import exception.name.EmptyNameException;
import exception.name.UnknownNameException;
import java.util.ArrayList;
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
public class LsSpecie implements Command {

    Play play;
    // args[0] : the argument after '--paddock'
    // args[1] : the argument after '--biome'
    // args[2] : the argument after '--ecoregion'
    // args[3] : the argument after '--diet'
    // args[4] : the argument after '--family'
    // args[5] : the argument after '--conservation'
    // args[6] : the argument after '--size'
    // args[7] : the argument after '--continent'
    String[] args;

    public LsSpecie(Play play) {
        this.play = play;
    }

    @Override
    public boolean hasInitiateAZoo() {
        return false;
    }

    boolean success = false;

    @Override
    public boolean isSuccess() {
        return this.success;
    }

    @Override
    public String execute(String[] cmd) {
        LightSpecie light = new LightSpecie(null, Constants.UNDEFIND_ENUM, Constants.UNDEFIND_ENUM,
                Constants.UNDEFIND_ENUM, Constants.UNDEFIND_ENUM, Constants.UNDEFIND_ENUM,
                Constants.UNDEFIND_ENUM, Constants.UNDEFIND_ENUM);
        IPaddock pad = null;
        try {
            if (args[0] != null) {
                pad = this.play.getZoo().findPaddockByName(args[0]);
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
            this.success = true;
        } catch (EmptyNameException | UnknownNameException ex) {
            return ex.getMessage();
        }
        return FormattingDisplay.formattingArrayList(this.play.getZoo().listSpecie(light, pad));
    }

    public boolean firstCmd(String[] cmd) {
        if (cmd.length >= 2) {
            if (cmd[0].equals("specie") || cmd[0].equals("spec")) {
                if (cmd[1].equals("ls")) {
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
        return cmd.equals("--paddock") || cmd.equals("-p");
    }

    private boolean hasArgumentEcoregion(String cmd) {
        return cmd.equals("--ecoregion") || cmd.equals("-e");
    }

    private boolean hasArgumentDiet(String cmd) {
        return cmd.equals("--diet") || cmd.equals("-d");
    }

    private boolean hasArgumentFamily(String cmd) {
        return cmd.equals("--family") || cmd.equals("-f");
    }

    private boolean hasArgumentConservation(String cmd) {
        return cmd.equals("--conservation") || cmd.equals("-cs");
    }

    private boolean hasArgumentBiome(String cmd) {
        return cmd.equals("--biome") || cmd.equals("-b");
    }

    private boolean hasArgumentSize(String cmd) {
        return cmd.equals("--size") || cmd.equals("-sz");
    }

    private boolean hasArgumentContinent(String cmd) {
        return cmd.equals("--continent") || cmd.equals("-ct");
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
