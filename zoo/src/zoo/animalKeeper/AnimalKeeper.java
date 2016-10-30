package zoo.animalKeeper;

import backup.save.SaveImpl;
import exception.IncorrectDataException;
import exception.name.UnknownNameException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import zoo.paddock.IPaddock;

/**
 *
 * @author doyenm
 */
public interface AnimalKeeper {

    public void addTaskToAPaddock(IPaddock paddock, List<Task> tasks, List<Double> times)
            throws IncorrectDataException, UnknownNameException;

    public void addTimedPaddocks(List<IPaddock> paddocks, List<Double> times)
            throws IncorrectDataException;

    public String getName();

    public Map<IPaddock, Double> getTimedPaddocks();

    public Map<TaskPaddock, Double> getTimedTaskPerPaddock();

    public List<String> info() throws UnknownNameException;

    public void removeTimedTasksPerPaddock(List<TaskPaddock> tasksPaddock);

    public void removeTimedPaddocks(List<IPaddock> paddocks);

    public boolean workInGivenPaddock(IPaddock paddock);

    public boolean isMakingCleaningInThePaddock(IPaddock paddock);

    public boolean isMakingFeedingInThePaddock(IPaddock paddock);

    public boolean isMakingMedicalTrainingInThePaddock(IPaddock paddock);

    public boolean isMakingEnrichmentInThePaddock(IPaddock paddock);

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
