package zoo.animalKeeper;

import exception.name.EmptyNameException;
import exception.name.NameException;
import exception.name.UnauthorizedNameException;
import java.util.HashMap;
import java.util.Map;
import launch.options.Option;
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
    private Option _option;

    public AnimalKeeper buildAnimalKeeper()
            throws EmptyNameException, UnauthorizedNameException {
        return new AnimalKeeperImpl(_name, _timedPaddocks, _timedTaskPerPaddock,
                _managedFamilies, _managedTasks, _option);
    }

    public AnimalKeeperBuilder option(Option option){
        this._option = option;
        return this;
    }
    
    public AnimalKeeperBuilder name(String _name) {
        this._name = _name;
        return this;
    }

    public AnimalKeeperBuilder timedPaddocks(Map<IPaddock, Double> paddocks) {
        this._timedPaddocks = paddocks;
        return this;
    }
    
    public AnimalKeeperBuilder timedTaskPerPaddock(Map<TaskPaddock, Double> paddocks) {
        this._timedTaskPerPaddock = paddocks;
        return this;
    }

    public AnimalKeeperBuilder managedFamilies(Map<Integer, Double> families) {
        this._managedFamilies = families;
        return this;
    }

    public AnimalKeeperBuilder managedTasks(Map<Integer, Double> tasks) {
        this._managedTasks = tasks;
        return this;
    }

}
