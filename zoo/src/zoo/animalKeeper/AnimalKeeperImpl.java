package zoo.animalKeeper;

import exception.IncorrectDataException;
import java.util.ArrayList;
import java.util.Collection;
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
    
    private boolean checkCumulativeTime(Collection<Double> times){
        Double result = 0.0;
        for(Double time : times){
           if(!checkIndiviualTime(time)){
               return false;
           } 
           result += time;
        }
        return result <= 100.0;
    }

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

    public void addTaskToAPaddock(IPaddock paddock, HashMap<Task, Double> timedTasks) throws IncorrectDataException {
        // Check if this animal keeper is associated to pad
        if (this.timedPaddocks.containsKey(paddock)) {
            // check if we can attribute this new collections of timed tasks to the paddock
            if (checkCumulativeTime(timedTasks.values()))  {
                for (HashMap.Entry<Task, Double> entry : timedTasks.entrySet()) {
                    this.timedTaskPerPaddock.put(new TaskPaddock(paddock, entry.getKey().getId()), entry.getValue());
                }
            } else {
                throw new IncorrectDataException("La durée cumulative doit être comprise entre 0 et 100");
            }
        } else {
            throw new IncorrectDataException("Une durée doit être comprise entre 0 et 100");
        }
    }
}
