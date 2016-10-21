
package zoo.animalKeeper;

import exception.name.UnknownNameException;
import java.util.ArrayList;
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
    
    Task(int id){
        this.id = id;
    }
    
     
    Option option;

    public void setOption(Option option){
         for (Task task : Task.values()) {
             task.option = option;
         }
    }
    
     public Task findById(int id) throws UnknownNameException {
        for (Task status : Task.values()) {
            if (status.getId() == id) {
                return status;
            }
        }
        throw new UnknownNameException("Unknown id");
    }
     
        public ArrayList<String> list(){
         ArrayList<String> list = new ArrayList<>();
        for (Task task : Task.values()) {
            list.add(
                   task.getId() + " - " +  this.option.getKeeperBundle().getString(task.toString().toUpperCase()));
        }
        return list;
    }
    
}
