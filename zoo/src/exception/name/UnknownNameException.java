package exception.name;

/**
 * Sub-case of NameException : if the searched name does not exist
 * @author doyenm
 */
public class UnknownNameException extends NameException {

         /**
     * Constructor
     * @param msg  the message to send
     */
    public UnknownNameException(String msg) {
        super(msg);
    }
}
