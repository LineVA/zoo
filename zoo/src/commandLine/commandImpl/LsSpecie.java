package commandLine.commandImpl;

import basicGui.FormattingDisplay;
import commandLine.AbstractCommand;
import commandLine.ReturnExec;
import commandLine.SplittingAmpersand;
import commandLine.TypeReturn;
import exception.name.EmptyNameException;
import exception.name.UnknownNameException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import launch.play.Play;
import utils.Constants;
import utils.Utils;
import zoo.animal.specie.LightSpecie;
import zoo.paddock.IPaddock;

/**
 *
 * @author doyenm
 */
public class LsSpecie extends AbstractCommand {

    Set<String> paddockName;
    Set<String> biomes;
    Set<String> ecoregions;
    Set<String> diets;
    Set<String> families;
    Set<String> conservations;
    Set<String> sizes;
    Set<String> continents;
    Set<String> breedingProgrammes;
    Set<String> tags;

    public LsSpecie(Play play) {
        super(play);
    }

    public Set<IPaddock> convertToIPaddock(Set<String> strings)
            throws EmptyNameException, UnknownNameException {
        Set<IPaddock> paddocks = new HashSet<>();
        for (String str : strings) {
            paddocks.add(super.getPlay().getZoo().findPaddockByName(str));
        }
        return paddocks;
    }

    @Override
    public ReturnExec execute(String[] cmd) {
        Set<IPaddock> pad = null;
        LightSpecie light = new LightSpecie(null, null, null, null, null, null, null, null, null, null);
        try {
            if (!tags.isEmpty()) {
                light.setTags(tags);
            }
            if (!paddockName.isEmpty()) {
                pad = convertToIPaddock(paddockName);
            }
            if (!biomes.isEmpty()) {
                light.setBiome(Utils.convertToListOfInteger(biomes));
            }
            if (!ecoregions.isEmpty()) {
                light.setEcoregion(Utils.convertToListOfInteger(ecoregions));
            }
            if (!diets.isEmpty()) {
                light.setDiet(Utils.convertToListOfInteger(diets));
            }
            if (!families.isEmpty()) {
                light.setFamily(Utils.convertToListOfInteger(families));
            }
            if (!conservations.isEmpty()) {
                light.setConservation(Utils.convertToListOfInteger(conservations));
            }
            if (!sizes.isEmpty()) {
                light.setSize(Utils.convertToListOfInteger(sizes));
            }
            if (!continents.isEmpty()) {
                light.setContinent(Utils.convertToListOfInteger(continents));
            }
            if (!breedingProgrammes.isEmpty()) {
                light.setBreedingProgramme(Utils.convertToListOfInteger(breedingProgrammes));
            }
            super.setSuccess(true);
        } catch (EmptyNameException | UnknownNameException ex) {
            return new ReturnExec(ex.getMessage(), TypeReturn.ERROR);
        } catch (java.lang.NumberFormatException ex) {
            return new ReturnExec(
                    this.getPlay().getOption().getGeneralCmdBundle().getString("NUMBER_FORMAT_EXCEPTION"),
                    TypeReturn.ERROR);
        }
        List<String> list = super.getPlay().getZoo().listSpecie(light, pad);
        Collections.sort(list);
        return new ReturnExec(FormattingDisplay.formattingList(list), TypeReturn.SUCCESS);
    }

    public boolean firstCmd(String[] cmd) {
        if (cmd.length >= 2) {
            if (Arrays.asList(Constants.SPEC_OR_SPECIE).contains(cmd[0])) {
                if (Constants.LS.equalsIgnoreCase(cmd[1])) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkLength(String[] cmd) {
        return cmd.length >= 2 && cmd.length <= 20 && cmd.length % 2 == 0;
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

    private boolean hasArgumentBreedingProgramme(String cmd) {
        return cmd.equalsIgnoreCase("--breedingProgramme") || cmd.equalsIgnoreCase("-bP");
    }

    private boolean hasArgumentTags(String cmd) {
        return cmd.equalsIgnoreCase("--tag") || cmd.equalsIgnoreCase("-t");
    }

    private boolean saveArgument(String arg, String value) {
        if (this.hasArgumentBiome(arg)) {
            biomes = SplittingAmpersand.split(value);
        } else if (this.hasArgumentConservation(arg)) {
            conservations = SplittingAmpersand.split(value);
        } else if (this.hasArgumentDiet(arg)) {
            diets = SplittingAmpersand.split(value);
        } else if (this.hasArgumentEcoregion(arg)) {
            ecoregions = SplittingAmpersand.split(value);
        } else if (this.hasArgumentFamily(arg)) {
            families = SplittingAmpersand.split(value);
        } else if (this.hasArgumentPaddock(arg)) {
            paddockName = SplittingAmpersand.split(value);
        } else if (this.hasArgumentSize(arg)) {
            sizes = SplittingAmpersand.split(value);
        } else if (this.hasArgumentContinent(arg)) {
            continents = SplittingAmpersand.split(value);
        } else if (this.hasArgumentBreedingProgramme(arg)) {
            breedingProgrammes = SplittingAmpersand.split(value);
        } else if (this.hasArgumentTags(arg)) {
            tags = SplittingAmpersand.split(value);
        } else {
            return false;
        }
        return true;
    }

    @Override
    public boolean canExecute(String[] cmd) {
        biomes = new HashSet<>();
        ecoregions = new HashSet<>();
        families = new HashSet<>();
        diets = new HashSet<>();
        conservations = new HashSet<>();
        continents = new HashSet<>();
        sizes = new HashSet<>();
        breedingProgrammes = new HashSet<>();
        paddockName = new HashSet<>();
        tags = new HashSet<>();

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
