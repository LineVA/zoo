package zoo.animal.wellbeing;

import exception.name.UnknownNameException;
import java.util.List;
import zoo.animal.AnimalsAttributes;
import zoo.animal.specie.Specie;
import zoo.animalKeeper.AnimalKeeper;
import zoo.paddock.IPaddock;

/**
 *
 * @author doyenm
 */
public interface WellBeing {
    public double computeWellBeing(AnimalsAttributes attributes, IPaddock pad, Specie specie, List<AnimalKeeper> keepers)
            throws UnknownNameException;
    
    public boolean isCloseEnoughToMax(double compare);
}
