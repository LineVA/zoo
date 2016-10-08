package zoo.animalKeeper;

import exception.IncorrectDataException;
import exception.name.UnknownNameException;
import java.util.HashMap;
import zoo.paddock.IPaddock;

/**
 *
 * @author doyenm
 */
public interface AnimalKeeper {

    public void addTaskToAPaddock(IPaddock paddock, HashMap<Task, Double> newTimedTasks)
            throws IncorrectDataException, UnknownNameException;

    public void addAPaddock(IPaddock pad, Double time) throws IncorrectDataException;

    public void evaluateByFamily(IPaddock paddock);
}
