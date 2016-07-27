package launch;

import lombok.Getter;
import lombok.Setter;
import zoo.IZoo;
import zoo.Zoo;

/**
 *
 * @author doyenm
 */
public class FreePlayImpl implements Play{

    @Getter @Setter
    public IZoo zoo;

    public FreePlayImpl() {
        this.zoo = new Zoo();
    }
    
    
}
