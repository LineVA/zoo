package exception;

import java.io.IOException;

/**
 *
 * @author doyenm
 */
public class IncorrectDimensionsException extends IOException {
    public IncorrectDimensionsException(String message){
        super(message);
    }
}
