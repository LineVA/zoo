package zoo.animalKeeper;

import java.util.Objects;
import lombok.Getter;
import zoo.paddock.IPaddock;

/**
 *
 * @author doyenm
 */
public class TaskPaddock {

    @Getter
    private IPaddock paddock;
    @Getter
    private int task;
    
    public TaskPaddock(IPaddock paddock, int task){
        this.paddock = paddock;
        this.task = task;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.paddock);
        hash = 97 * hash + this.task;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TaskPaddock other = (TaskPaddock) obj;
        if (!Objects.equals(this.paddock, other.paddock)) {
            return false;
        }
        if (this.task != other.task) {
            return false;
        }
        return true;
    }
    
    
    
}
