package zoo.animal.wellbeing;

import exception.name.UnknownNameException;
import java.util.ArrayList;
import lombok.Getter;
import zoo.animal.AnimalsAttributes;
import zoo.animal.feeding.FeedingAttributes;
import zoo.animal.social.SocialAttributes;
import zoo.animal.specie.Specie;
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

    @Override
    public double computeWellBeing(AnimalsAttributes attributes, IPaddock pad, Specie specie)
            throws UnknownNameException {
        double wB = 0.0;
        wB += computeSocialWB(attributes.getOptimalSocial(), pad, specie);
        wB += computeTerritoryWB(attributes.getOptimalTerritory(), pad);
        wB += computeFoodWB(attributes.getActualDiet(), attributes.getOptimalFeeding(),
                attributes.getActualFeeding(), specie);
        wB = (wB / 3) * this.coefficient;
        wB += checkBiomeWB(pad, specie);
        wB += compatibilitiesWB(pad, specie);
        wB += fearWB(pad, specie);
        System.out.println("Total : " + wB);
        return wB;
    }

    public double computeSocialWB(SocialAttributes social, IPaddock pad, Specie spec) {
        System.out.println("Social : ");
        double a = Compare.compare(social.getGroupSize(), pad.countAnimalsOfTheSameSpecie(spec), this.diameter);
        System.out.println(a);
        return a;
    }

    public double computeTerritoryWB(TerritoryAttributes territory, IPaddock pad) {
        System.out.println("Territory : ");
        double a = Compare.compare(territory.getTerritorySize(), pad.computeSize(), this.diameter);
        System.out.println(a);
        return a;
    }

    public double computeFoodWB(int diet, FeedingAttributes optimalFeeding,
            FeedingAttributes actualFeeding, Specie spec) {
        System.out.println("Food quantity : ");
        if (diet == spec.getDiet()) {
            System.out.println("Good diet");
            double a = Compare.compare(optimalFeeding.getFoodQuantity(), actualFeeding.getFoodQuantity(), this.diameter);
            System.out.println(a);
            return a;
        } else {
            System.out.println("Bad diet");
            System.out.println(Compare.returnMin());
            return Compare.returnMin();
        }
    }

    public double checkBiomeWB(IPaddock paddock, Specie specie) {
        if (paddock.getBiome() == specie.getBiome()) {
            System.out.println("Good biome");
            return Compare.returnPositivMean();
        } else {
            System.out.println("Bad biome");
            return Compare.returnNegativMean();
        }
    }

    public double compatibilitiesWB(IPaddock pad, Specie spec) throws UnknownNameException {
        if (isThereIncompatibleSpeciesInThePaddock(pad, spec)) {
            System.out.println("Inciompatibilities");
            return Compare.returnMin() * this.coefficient;
        } else {
            return 0.0;
        }
    }

    public double fearWB(IPaddock pad, Specie spec) throws UnknownNameException {
        if (isAfraidBySpeciesInOtherPaddocks(pad, spec)) {
            System.out.println("Afraid");
            return Compare.returnNegativMean() * this.coefficient;
        } else {
            return 0.0;
        }
    }

    public boolean isThereIncompatibleSpeciesInThePaddock(IPaddock pad, Specie currentSpec)
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

    public boolean isAfraidBySpeciesInOtherPaddocks(IPaddock pad, Specie currentSpec)
            throws UnknownNameException {
        boolean isAfraid = false;
        ArrayList<Specie> species = pad.listSpeciesInNeightbourhood();
        for (Specie spec : species) {
            isAfraid |= currentSpec.canBeAfraidOf(spec);
        }
        return isAfraid;
    }

    public boolean isCloseEnoughToMax(double compare) {
        return (compare >= (1 - 2 * this.diameter) * Compare.getMax() * this.criteriaNumber);
    }

}
