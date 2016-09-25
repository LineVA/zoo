package backup.load;

import java.io.IOException;
import org.jdom2.JDOMException;
import zoo.IZoo;

/**
 * Interface to load a zoo
 * @author doyenm
 */
public interface Load {
    /**
     * Load a file and charge the zoo describing in
     * @param fileName the name of the file to load
     * @return the zoo describing in the file
     * @throws IOException 
     * @throws JDOMException when the XMl is incorrect
     */
    public IZoo loadZoo(String fileName) throws IOException, JDOMException;
}
