package zoo.animalKeeper;

import backup.save.SaveImpl;
import exception.IncorrectDataException;
import exception.name.UnknownNameException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import zoo.paddock.IPaddock;

/**
 *
 * @author doyenm
 */
public interface AnimalKeeper {

    public void addTaskToAPaddock(IPaddock paddock, HashMap<Task, Double> newTimedTasks)
            throws IncorrectDataException, UnknownNameException;

    public void addTimedPaddocks(ArrayList<IPaddock> paddocks, ArrayList<Double> times)
            throws IncorrectDataException;

    public String getName();
    
    public Map<IPaddock, Double> getTimedPaddocks();
    
    public ArrayList<String> info() throws UnknownNameException;

    /**
     * Getters for the back up
     */
    public String getName(SaveImpl.FriendSave friend);

    public Map<IPaddock, Double> getTimedPaddocks(SaveImpl.FriendSave friend);

    public Map<TaskPaddock, Double> getTimedTaskPerPaddock(SaveImpl.FriendSave friend);

    public Map<Integer, Double> getManagedFamilies(SaveImpl.FriendSave friend);

    public Map<Integer, Double> getManagedTasks(SaveImpl.FriendSave friend);

    public void evolve();

}
