package zoo;

import exception.name.EmptyNameException;
import exception.name.UnauthorizedNameException;
import java.util.ResourceBundle;

/**
 *
 * @author doyenm
 */
public class NameVerifications {

    public static boolean verify(String name, ResourceBundle bundle)
            throws EmptyNameException, UnauthorizedNameException{
        return isNotEmpty(name, bundle) && isNotLs(name, bundle); 
    }
    
    public static boolean isNotEmpty(String name, ResourceBundle bundle )
            throws EmptyNameException{
        if(name.trim().equals("")){
            throw new EmptyNameException(bundle.getString("EMPTY_NAME"));
        }
        return true;
    }
    
    public static boolean isNotLs(String name, ResourceBundle bundle) 
            throws UnauthorizedNameException{
          if(name.equalsIgnoreCase("ls")){
            throw new UnauthorizedNameException(bundle.getString("LS_NAME"));
        }
        return true;
    }
    
}
