package exception;

import java.io.IOException;

/**
 *
 * @author doyenm
 */
public class UnexistedFileException extends IOException{

        /**
     * Constructor
     * @param msg  the message to send
     */
    public UnexistedFileException(String msg) {
        super(msg);
    }
}
