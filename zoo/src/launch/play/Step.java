package launch.play;

import lombok.Getter;

/**
 *
 * @author doyenm
 */
public abstract class Step {
    
    @Getter
    String advert;
    

    public abstract boolean check();
}


