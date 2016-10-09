package zoo.animalKeeper;

import java.util.Map;
import launch.options.Option;
import lombok.Getter;
import zoo.paddock.IPaddock;

/**
 *
 * @author doyenm
 */
public class FakeAnimalKeeper {
    @Getter
    String name;
    @Getter
    Map<IPaddock, Double> timedPaddocks;
    @Getter
    Map<TaskPaddock, Double> timedTasksPerPaddock;
    @Getter
    Map<Integer, Double> managedFamilies;
    @Getter
    Map<Integer, Double> managedTasks;

    public FakeAnimalKeeper(String name, Map<IPaddock, Double> timedPaddocks, 
            Map<TaskPaddock, Double> timedTasksPerPaddock, Map<Integer, Double> managedFamilies, 
            Map<Integer, Double> managedTasks) {
        this.name = name;
        this.timedPaddocks = timedPaddocks;
        this.timedTasksPerPaddock = timedTasksPerPaddock;
        this.managedFamilies = managedFamilies;
        this.managedTasks = managedTasks;
    }
    
    public AnimalKeeper convertToAnimalKeeper(Option option){
        return new AnimalKeeperBuilder().name(this.name)
                .timedPaddocks(this.timedPaddocks)
                .timedTaskPerPaddock(this.timedTasksPerPaddock)
                .managedFamilies(this.managedFamilies)
                .managedTasks(this.managedTasks)
                .buildAnimalKeeper();
    }
}
