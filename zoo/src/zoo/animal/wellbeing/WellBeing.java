package zoo.animal.wellbeing;

import exception.name.UnknownNameException;
import zoo.animal.AnimalsAttributes;
import zoo.animal.specie.Specie;
import zoo.paddock.IPaddock;

/**
 *
 * @author doyenm
 */
public interface WellBeing {
    public double computeWellBeing(AnimalsAttributes attributes, IPaddock pad, Specie specie)
            throws UnknownNameException;
}
