package zoo.animal.reproduction;

import exception.IncorrectDataException;
import exception.IncorrectLoadException;
import exception.name.NameException;
import java.util.ArrayList;
import zoo.animal.Animal;

/**
 *
 * @author doyenm
 */
public interface Reproduction {
    
    /**
     * Reprioducte an animal
     * @param animal the animal we try to reproducte
     * @param monthsPerEvaluation the number of months spent at every evaluation
     * @return the family of this animal :
     * [0] the mother
     * [1] the father
     * [2 .. n] the children 
     * @throws IncorrectDataException 
     * @throws NameException
     * @throws IncorrectLoadException 
     */
    public ArrayList<Animal> reproducte(Animal animal, int monthsPerEvaluation) 
            throws IncorrectDataException, NameException, IncorrectLoadException;
    
}
