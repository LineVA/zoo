package commandLine.commandImpl;

import basicGui.FormattingDisplay;
import commandLine.AbstractCommand;
import commandLine.ReturnExec;
import commandLine.SplittingAmpersand;
import commandLine.TypeReturn;
import exception.name.EmptyNameException;
import exception.name.UnknownNameException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import launch.play.Play;
import utils.Utils;
import zoo.animal.Animal;
import zoo.paddock.LightPaddock;

/**
 *
 * @author doyenm
 */
public class LsPaddock extends AbstractCommand {

    String[] args;
    Set<String> biomes;

    public LsPaddock(Play play) {
        super(play);
    }

    @Override
    public ReturnExec execute(String[] cmd) {
        LightPaddock light = new LightPaddock(null, null, null);
        try {
            if (!this.biomes.isEmpty()) {
                light.setBiomes(Utils.convertToSetOfInteger(biomes));
            }
            List<String> names = super.getPlay().getZoo().listPaddock(light);
            Collections.sort(names);
            super.setSuccess(true);
            return new ReturnExec(FormattingDisplay.formattingList(names), TypeReturn.SUCCESS);
        } catch (NumberFormatException ex) {
            return new ReturnExec("INTEGER ERROR", TypeReturn.ERROR);
        }
    }

//    @Override
//    public ReturnExec execute(String[] cmd) {
//        Specie spec = null;
//        if (args[0] != null) {
//            try {
//                spec = super.getPlay().getZoo().findSpecieByName(args[0]);
//                super.setSuccess(true);
//            } catch (EmptyNameException | UnknownNameException ex) {
//                return new ReturnExec(ex.getMessage(), TypeReturn.ERROR);
//            }
//        }
//        List<String> list = super.getPlay().getZoo().listPaddock(spec);
//        Collections.sort(list);
//        return new ReturnExec(FormattingDisplay.formattingList(list), TypeReturn.SUCCESS);
//    }
//    public boolean firstCmd(String[] cmd) {
//        if (cmd.length >= 2) {
//            if (cmd[0].equalsIgnoreCase("paddock") || cmd[0].equalsIgnoreCase("pad")) {
//                if (cmd[1].equalsIgnoreCase("ls")) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
    private boolean checkLength(String[] cmd) {
        return cmd.length >= 2 && cmd.length % 2 == 0 && cmd.length <= 6;
    }

    private boolean checkFirstParts(String[] cmd) {
        return ("pad".equalsIgnoreCase(cmd[0]) || "paddock".equalsIgnoreCase(cmd[0]))
                && "ls".equalsIgnoreCase(cmd[1]);
    }

//    @Override
//    public boolean canExecute(String[] cmd) {
//        this.args = new String[]{null};
//        if (firstCmd(cmd)) {
//            if (cmd.length == 2) {
//                return true;
//            } else if (cmd.length == 4 && (cmd[2].equalsIgnoreCase("-s") || cmd[2].equalsIgnoreCase("--specie"))) {
//                args[0] = cmd[3];
//                return true;
//            }
//        }
//        return false;
//    }
    private boolean hasArgumentBiome(String arg) {
        return "--biome".equalsIgnoreCase(arg) || "-b".equalsIgnoreCase(arg);
    }

    private boolean saveArguments(String arg, String value) {
        if (this.hasArgumentBiome(arg)) {
            this.biomes = SplittingAmpersand.split(value);
        } else {
            return false;
        }
        return true;
    }

    @Override
    public boolean canExecute(String[] cmd) {
        this.biomes = new HashSet<>();
        if (checkLength(cmd)) {
            if (checkFirstParts(cmd)) {
                int i = 2;
                while (i < cmd.length) {
                    if (!saveArguments(cmd[i], cmd[i + 1])) {
                        return false;
                    }
                    i += 2;
                }
                return true;
            }
        }
        return false;
    }
}
