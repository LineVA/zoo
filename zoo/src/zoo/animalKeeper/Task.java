
package zoo.animalKeeper;

import exception.name.UnknownNameException;
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
    int id;
    
    Task(int id){
        this.id = id;
    }
    
     public Task findById(int id) throws UnknownNameException {
        for (Task status : Task.values()) {
            if (status.getId() == id) {
                return status;
            }
        }
        throw new UnknownNameException("Unknown id");
    }
    
}
