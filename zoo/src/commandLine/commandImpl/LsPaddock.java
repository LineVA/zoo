package commandLine.commandImpl;

import basicGui.FormattingDisplay;
import commandLine.AbstractCommand;
import commandLine.ReturnExec;
import commandLine.SplittingAmpersand;
import commandLine.TypeReturn;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import launch.play.Play;
import utils.Utils;
import zoo.paddock.LightPaddock;

/**
 *
 * @author doyenm
 */
public class LsPaddock extends AbstractCommand {

    String[] args;
    Set<String> biomes;
    Set<String> types;

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
               if (!this.types.isEmpty()) {
                light.setTypes(Utils.convertToSetOfInteger(types));
            }
            List<String> names = super.getPlay().getZoo().listPaddock(light);
            Collections.sort(names);
            super.setSuccess(true);
            return new ReturnExec(FormattingDisplay.formattingList(names), TypeReturn.SUCCESS);
        } catch (NumberFormatException ex) {
            return new ReturnExec("INTEGER ERROR", TypeReturn.ERROR);
        }
    }

    private boolean checkLength(String[] cmd) {
        return cmd.length >= 2 && cmd.length % 2 == 0 && cmd.length <= 6;
    }

    private boolean checkFirstParts(String[] cmd) {
        return ("pad".equalsIgnoreCase(cmd[0]) || "paddock".equalsIgnoreCase(cmd[0]))
                && "ls".equalsIgnoreCase(cmd[1]);
    }

    private boolean hasArgumentBiome(String arg) {
        return "--biome".equalsIgnoreCase(arg) || "-b".equalsIgnoreCase(arg);
    }

    private boolean hasArgumentType(String arg) {
        return "-pt".equalsIgnoreCase(arg) || "--padType".equalsIgnoreCase(arg)
                || "--paddockType".equalsIgnoreCase(arg);
    }

    private boolean saveArguments(String arg, String value) {
        if (this.hasArgumentBiome(arg)) {
            this.biomes = SplittingAmpersand.split(value);
        } else if (this.hasArgumentType(arg)) {
            this.types = SplittingAmpersand.split(value);
        } else {
            return false;
        }
        return true;
    }

    @Override
    public boolean canExecute(String[] cmd) {
        this.biomes = new HashSet<>();
        this.types = new HashSet<>();
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
