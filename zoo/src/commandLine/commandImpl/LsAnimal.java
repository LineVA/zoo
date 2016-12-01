package commandLine.commandImpl;

import basicGui.FormattingDisplay;
import commandLine.AbstractCommand;
import commandLine.ReturnExec;
import commandLine.SplittingAmpersand;
import commandLine.TypeReturn;
import exception.name.EmptyNameException;
import exception.name.UnknownNameException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import launch.play.Play;
import utils.Constants;
import utils.Utils;
import zoo.animal.Animal;
import zoo.animal.LightAnimal;
import zoo.animal.feeding.Diet;
import zoo.animal.reproduction.Sex;
import zoo.animal.specie.LightSpecie;
import zoo.paddock.IPaddock;
import zoo.paddock.biome.Biome;

/**
 *
 * @author doyenm
 */
public class LsAnimal extends AbstractCommand {

    String specie = null;
    Set<String> sexes;
    Set<String> diets;
    Set<String> biomes;
    Set<String> ecoregions;
    Set<String> families;
    Set<String> conservations;
    Set<String> sizes;
    Set<String> continents;
    Set<String> breedingProgrammes;
    Set<String> paddocks;
    Set<String> tags;
    Set<String> fastDays;
    Set<String> starvations;
    Set<String> drownings;

    public LsAnimal(Play play) {
        super(play);
    }

    @Override
    public ReturnExec execute(String[] cmd) {
        LightSpecie spec = new LightSpecie(null, null, null, null, null, null, null, null, null, null, null);
        LightAnimal lightAnimal = new LightAnimal(null, null, null, null, null, null, null);
        try {
            if (specie != null) {
                spec.setNames(super.getPlay().getZoo().findSpecieByName(specie).getNames());
            }
            if (!ecoregions.isEmpty()) {
                spec.setEcoregion(Utils.convertToListOfInteger(ecoregions));
            }
            if (!families.isEmpty()) {
                spec.setFamily(Utils.convertToListOfInteger(families));
            }
            if (!conservations.isEmpty()) {
                spec.setConservation(Utils.convertToListOfInteger(conservations));
            }
            if (!sizes.isEmpty()) {
                spec.setSize(Utils.convertToListOfInteger(sizes));
            }
            if (!continents.isEmpty()) {
                spec.setContinent(Utils.convertToListOfInteger(continents));
            }
            if (!breedingProgrammes.isEmpty()) {
                spec.setBreedingProgramme(Utils.convertToListOfInteger(breedingProgrammes));
            }
            if (!tags.isEmpty()) {
                spec.setTags(tags);
            }
//            if (!paddocks.isEmpty()) {
                lightAnimal.setPaddocks(convertToIPaddock(paddocks));
//            }
            if (!sexes.isEmpty()) {
                lightAnimal.setSexes(convertStringToSex(sexes));
            }
            if (!biomes.isEmpty()) {
                lightAnimal.setBiomes(convertStringToBiome(biomes));
            }
            if (!diets.isEmpty()) {
                lightAnimal.setDiets(Utils.convertToSetOfInteger(diets));
            }
            if (!fastDays.isEmpty()) {
                lightAnimal.setFastDays(Utils.convertToSetOfInteger(fastDays));
            }
            if (!starvations.isEmpty()) {
                lightAnimal.setStarvations(Utils.convertToSetOfInteger(starvations));
            }
            if (!drownings.isEmpty()) {
                lightAnimal.setDrownings(Utils.convertToSetOfInteger(drownings));
            }
            super.setSuccess(true);
            List<String> names = new ArrayList<>();
            for (Animal animal : super.getPlay().getZoo().listAnimal(
                    spec, lightAnimal)) {
                names.add(animal.getName());
            }
            Collections.sort(names);
            return new ReturnExec(FormattingDisplay.formattingList(names), TypeReturn.SUCCESS);
        } catch (UnknownNameException | EmptyNameException ex) {
            return new ReturnExec(ex.getMessage(), TypeReturn.ERROR);
        } catch (NumberFormatException ex) {
            return new ReturnExec(super.getPlay().getOption().getGeneralCmdBundle()
                    .getString("NUMBER_FORMAT_EXCEPTION"), TypeReturn.ERROR);
        }
    }

    private Set<Sex> convertStringToSex(Set<String> strings)
            throws UnknownNameException, java.lang.NumberFormatException {
        Set<Sex> sexes = new HashSet<>();
        for (String str : strings) {
            sexes.add(Sex.UNKNOWN.findById(Integer.parseInt(str)));
        }
        return sexes;
    }

    private Set<Diet> convertStringToDiet(Set<String> strings)
            throws UnknownNameException, java.lang.NumberFormatException {
        Set<Diet> diets = new HashSet<>();
        for (String str : strings) {
            diets.add(Diet.NONE.findById(Integer.parseInt(str)));
        }
        return diets;
    }

    private Set<Biome> convertStringToBiome(Set<String> strings)
            throws UnknownNameException, java.lang.NumberFormatException {
        Set<Biome> biomes = new HashSet<>();
        for (String str : strings) {
            biomes.add(Biome.NONE.findById(Integer.parseInt(str)));
        }
        return biomes;
    }

    public Set<IPaddock> convertToIPaddock(Set<String> strings)
            throws EmptyNameException, UnknownNameException {
        Set<IPaddock> paddocks = new HashSet<>();
        for (String str : strings) {
            paddocks.add(super.getPlay().getZoo().findPaddockByName(str));
        }
        return paddocks;
    }

    private boolean firstCmd(String[] cmd) {
        if (cmd.length >= 2) {
            if (Constants.ANIMAL.equalsIgnoreCase(cmd[0])) {
                if (Constants.LS.equalsIgnoreCase(cmd[1])) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkLength(String[] cmd) {
        return cmd.length >= 2 && cmd.length <= 30 && cmd.length % 2 == 0;
    }

    private boolean hasArgumentSpecie(String cmd) {
        return Arrays.asList(Constants.SPECIE_ARG).contains(cmd);
    }

    private boolean hasArgumentSex(String cmd) {
        return Arrays.asList(Constants.SEX_ARG).contains(cmd);
    }

    private boolean hasArgumentPaddock(String cmd) {
        return Arrays.asList(Constants.PADDOCK_ARG).contains(cmd);
    }

    private boolean hasArgumentEcoregion(String cmd) {
        return Arrays.asList(Constants.ECOREGION_ARG).contains(cmd);
    }

    private boolean hasArgumentDiet(String cmd) {
        return Arrays.asList(Constants.DIET_ARG).contains(cmd);
    }

    private boolean hasArgumentFamily(String cmd) {
        return Arrays.asList(Constants.FAMILY_ARG).contains(cmd);
    }

    private boolean hasArgumentConservation(String cmd) {
        return Arrays.asList(Constants.CONSERVATION_ARG).contains(cmd);
    }

    private boolean hasArgumentBiome(String cmd) {
        return Arrays.asList(Constants.BIOME_ARG).contains(cmd);
    }

    private boolean hasArgumentSize(String cmd) {
        return Arrays.asList(Constants.SIZE_ARG).contains(cmd);
    }

    private boolean hasArgumentContinent(String cmd) {
        return Arrays.asList(Constants.CONTINENT_ARG).contains(cmd);
    }

    private boolean hasArgumentBreedingProgramme(String cmd) {
        return Arrays.asList(Constants.BREEDING_ARG).contains(cmd);
    }

    private boolean hasArgumentTags(String cmd) {
        return Arrays.asList(Constants.TAG_ARG).contains(cmd);
    }

    private boolean hasArgumentFastDays(String cmd) {
        return Arrays.asList(Constants.FASTDAY_ARG).contains(cmd);
    }

    private boolean hasArgumentStarvations(String cmd) {
        return Arrays.asList(Constants.STARVATION_ARG).contains(cmd);
    }

    private boolean hasArgumentDrownings(String cmd) {
        return Arrays.asList(Constants.DROWNING_ARG).contains(cmd);
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
            paddocks = SplittingAmpersand.split(value);
        } else if (this.hasArgumentSex(arg)) {
            sexes = SplittingAmpersand.split(value);
        } else if (this.hasArgumentSize(arg)) {
            sizes = SplittingAmpersand.split(value);
        } else if (this.hasArgumentSpecie(arg)) {
            specie = value;
        } else if (this.hasArgumentContinent(arg)) {
            continents = SplittingAmpersand.split(value);
        } else if (this.hasArgumentBreedingProgramme(arg)) {
            breedingProgrammes = SplittingAmpersand.split(value);
        } else if (this.hasArgumentTags(arg)) {
            tags = SplittingAmpersand.split(value);
        } else if (this.hasArgumentFastDays(arg)) {
            fastDays = SplittingAmpersand.split(value);
        } else if (this.hasArgumentStarvations(arg)) {
            starvations = SplittingAmpersand.split(value);
        } else if (this.hasArgumentDrownings(arg)) {
            drownings = SplittingAmpersand.split(value);
        } else {
            return false;
        }
        return true;
    }

    @Override
    public boolean canExecute(String[] cmd) {
        sexes = new HashSet<>();
        diets = new HashSet<>();
        biomes = new HashSet<>();
        conservations = new HashSet<>();
        ecoregions = new HashSet<>();
        families = new HashSet<>();
        sizes = new HashSet<>();
        continents = new HashSet<>();
        paddocks = new HashSet<>();
        breedingProgrammes = new HashSet<>();
        tags = new HashSet<>();
        fastDays = new HashSet<>();
        starvations = new HashSet<>();
        drownings = new HashSet<>();
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
