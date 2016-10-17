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
    Map<String, Double> timedPaddocks;
    @Getter
    Map<FakeTaskPaddock, Double> timedTasksPerPaddock;
    @Getter
    Map<Integer, Double> managedFamilies;
    @Getter
    Map<Integer, Double> managedTasks;

    public FakeAnimalKeeper(String name, Map<String, Double> timedPaddocks, 
            Map<FakeTaskPaddock, Double> timedTasksPerPaddock, Map<Integer, Double> managedFamilies, 
            Map<Integer, Double> managedTasks) {
        this.name = name;
        this.timedPaddocks = timedPaddocks;
        this.timedTasksPerPaddock = timedTasksPerPaddock;
        this.managedFamilies = managedFamilies;
        this.managedTasks = managedTasks;
    }
    
    public AnimalKeeper convertToAnimalKeeper(Map<IPaddock, Double> timedPads, 
            Map<TaskPaddock, Double> timedTasks, Option option){
        return new AnimalKeeperBuilder().name(this.name)
                .timedPaddocks(timedPads)
                .timedTaskPerPaddock(timedTasks)
                .managedFamilies(this.managedFamilies)
                .managedTasks(this.managedTasks)
                .option(option)
                .buildAnimalKeeper();
    }
}
