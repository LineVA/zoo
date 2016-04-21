package exception.name;

import java.io.IOException;

/**
 *
 * @author doyenm
 */
public class AlreadyUsedNameException extends IOException {
    public AlreadyUsedNameException(String message){
        super(message);
    }
}
