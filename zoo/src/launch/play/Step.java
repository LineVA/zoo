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
    String next;
    
    @Getter
    IZoo zoo;
    
    public Step(IZoo zoo, String previous, String after){
        this.zoo = zoo;
        this.previous = previous;
        this.next = after;
    }

    public abstract boolean check();
}


