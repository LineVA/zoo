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
import java.util.Set;
import launch.play.Play;
import utils.Utils;
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
public class LsAnimal extends AbstractCommand {

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
    // args[10] : the argument after '--breedingProgramme'
    String[] args;
    Set<String> sexes;
    Set<String> diets;
    Set<String> biomes;
    Set<String> ecoregions;
    Set<String> families;
    Set<String> conservations;
    Set<String> sizes;
    Set<String> continents;
    Set<String> breedingProgrammes;
    
    public LsAnimal(Play play) {
        super(play);
    }

    @Override
    public ReturnExec execute(String[] cmd) {
        IPaddock pad = null;
//        Diet diet = null;
        Biome biome = null;
        LightSpecie spec = new LightSpecie(null, null, null, null, null, null, null, null, null);
        try {
            if (args[0] != null) {
                spec.setNames(super.getPlay().getZoo().findSpecieByName(args[0]).getNames());
            }
//            if (args[1] != null) {
//                pad = super.getPlay().getZoo().findPaddockByName(args[1]);
//            }
            if (ecoregions.size() != 0) {
                spec.setEcoregion(Utils.convertToArrayListOfInteger(ecoregions));
            }
            if (!families.isEmpty()) {
                spec.setFamily(Utils.convertToArrayListOfInteger(families));
            }
            if (!conservations.isEmpty()) {
                spec.setConservation(Utils.convertToArrayListOfInteger(conservations));
            }
            if (!sizes.isEmpty()) {
                spec.setSize(Utils.convertToArrayListOfInteger(sizes));
            }
            if (!continents.isEmpty()) {
                spec.setContinent(Utils.convertToArrayListOfInteger(continents));
            }
            if (!breedingProgrammes.isEmpty()) {
                spec.setBreedingProgramme(Utils.convertToArrayListOfInteger(breedingProgrammes));
            }
            super.setSuccess(true);
            ArrayList<String> names = new ArrayList<>();
            for (Animal animal : super.getPlay().getZoo().listAnimal(pad, spec, convertStringToSex(sexes), 
                    convertStringToDiet(diets), convertStringToBiome(biomes))) {
                names.add(animal.getName());
            }
            Collections.sort(names);
            return new ReturnExec(FormattingDisplay.formattingArrayList(names), TypeReturn.SUCCESS);
        } catch (UnknownNameException | EmptyNameException ex) {
            return new ReturnExec(ex.getMessage(), TypeReturn.ERROR);
        } catch (NumberFormatException ex) {
            return new ReturnExec("INTEGER ERROR", TypeReturn.ERROR);
        } 
    }
    
    private Set<Sex> convertStringToSex(Set<String> strings)
            throws UnknownNameException, java.lang.NumberFormatException{
        Set<Sex> sexes = new HashSet<>();
        for(String str : strings){
            sexes.add(Sex.UNKNOWN.findById(Integer.parseInt(str)));
        }
        return sexes;
    }
    
     private Set<Diet> convertStringToDiet(Set<String> strings)
            throws UnknownNameException, java.lang.NumberFormatException{
        Set<Diet> diets = new HashSet<>();
        for(String str : strings){
            diets.add(Diet.NONE.findDietById(Integer.parseInt(str)));
        }
        return diets;
    }
     
      private Set<Biome> convertStringToBiome(Set<String> strings)
            throws UnknownNameException, java.lang.NumberFormatException{
        Set<Biome> biomes = new HashSet<>();
        for(String str : strings){
            biomes.add(Biome.NONE.findById(Integer.parseInt(str)));
        }
        return biomes;
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
        return cmd.length >= 2 && cmd.length <= 22 && cmd.length % 2 == 0;
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

    private boolean hasArgumentBreedingProgramme(String cmd) {
        return cmd.equalsIgnoreCase("--breedingProgramme") || cmd.equalsIgnoreCase("-bp");
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
            args[1] = value;
        } else if (this.hasArgumentSex(arg)) {
            sexes = SplittingAmpersand.split(value);
        } else if (this.hasArgumentSize(arg)) {
            sizes = SplittingAmpersand.split(value);
        } else if (this.hasArgumentSpecie(arg)) {
            args[0] = value;
        } else if (this.hasArgumentContinent(arg)) {
            continents = SplittingAmpersand.split(value);
        } else if (this.hasArgumentBreedingProgramme(arg)) {
            breedingProgrammes = SplittingAmpersand.split(value);
        } else {
            return false;
        }
        return true;
    }

    @Override
    public boolean canExecute(String[] cmd) {
        this.args = new String[]{null, null, null, null, null, null, null, null, null, null, null};
        sexes = new HashSet<>();
        diets = new HashSet<>();
        biomes = new HashSet<>();
        conservations = new HashSet<>();
        ecoregions = new HashSet<>();
        families = new HashSet<>();
        sizes = new HashSet<>();
        continents = new HashSet<>();
        breedingProgrammes = new HashSet<>();
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
