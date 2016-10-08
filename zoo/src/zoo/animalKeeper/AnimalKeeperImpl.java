package zoo.animalKeeper;

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
public class AnimalKeeperImpl implements AnimalKeeper {

    private final String name;
    private Map<IPaddock, Double> timedPaddocks;
    private Map<TaskPaddock, Double> timedTaskPerPaddock;
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

//    private boolean checkCumulativeTime(Collection<Double> times) {
//        Double result = 0.0;
//        for (Double time : times) {
//            if (!checkIndiviualTime(time)) {
//                return false;
//            }
//            result += time;
//        }
//        return result <= 100.0;
//    }
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

//    private HashMap<Task, Boolean> initializeBooleans(HashMap<Task, Double> timedTask) {
//        HashMap<Task, Boolean> booleans = new HashMap<>();
//        for(HashMap.Entry<Task, Double> entry : timedTask.entrySet()){
//            booleans.put(entry.getKey(), false);
//        }
//        return booleans;
//    }
//    private ArrayList<Double> defineNewRepartitionForGivenPaddock
//        (IPaddock paddock, HashMap<Task, Double> timedTasks)
//            throws UnknownNameException {
//        ArrayList<Double> times = new ArrayList<>();
//        HashMap<Task, Boolean> booleans = initializeBooleans(timedTasks);
//        if (!this.timedTaskPerPaddock.isEmpty()) {
//            for (HashMap.Entry<TaskPaddock, Double> entry : this.timedTaskPerPaddock.entrySet()) {
//                if (timedTasks.containsKey(Task.UNKNOWN.findById(entry.getKey().getTask()))
//                        && entry.getKey().getPaddock().equals(paddock)) {
//                    times.add(timedTasks.get(entry.getKey()));
//                    booleans.put(Task.UNKNOWN.findById(entry.getKey().getTask()), true);
//                } else if (!timedTasks.containsKey(Task.UNKNOWN.findById(entry.getKey().getTask()))
//                        && entry.getKey().getPaddock().equals(paddock)) {
//                    times.add(entry.getValue());
//                }
//            }
//            for(HashMap.Entry<Task, Boolean> entry : booleans.entrySet()){
//                if(!entry.getValue()){
//                    times.add(timedTasks.get(entry.getKey()));
//                }
//            }
//        } else {
//            for (HashMap.Entry<Task, Double> entry : timedTasks.entrySet()) {
//                times.add(entry.getValue());
//            }
//        }
//        return times;
//    }
//    @Override
//    public void addTaskToAPaddock(IPaddock paddock, HashMap<Task, Double> timedTasks)
//            throws IncorrectDataException, UnknownNameException {
//        // Check if this animal keeper is associated to pad
//        if (this.timedPaddocks.containsKey(paddock)) {
//            // check if we can attribute this new collections of timed tasks to the paddock
//            if (checkCumulativeTime(defineNewRepartitionForGivenPaddock(paddock, timedTasks))) {
//                for (HashMap.Entry<Task, Double> entry : timedTasks.entrySet()) {
//                    this.timedTaskPerPaddock.put(new TaskPaddock(paddock, entry.getKey().getId()), entry.getValue());
//                }
//            } else {
//                throw new IncorrectDataException("La durée cumulative doit être comprise entre 0 et 100");
//            }
//        } else {
//            throw new IncorrectDataException("Il vous faut d'abord consacrer un temps à cet enclos");
//        }
//    }
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
            if(askedTimedTasks.containsKey(task)){
                actualTimedTasks.put(task.getId(), askedTimedTasks.get(task));
            } else if(oldTimedTasks.containsKey(task.getId())){
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
     
     private boolean checkTimes(ArrayList<Double> times){
         for(Double time : times){
             if(!this.checkIndiviualTime(time)){
                 return false;
             }
         }
         return this.checkCumulativeTime(times);
     }
     
     private HashMap<TaskPaddock, Double> reconstructTimedTasksPerPaddock
        (IPaddock paddock, HashMap<Integer, Double> timedTasks){
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
            if(checkTimes(times)){
                this.timedTaskPerPaddock = reconstructTimedTasksPerPaddock(paddock, askedTimedTasks);
            } else {
                    throw new IncorrectDataException("ITemps incorrects");
            }
              } else {
            throw new IncorrectDataException("Il vous faut d'abord consacrer un temps à cet enclos");
        }
    }
}
