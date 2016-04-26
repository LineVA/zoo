
package exception.name;

/**
 * Exception when the user used an unknown name for a paddock or an animal
 * CANNOT be used for an incorrect cmd (ex. : zpp instead of zoo)
 * @author doyenm
 */
public class UnknownNameException extends NameException {
    public UnknownNameException(String msg){
        super(msg);
    }
}
