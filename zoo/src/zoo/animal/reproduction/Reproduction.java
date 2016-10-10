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
    public ArrayList<Animal> reproducte(Animal animal) 
            throws IncorrectDataException, NameException, IncorrectLoadException;
    
}
