package zoo.animalKeeper;

import exception.IncorrectDataException;
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
    private Map<IPaddock, Double> managedPaddocks;
    private Map<Integer, Double> managedFamilies;
    private Map<Integer, Double> managedTasks;

    public AnimalKeeperImpl(String name, Map<IPaddock, Double> managedPaddocks,
            Map<Integer, Double> managedFamilies, Map<Integer, Double> managedTasks) {
        this.name = name;
        this.managedPaddocks = managedPaddocks;
        this.managedFamilies = managedFamilies;
        this.managedTasks = managedTasks;
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

    public void addAPaddock(IPaddock pad, Double time) throws IncorrectDataException {
        if (checkIndiviualTime(time)) {
            Double previousTime = this.managedPaddocks.putIfAbsent(pad, time);
            if (this.checkCumulativeTime(extractTimes(this.managedPaddocks))) {
                if (previousTime != null) {
                    this.managedPaddocks.put(pad, previousTime);
                } else {
                    this.managedPaddocks.remove(pad);
                }
            } else {
                throw new IncorrectDataException("La durée cumulative doit être comprise entre 0 et 100");
            }
        } else {
            throw new IncorrectDataException("Une durée doit être comprise entre 0 et 100");
        }
    }

}
