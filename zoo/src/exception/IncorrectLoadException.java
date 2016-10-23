package exception;

import java.io.IOException;

/**
  Exception to throw after a problem with the loaded data
 * @author doyenm
 */
public class IncorrectLoadException extends IOException {

        /**
     * Constructor
     * @param msg  the message to send
     */
    public IncorrectLoadException(String msg) {
        super(msg);
    }
}
