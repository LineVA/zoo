package exception.name;

/**
 * Sub-case of NameException when the name is empty or has only blank characters
 * @author doyenm
 */
public class EmptyNameException extends NameException {
    
        /**
     * Constructor
     * @param message  the message to send
     */
    public EmptyNameException(String message){
        super(message);
    }
}
