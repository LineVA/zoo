package zoo.animalKeeper;

import backup.save.SaveImpl;
import exception.IncorrectDataException;
import exception.name.UnknownNameException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import zoo.paddock.IPaddock;

/**
 *
 * @author doyenm
 */
public class AnimalKeeperImpl implements AnimalKeeper {

    private final String name;
    private Map<IPaddock, Double> timedPaddocks;
    private Map<TaskPaddock, Double> timedTaskPerPaddock;
    @Getter
    private Map<Integer, Double> managedFamilies;
    private Map<Integer, Double> managedTasks;

    public AnimalKeeperImpl(String name, Map<IPaddock, Double> timedPaddocks,
            Map<TaskPaddock, Double> timedTaskPerPaddock,
            Map<Integer, Double> managedFamilies, Map<Integer, Double> managedTasks) {
        this.name = name;
        this.timedPaddocks = timedPaddocks;
        this.managedFamilies = managedFamilies;
        this.managedTasks = managedTasks;
        this.timedTaskPerPaddock = timedTaskPerPaddock;
    }

    private boolean checkIndiviualTime(Double time) {
        return time >= 0.0 && time <= 100.0;
    }

    private boolean checkCumulativeTime(ArrayList<Double> times) {
        Double cumulativeTime = 0.0;
        cumulativeTime = times.stream().map((time) -> time)
                .reduce(cumulativeTime, (accumulator, _item) -> accumulator + _item);
        return cumulativeTime >= 0.0 && cumulativeTime <= 100.0;
    }

    private ArrayList<Double> extractTimes(Map<IPaddock, Double> occupations) {
        Object[] times = occupations.values().toArray();
        ArrayList<Double> timesList = new ArrayList<>();
        for (Object time : times) {
            timesList.add((Double) time);
        }
        return timesList;
    }

    @Override
    public void addAPaddock(IPaddock pad, Double time) throws IncorrectDataException {
        if (checkIndiviualTime(time)) {
            Double previousTime = this.timedPaddocks.putIfAbsent(pad, time);
            if (this.checkCumulativeTime(extractTimes(this.timedPaddocks))) {
                if (previousTime != null) {
                    this.timedPaddocks.put(pad, previousTime);
                } else {
                    this.timedPaddocks.remove(pad);
                }
            } else {
                throw new IncorrectDataException("La durée cumulative doit être comprise entre 0 et 100");
            }
        } else {
            throw new IncorrectDataException("Une durée doit être comprise entre 0 et 100");
        }
    }

    private HashMap<Integer, Double> findTimedTasksByPaddock(IPaddock paddock) {
        HashMap<Integer, Double> timedTasks = new HashMap<>();
        for (HashMap.Entry<TaskPaddock, Double> entry : this.timedTaskPerPaddock.entrySet()) {
            if (entry.getKey().getPaddock().equals(paddock)) {
                timedTasks.put(entry.getKey().getTask(), entry.getValue());
            }
        }
        return timedTasks;
    }

    private HashMap<Integer, Double> expectTimedTasks(
            HashMap<Integer, Double> oldTimedTasks, HashMap<Task, Double> askedTimedTasks) {
        HashMap<Integer, Double> actualTimedTasks = new HashMap<>();
        for (Task task : Task.values()) {
            if (askedTimedTasks.containsKey(task)) {
                actualTimedTasks.put(task.getId(), askedTimedTasks.get(task));
            } else if (oldTimedTasks.containsKey(task.getId())) {
                actualTimedTasks.put(task.getId(), oldTimedTasks.get(task.getId()));
            }
        }
        return actualTimedTasks;
    }

    private ArrayList<Double> extractTimesFromTimedTasks(Map<Integer, Double> timedTasks) {
        Object[] times = timedTasks.values().toArray();
        ArrayList<Double> timesList = new ArrayList<>();
        for (Object time : times) {
            timesList.add((Double) time);
        }
        return timesList;
    }

    private boolean checkTimes(ArrayList<Double> times) {
        for (Double time : times) {
            if (!this.checkIndiviualTime(time)) {
                return false;
            }
        }
        return this.checkCumulativeTime(times);
    }

    private HashMap<TaskPaddock, Double> reconstructTimedTasksPerPaddock(IPaddock paddock, HashMap<Integer, Double> timedTasks) {
        HashMap<TaskPaddock, Double> timedTasksPerPaddock = new HashMap<>();
        for (HashMap.Entry<Integer, Double> entry : timedTasks.entrySet()) {
            timedTasksPerPaddock.put(new TaskPaddock(paddock, entry.getKey()), entry.getValue());
        }
        return timedTasksPerPaddock;
    }

    @Override
    public void addTaskToAPaddock(IPaddock paddock, HashMap<Task, Double> newTimedTasks)
            throws IncorrectDataException, UnknownNameException {
        // Check if this animal keeper is associated to pad
        if (this.timedPaddocks.containsKey(paddock)) {
            // We retrieve all the timed tasks associated to this paddock
            HashMap<Integer, Double> oldTimedTasks = findTimedTasksByPaddock(paddock);
            // We remplace the old tasks by the new ones
            HashMap<Integer, Double> askedTimedTasks = expectTimedTasks(oldTimedTasks, newTimedTasks);
            // We check if the timer are consistent or not
            ArrayList<Double> times = extractTimesFromTimedTasks(askedTimedTasks);
            if (checkTimes(times)) {
                this.timedTaskPerPaddock = reconstructTimedTasksPerPaddock(paddock, askedTimedTasks);
            } else {
                throw new IncorrectDataException("ITemps incorrects");
            }
        } else {
            throw new IncorrectDataException("Il vous faut d'abord consacrer un temps à cet enclos");
        }
    }

    private double computeFamilies(IPaddock paddock) {
        return this.timedPaddocks.get(paddock) / 100.0;
    }

    @Override
    public void evaluateByFamily(IPaddock paddock) {
        if (this.timedPaddocks.containsKey(paddock)) {
            ArrayList<Integer> familiesList = paddock.listFamiliesById();
            for (Integer family : familiesList) {
                double init = 0.0;
                if (this.managedFamilies.containsKey(family)) {
                    init = this.managedFamilies.get(family);
                }
                init += computeFamilies(paddock);
                this.managedFamilies.put(family, init);
            }
        }
    }
    
    private Double computeTasks(TaskPaddock taskPaddock){
        return this.timedPaddocks.get(taskPaddock.getPaddock()) /100
                * this.timedTaskPerPaddock.get(taskPaddock) / 100;
    }
    
    @Override
    public void evaluateByTask() {
        double init = 0.0;
        for (HashMap.Entry<TaskPaddock, Double> entry : this.timedTaskPerPaddock.entrySet()) {
         if(this.managedTasks.containsKey(entry.getKey().getTask())){
             init = this.managedTasks.get(entry.getKey().getTask());
             this.managedTasks.put(entry.getKey().getTask(), init + computeTasks(entry.getKey()));
         }
        }
    }
    
    
    /**
     * Getters
     */
    @Override
    public String getName(SaveImpl.FriendSave friend){
        friend.hashCode();
        return this.name;
    }
    
    @Override
    public Map<IPaddock, Double> getTimedPaddocks(SaveImpl.FriendSave friend){
        friend.hashCode();
        return this.timedPaddocks;
    }
    
    @Override
    public Map<TaskPaddock, Double> getTimedTaskPerPaddock(SaveImpl.FriendSave friend){
        friend.hashCode();
        return this.timedTaskPerPaddock;
    }
    
    @Override
    public Map<Integer, Double> getManagedFamilies(SaveImpl.FriendSave friend){
        friend.hashCode();
        return this.managedFamilies;
    }
    
    @Override
    public Map<Integer, Double> getManagedTasks(SaveImpl.FriendSave friend){
        friend.hashCode();
        return this.managedTasks;
    }
    
}
