package zoo.animal.wellbeing;

import exception.name.UnknownNameException;
import java.util.ArrayList;
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

    private double coefficient;

    public WellBeingImpl(double coefficient) {
        this.coefficient = coefficient;
    }

    @Override
    public double computeWellBeing(AnimalsAttributes attributes, IPaddock pad, Specie specie) 
            throws UnknownNameException{
        double wB = 0.0;
        wB += computeSocialWB(attributes.getOptimalSocial(), pad, specie);
        wB += computeTerritoryWB(attributes.getOptimalTerritory(), pad);
        wB += computeFoodWB(attributes.getActualDiet(), attributes.getOptimalFeeding(),
                attributes.getActualFeeding(), specie);
        wB /= 3;
        wB += compatibilitiesWB(pad, specie);
        wB += fearWB(pad, specie);
        return wB;
    }

    public double computeSocialWB(SocialAttributes social, IPaddock pad, Specie spec) {
        System.out.println("Social : ");
        return Compare.compare(social.getGroupSize(), pad.countAnimalsOfTheSameSpecie(spec), this.coefficient);
    }

    public double computeTerritoryWB(TerritoryAttributes territory, IPaddock pad) {
        System.out.println("Territory : ");
        return Compare.compare(territory.getTerritorySize(), pad.computeSize(), this.coefficient);
    }

    public double computeFoodWB(int diet, FeedingAttributes optimalFeeding,
            FeedingAttributes actualFeeding, Specie spec) {
        System.out.println("Diet : ");
        if (diet == spec.getDiet()) {
            return Compare.compare(optimalFeeding.getFoodQuantity(), actualFeeding.getFoodQuantity(), this.coefficient);
        } else {
            return Compare.compare(optimalFeeding.getFoodQuantity(), 0, this.coefficient);
        }
    }

    public double compatibilitiesWB(IPaddock pad, Specie spec) throws UnknownNameException{
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

}
