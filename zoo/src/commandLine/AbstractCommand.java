package commandLine;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author doyenm
 */
public abstract class AbstractCommand implements Command{

    @Getter
    @Setter
    private boolean isSaving =false;
    
    
}
