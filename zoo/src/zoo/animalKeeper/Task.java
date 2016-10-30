package zoo.animalKeeper;

import exception.name.UnknownNameException;
import java.util.ArrayList;
import java.util.List;
import launch.options.Option;
import lombok.Getter;

/**
 *
 * @author doyenm
 */
public enum Task {

    UNKNOWN(0),
    CLEANING(1),
    ENRICHMENT(2),
    FEEDING(3),
    MEDICALTRAINING(4);

    @Getter
    private int id;

    Task(int id) {
        this.id = id;
    }

    /**
     * The option used to know the current language
     */
    private Option option;

    public void setOption(Option option) {
        for (Task task : Task.values()) {
            task.option = option;
        }
    }

      /**
     * Find a task by its identifier
     * @param id the identifier to search
     * @return the corresponding diet
     * @throws UnknownNameException if no task matches the identifier 
     */
    public Task findById(int id) throws UnknownNameException {
        for (Task status : Task.values()) {
            if (status.getId() == id) {
                return status;
            }
        }
        throw new UnknownNameException("Unknown id");
    }

      /**
     * List all of the tasks
     * @return the list, each item corresponding to an element of the enumeration
     */
    public List<String> list() {
        List<String> list = new ArrayList<>();
        for (Task task : Task.values()) {
            list.add(
                    task.getId() + " - " + this.option.getKeeperBundle().getString(task.toString().toUpperCase()));
        }
        return list;
    }

}
