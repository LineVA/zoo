package zoo.animal.feeding;

import lombok.Getter;

/**
 *
 * @author doyenm
 */
public enum Size {
    UNKNOWN(0),
    EXTRASMALL(1),
    SMALL(2),
    MEDIUM(3),
    LARGE(4),
    EXTRALARGE(5);
    
    @Getter
    int id;
    
    Size(int id){
        this.id = id;
    }
}
