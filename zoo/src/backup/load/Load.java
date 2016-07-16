package backup.load;

import java.io.IOException;
import org.jdom2.JDOMException;
import zoo.IZoo;

/**
 *
 * @author doyenm
 */
public interface Load {
    public IZoo loadZoo(String fileName) throws IOException, JDOMException;
}
