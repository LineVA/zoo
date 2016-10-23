package exception.name;

/**
 * Sub-case of NameException when a name cannot be allowed 
 * because another element of the class has the same name
 * @author doyenm
 */
public class AlreadyUsedNameException extends NameException {
    
        /**
     * Constructor
     * @param message  the message to send
     */
    public AlreadyUsedNameException(String message){
        super(message);
    }
}
