package exception;

import java.io.IOException;

/**
 * Exception to throw when a data is incorrect : 
 * typically, when it wants to tkae a forbidden value
 * @author doyenm
 */
public class IncorrectDataException extends IOException {
    
    /**
     * Constructor
     * @param msg the msg to send 
     */
    public IncorrectDataException(String msg){
        super(msg);
    }
}
