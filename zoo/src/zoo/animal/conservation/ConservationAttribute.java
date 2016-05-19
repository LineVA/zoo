package zoo.animal.conservation;

import lombok.Getter;

/**
 *
 * @author doyenm
 */
public class ConservationAttribute {

    @Getter
    private int id;
    
    public ConservationAttribute(int id){
        this.id = id;
    }
    
}
