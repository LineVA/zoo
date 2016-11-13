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
     * @throws UnknownNameException if
     */
    public double computeWellBeing(AnimalsAttributes attributes, IPaddock pad, Specie specie, 
            List<AnimalKeeper> keepers)
            throws UnknownNameException;
    
    public boolean isCloseEnoughToMax(double compare);
    
    public boolean isStarving(int actualDiet, FeedingAttributes feedinfgAt, IPaddock paddock, 
            List<Integer> specieDiet, List<AnimalKeeper> keepers);
}
