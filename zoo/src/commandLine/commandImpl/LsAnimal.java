package commandLine.commandImpl;

import basicGui.FormattingDisplay;
import commandLine.Command;
import exception.name.EmptyNameException;
import exception.name.UnknownNameException;
import java.util.ArrayList;
import launch.play.Play;
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
public class LsAnimal implements Command {

    Play play;
    // args[0] : the argument after '--specie'
    // args[1] : the argument after '--paddock'
    // args[2] : the argument after '--ecoregion'
    // args[3] : the argument after '--diet'
    // args[4] : the argument after '--sex'
    // args[5] : the argument after '--family'
    // args[6] : the argument after '--conservation'
    // args[7] : the argument after '--biome'
    // args[8] : the argument after '--size'

    String[] args;

    public LsAnimal(Play play) {
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
        IPaddock pad = null;
        Diet diet = null;
        Biome biome = null;
        Sex sex = null;
        LightSpecie spec = new LightSpecie(null,
                -1,
                -1,
                -1,
                -1,
                -1,
                -1);;
        try {
            if (args[0] != null) {
                spec.setNames(this.play.zoo.findSpecieByName(args[0]).getNames());
            }
            if (args[1] != null) {
                pad = this.play.getZoo().findPaddockByName(args[1]);
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
              
            this.success = true;
            ArrayList<String> names = new ArrayList<>();
            for (Animal animal : this.play.getZoo().listAnimal(pad, spec, sex, diet, biome)) {
                names.add(animal.getName());
            }
            return FormattingDisplay.formattingArrayList(names);
        } catch (EmptyNameException | UnknownNameException ex) {
            return ex.getMessage();
        } catch (NumberFormatException ex) {
            return "Integer ERROR !!!!!!!!!!";
        }
    }

    private boolean firstCmd(String[] cmd) {
        if (cmd.length >= 2) {
            if (cmd[0].equals("animal")) {
                if (cmd[1].equals("ls")) {
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
        return cmd.equals("--specie") || cmd.equals("-s");
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

    private boolean hasArgumentSex(String cmd) {
        return cmd.equals("--sex") || cmd.equals("-sex");
    }

    private boolean hasArgumentFamily(String cmd) {
        return cmd.equals("--family") || cmd.equals("-f");
    }

    private boolean hasArgumentConservation(String cmd) {
        return cmd.equals("--conservation") || cmd.equals("-c");
    }

    private boolean hasArgumentBiome(String cmd) {
        return cmd.equals("--biome") || cmd.equals("-b");
    }

    private boolean hasArgumentSize(String cmd) {
        return cmd.equals("--size") || cmd.equals("-size");
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
        } else {
            return false;
        }
        return true;
    }

    @Override
    public boolean canExecute(String[] cmd) {
        this.args = new String[]{null, null, null, null, null, null, null, null, null};
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
