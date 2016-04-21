package exception.name;

import java.io.IOException;

/**
 *
 * @author doyenm
 */
public class EmptyNameException extends IOException {
    public EmptyNameException(String message){
        super(message);
    }
}
