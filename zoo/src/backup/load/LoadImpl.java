package backup.load;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom2.JDOMException;
import zoo.IZoo;
import zoo.Zoo;
import zoo.paddock.IPaddock;

/**
 *
 * @author doyenm
 */
public class LoadImpl implements Load {

    @Override
    public IZoo loadZoo(String fileName) {
        IZoo zoo = new Zoo();
        try {
            File file = new File("gameBackUps/test2.xml");
            ParserBackUp parser = new ParserBackUp(file);
            // Creation of the zoo
            zoo = parser.parserZoo();
            // Creation of the paddocks
            ArrayList<IPaddock> padList = parser.parserPaddock();
            for (IPaddock pad : padList) {
                zoo.addPaddock(pad);
            }
            // Creation of the animals
            
            return zoo;
        } catch (IOException ex) {
            Logger.getLogger(Zoo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JDOMException ex) {
            Logger.getLogger(Zoo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return zoo;
    }
}
