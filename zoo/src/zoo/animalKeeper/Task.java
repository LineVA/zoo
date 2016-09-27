
package zoo.animalKeeper;

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
    
    
}
