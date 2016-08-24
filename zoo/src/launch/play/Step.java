package launch.play;

import lombok.Getter;
import zoo.IZoo;

/**
 *
 * @author doyenm
 */
public abstract class Step {
    
    @Getter
    String previous;
    @Getter
    String success;
    @Getter
    String fail;
    
    @Getter
    IZoo zoo;
    
    public Step(IZoo zoo, String previous, String success, String fail){
        this.zoo = zoo;
        this.previous = previous;
        this.success = success;
        this.fail = fail;
    }

    public abstract boolean check();
}


