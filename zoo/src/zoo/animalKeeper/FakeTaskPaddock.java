package zoo.animalKeeper;

import lombok.Getter;

/**
 *
 * @author doyenm
 */
public class FakeTaskPaddock {
    @Getter
    private String paddock;
    @Getter
    private int task;
    
    public FakeTaskPaddock(String paddock, int task){
        this.paddock= paddock;
        this.task = task;
    }
    
}
