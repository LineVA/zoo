package exception.name;

import java.io.IOException;

/**
 * Abstract exception related to a problem with the name of a zoo, paddock, animal...
 * @author doyenm
 */
public abstract class NameException extends IOException {

    /**
     * Constructor
     * @param msg  the message to send
     */    public NameException(String msg) {
        super(msg);
    }
}
