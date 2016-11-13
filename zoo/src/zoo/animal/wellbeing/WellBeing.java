package zoo.animal.wellbeing;

import exception.name.UnknownNameException;
import java.util.List;
import zoo.animal.AnimalsAttributes;
import zoo.animal.feeding.FeedingAttributes;
import zoo.animal.specie.Specie;
import zoo.animalKeeper.AnimalKeeper;
import zoo.paddock.IPaddock;

/**
 *
 * @author doyenm
 */
public interface WellBeing {
    /**
     * Compute the well-being of an animal
     * @param attributes the attributes of this animal
     * @param pad the paddock of this animal
     * @param specie the specie of this animal
     * @param keepers the keepers of the zoo
     * @return the value of the well-being of the animal
     * @throws UnknownNameException if a member of an enum used to compute the well-being is unknown
     */
    public double computeWellBeing(AnimalsAttributes attributes, IPaddock pad, Specie specie, 
            List<AnimalKeeper> keepers)
            throws UnknownNameException;
    
    /**
     * Compute if a a value is close enough to the max well-being according 
     * to the level of the specie
     * @param compare the value we need to compare
     * @return true if it is close enough
     */
    public boolean isCloseEnoughToMax(double compare);
    
    /**
     * Check if an animal is starving
     * @param actualDiet the actual diet of the animal
     * @param feedinfgAt the actual FeedingAttributes of the animal
     * @param paddock the paddock of the animal
     * @param specieDiet the diets of the corresponding specie
     * @param keepers the keepers of the zoo
     * @return true if the actual diet is not one the specie's one, or if no keeper is feeding this paddock
     * or if the animal has 7 fast days
     */
    public boolean isStarving(int actualDiet, FeedingAttributes feedinfgAt, IPaddock paddock, 
            List<Integer> specieDiet, List<AnimalKeeper> keepers);
}
