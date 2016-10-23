package exception.name;

/**
 * Sub-case of NameException : when an element has a unauthorized name
 * @author doyenm
 */
public class UnauthorizedNameException extends NameException{
    
        /**
     * Constructor
     * @param message  the message to send
     */
    public UnauthorizedNameException(String message){
        super(message);
    }
}
