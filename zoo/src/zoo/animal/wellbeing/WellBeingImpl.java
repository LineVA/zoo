package zoo.animal.wellbeing;

import exception.name.UnknownNameException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import zoo.animal.AnimalsAttributes;
import zoo.animal.feeding.FeedingAttributes;
import zoo.animal.personality.PersonalityAttributes;
import zoo.animal.social.SocialAttributes;
import zoo.animal.specie.Specie;
import zoo.animalKeeper.AnimalKeeper;
import zoo.animalKeeper.Task;
import zoo.animalKeeper.TaskPaddock;
import zoo.paddock.IPaddock;
import zoo.paddock.TerritoryAttributes;
import zoo.statistics.Compare;

/**
 *
 * @author doyenm
 */
public class WellBeingImpl implements WellBeing {

    private final double coefficient;
    private final double diameter;
    @Getter
    private final int criteriaNumber = 3;

    public WellBeingImpl(double coefficient, double diameter) {
        this.coefficient = coefficient;
        this.diameter = diameter;
    }

    /**
     * @param attributes
     * @param pad
     * @param specie
     * @param keepers
     * @return keepers : the keepers who work in the paddock of the current
     * animal
     * @throws UnknownNameException
     */
    @Override
    public double computeWellBeing(AnimalsAttributes attributes, IPaddock pad,
            Specie specie, List<AnimalKeeper> keepers)
            throws UnknownNameException {
        double wB = 0.0;
        wB += computeSocialWB(attributes.getOptimalSocial(), pad, specie);
        wB += computeTerritoryWB(attributes.getOptimalTerritory(), pad);
        wB += computeFoodWB(attributes.getActualDiet(), attributes.getOptimalFeeding(),
                attributes.getActualFeeding(), specie, keepers, pad
        );
        wB = (wB / 3) * this.coefficient;
        wB += checkBiomeWB(pad, specie);
        wB += compatibilitiesWB(pad, specie);
        wB += fearWB(pad, specie);
        wB += computeKeepersInfluence(attributes.getPersonality(), keepers, pad);
        System.out.println("Total : " + wB);
        return wB;
    }

    private double computeKeepersInfluence(PersonalityAttributes personality,
            List<AnimalKeeper> keepers, IPaddock paddock) {
        System.out.println("Personality");
        double wB = 0.0;
        TaskPaddock taskPaddock;
        for (AnimalKeeper keeper : keepers) {
            wB += influenceOfBravery(personality.getBravery(), keeper.getTimedPaddocks(), paddock);
            taskPaddock = new TaskPaddock(paddock, Task.MEDICALTRAINING.getId());
            if (keeper.getTimedTaskPerPaddock().containsKey(taskPaddock)) {
                wB += influenceOfSpecificTrait(personality.getIntelligence(),
                        keeper.getTimedTaskPerPaddock().get(taskPaddock));
            }
            taskPaddock = new TaskPaddock(paddock, Task.CLEANING.getId());
            if (keeper.getTimedTaskPerPaddock().containsKey(taskPaddock)) {
                wB += influenceOfSpecificTrait(personality.getMeticulousness(),
                        keeper.getTimedTaskPerPaddock().get(taskPaddock));
            }
            taskPaddock = new TaskPaddock(paddock, Task.FEEDING.getId());
            if (keeper.getTimedTaskPerPaddock().containsKey(taskPaddock)) {
                wB += influenceOfSpecificTrait(personality.getGreed(),
                        keeper.getTimedTaskPerPaddock().get(taskPaddock));
            }
            taskPaddock = new TaskPaddock(paddock, Task.ENRICHMENT.getId());
            if (keeper.getTimedTaskPerPaddock().containsKey(taskPaddock)) {
                wB += influenceOfSpecificTrait(personality.getCuriosity(),
                        keeper.getTimedTaskPerPaddock().get(taskPaddock));
            }
        }
        System.out.println(wB);
        return wB;
    }

    private double influenceOfSpecificTrait(double trait, double time) {
        if (trait >= 0.5) {
            System.out.println("if");
            return computeNumericInfluenceOfATrait(trait - 0.5, time);
        } else {
            System.out.println("else");
            return -computeNumericInfluenceOfATrait(trait, time);
        }
    }

    private double influenceOfBravery(Double trait, Map<IPaddock, Double> timedPaddocks, IPaddock paddock) {
        if (trait >= 0.5) {
            System.out.println("if");
            return computeNumericInfluenceOfATrait(trait - 0.5, timedPaddocks.get(paddock));
        } else {
            System.out.println("else");
            return -computeNumericInfluenceOfATrait(trait, timedPaddocks.get(paddock));
        }
    }

    private double computeNumericInfluenceOfATrait(double trait, double time) {
        return trait * time;
    }

    public double computeSocialWB(SocialAttributes social, IPaddock pad, Specie spec) {
        System.out.println("Social : ");
        double a = Compare.compare(social.getGroupSize(), pad.countAnimalsOfTheSameSpecie(spec), this.diameter);
        System.out.println(a);
        return a;
    }

    private double computeTerritoryWB(TerritoryAttributes territory, IPaddock pad) {
        System.out.println("Territory : ");
        double a = Compare.compare(territory.getTerritorySize(), pad.computeSize(), this.diameter);
        System.out.println(a);
        return a;
    }

    private boolean isAKeeperForFeeding(IPaddock paddock, List<AnimalKeeper> keepers) {
        for (AnimalKeeper keeper : keepers) {
            if (keeper.isMakingFeedingInThePaddock(paddock)) {
                return true;
            }
        }
        return false;
    }

    private double computeFoodWB(int diet, FeedingAttributes optimalFeeding,
            FeedingAttributes actualFeeding, Specie spec,
            List<AnimalKeeper> keepers, IPaddock paddock) {
        System.out.println("Food quantity : ");
        if (isAKeeperForFeeding(paddock, keepers) && spec.getDiets().contains(diet)) {
            System.out.println("Good diet");
            double a = Compare.compare(optimalFeeding.getFoodQuantity(), actualFeeding.getFoodQuantity(), this.diameter);
            System.out.println(a);
            return a;
        } else {
            System.out.println("Bad diet");
            System.out.println(Compare.getMin());
            return Compare.getMin();
        }
    }

    private double checkBiomeWB(IPaddock paddock, Specie specie) {
        if (specie.getBiomes().contains(paddock.getBiome())) {
            System.out.println("Good biome");
            return Compare.getPositivMean();
        } else {
            System.out.println("Bad biome");
            return Compare.getNegativMean();
        }
    }

    private double compatibilitiesWB(IPaddock pad, Specie spec) throws UnknownNameException {
        if (isThereIncompatibleSpeciesInThePaddock(pad, spec)) {
            System.out.println("Inciompatibilities");
            return Compare.getMin() * this.coefficient;
        } else {
            return 0.0;
        }
    }

    private double fearWB(IPaddock pad, Specie spec) throws UnknownNameException {
        if (isAfraidBySpeciesInOtherPaddocks(pad, spec)) {
            System.out.println("Afraid");
            return Compare.getNegativMean() * this.coefficient;
        } else {
            return 0.0;
        }
    }

    private boolean isThereIncompatibleSpeciesInThePaddock(IPaddock pad, Specie currentSpec)
            throws UnknownNameException {
        boolean absenceOfIncompabilities = true;
        ArrayList<Specie> species = pad.listSpecies();
        for (Specie spec : species) {
            if (spec != currentSpec) {
                absenceOfIncompabilities &= currentSpec.canBeInTheSamePaddock(spec);
            }
        }
        return !absenceOfIncompabilities;
    }

    private boolean isAfraidBySpeciesInOtherPaddocks(IPaddock pad, Specie currentSpec)
            throws UnknownNameException {
        boolean isAfraid = false;
        ArrayList<Specie> species = pad.listSpeciesInNeightbourhood();
        for (Specie spec : species) {
            isAfraid |= currentSpec.canBeAfraidOf(spec);
        }
        return isAfraid;
    }

    @Override
    public boolean isCloseEnoughToMax(double compare) {
        return (compare >= (1 - 2 * this.diameter) * Compare.getMax() * this.criteriaNumber);
    }

}
