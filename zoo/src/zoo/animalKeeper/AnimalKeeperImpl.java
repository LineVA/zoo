package zoo.animalKeeper;

import java.util.ArrayList;
import java.util.Map;
import zoo.paddock.IPaddock;

/**
 *
 * @author doyenm
 */
public class AnimalKeeperImpl implements AnimalKeeper{
    
    private final String name;
    private ArrayList<IPaddock> managedPaddocks;
    private Map<Integer, Double> managedFamilies;
    private Map<Integer, Double> managedTasks;

    public AnimalKeeperImpl(String name, ArrayList<IPaddock> managedPaddocks, 
            Map<Integer, Double> managedFamilies, Map<Integer, Double> managedTasks) {
        this.name = name;
        this.managedPaddocks = managedPaddocks;
        this.managedFamilies = managedFamilies;
        this.managedTasks = managedTasks;
    }
    

}
