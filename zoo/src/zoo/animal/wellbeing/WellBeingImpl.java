package zoo.animal.wellbeing;

import exception.name.UnknownNameException;
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
import zoo.paddock.PaddockTypes;
import zoo.paddock.TerritoryAttributes;
import zoo.statistics.Compare;

/**
 *
 * @author doyenm
 */
public class WellBeingImpl implements WellBeing {

    /**
     * The coefficient to apply to the grade according to the level of the
     * specie
     */
    private final double coefficient;
    /**
     * The width of each interval associated with a grade
     */
    private final double diameter;
    /**
     * Number of criteria used to compute
     */
    @Getter
    private final int criteriaNumber = 3;

    /**
     * Constructor
     *
     * @param coefficient the coeficient
     * @param diameter the diameter
     */
    public WellBeingImpl(double coefficient, double diameter) {
        this.coefficient = coefficient;
        this.diameter = diameter;
    }

    public boolean isDrowning(IPaddock paddock) {
        return paddock.getPaddockType() == PaddockTypes.AQUARIUM.getId();
    }

    @Override
    public boolean isStarving(int actualDiet, FeedingAttributes feedingAt, IPaddock paddock,
            List<Integer> specieDiet, List<AnimalKeeper> keepers) {
        if (feedingAt.getFastDays() == 7) {
            return true;
        }
        if (!specieDiet.contains(actualDiet)) {
            return true;
        }
        for (AnimalKeeper keeper : keepers) {
            if (keeper.isMakingFeedingInThePaddock(paddock)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Compute the well-beeing of the animal
     *
     * @param attributes the artributes of the animal
     * @param pad the paddock in which the animal is
     * @param specie the specie of the animal
     * @param keepers the keepers who work in the paddock of the current animal
     * @return the value of the weel-beeing of the animal
     * @throws UnknownNameException if the informations about the specie are
     * corrupted
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

    /**
     * Compute the influence of the keepers on the animal
     *
     * @param personality the personality's attributes of the animal
     * @param keepers the keepers who work in the same paddock where the animal
     * is
     * @param paddock the paddock of the animal
     * @return the value of weel-beeing given by the keepers
     */
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

    /**
     * Compute the influence of a specific personality's trait
     *
     * @param trait the value of the trait
     * @param time the time spent by the keeper with the animal
     * @return the value of the influence
     */
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

    private boolean isActuallyFeeding(IPaddock paddock, List<AnimalKeeper> keepers,
            Specie spec, int diet, FeedingAttributes actualFeedingAttributes) {
        return isAKeeperForFeeding(paddock, keepers) && spec.getDiets().contains(diet)
                && actualFeedingAttributes.getFastDays() < 7;
    }

    private double computeFoodWB(int diet, FeedingAttributes optimalFeeding,
            FeedingAttributes actualFeeding, Specie spec,
            List<AnimalKeeper> keepers, IPaddock paddock) {
        System.out.println("Food quantity : ");
        if (isActuallyFeeding(paddock, keepers, spec, diet, actualFeeding)) {
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
            System.out.println("Incompatibilities");
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
        List<Specie> species = pad.listSpecies();
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
        List<Specie> species = pad.listSpeciesInNeightbourhood();
        for (Specie spec : species) {
            isAfraid |= currentSpec.canBeAfraidOf(spec);
        }
        return isAfraid;
    }

    /**
     * Check if the value of the well-beeing is greater than 1-2*diameter of the
     * maximum
     *
     * @param compare the value we want to compare
     * @return true if it is close enough
     */
    @Override
    public boolean isCloseEnoughToMax(double compare) {
        return (compare >= (1 - 2 * this.diameter) * Compare.getMax() * this.criteriaNumber);
    }

}
