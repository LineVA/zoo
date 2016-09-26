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
    private ArrayList<IPaddock> _managedPaddocks = new ArrayList<>();
    private Map<Integer, Double> _managedFamilies = new HashMap<>();
    private Map<Integer, Double> _managedTasks = new HashMap<>();

    public AnimalKeeper buildAnimalKeeper() {
        return new AnimalKeeperImpl(_name, _managedPaddocks,
                _managedFamilies, _managedTasks);
    }

    public AnimalKeeperBuilder name(String _name){
        this._name = _name;
        return this;
    }
    
    public AnimalKeeperBuilder managedPaddocks(ArrayList<IPaddock> paddocks){
        this._managedPaddocks = paddocks;
        return this;
    }
    
    public AnimalKeeperBuilder managedFamilies(HashMap<Integer, Double> families){
        this._managedFamilies = families;
        return this;
    }
    
     public AnimalKeeperBuilder managedTasks(HashMap<Integer, Double> tasks){
        this._managedTasks = tasks;
        return this;
    }
    
}
