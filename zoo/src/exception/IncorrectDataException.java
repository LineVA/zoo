package exception;

import java.io.IOException;

/**
 *
 * @author doyenm
 */
public class IncorrectDataException extends IOException {
    public IncorrectDataException(String msg){
        super(msg);
    }
}
