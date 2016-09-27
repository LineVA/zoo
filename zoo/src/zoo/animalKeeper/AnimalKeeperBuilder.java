package zoo.animalKeeper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import zoo.paddock.IPaddock;

/**
 *
 * @author doyenm
 */
public class AnimalKeeperBuilder {

    private String _name;
    private Map<IPaddock, Double> _timedPaddocks = new HashMap<>();
    private Map<TaskPaddock, Double> _timedTaskPerPaddock = new HashMap<>();
    private Map<Integer, Double> _managedFamilies = new HashMap<>();
    private Map<Integer, Double> _managedTasks = new HashMap<>();

    public AnimalKeeper buildAnimalKeeper() {
        return new AnimalKeeperImpl(_name, _timedPaddocks, _timedTaskPerPaddock,
                _managedFamilies, _managedTasks);
    }

    public AnimalKeeperBuilder name(String _name) {
        this._name = _name;
        return this;
    }

    public AnimalKeeperBuilder timedPaddocks(HashMap<IPaddock, Double> paddocks) {
        this._timedPaddocks = paddocks;
        return this;
    }
    
    public AnimalKeeperBuilder timedTaskPerPaddock(HashMap<TaskPaddock, Double> paddocks) {
        this._timedTaskPerPaddock = paddocks;
        return this;
    }

    public AnimalKeeperBuilder managedFamilies(HashMap<Integer, Double> families) {
        this._managedFamilies = families;
        return this;
    }

    public AnimalKeeperBuilder managedTasks(HashMap<Integer, Double> tasks) {
        this._managedTasks = tasks;
        return this;
    }

}
