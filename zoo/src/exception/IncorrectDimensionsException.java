package exception;

/**
 * The exception to throw when a zoo or a paddock has incorrect dimensions
 * @author doyenm
 */
public class IncorrectDimensionsException extends IncorrectDataException {
    
    /**
     * Constructor
     * @param message  the message to send
     */
    public IncorrectDimensionsException(String message){
        super(message);
    }
}
