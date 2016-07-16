package backup.load;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom2.JDOMException;
import zoo.IZoo;
import zoo.Zoo;
import zoo.animal.Animal;
import zoo.paddock.IPaddock;

/**
 *
 * @author doyenm
 */
public class LoadImpl implements Load {

    @Override
    public IZoo loadZoo(String fileName) throws IOException, JDOMException {
        IZoo zoo = new Zoo();
        File file = new File(fileName);
        ParserBackUp parser = new ParserBackUp(file);
        // Creation of the zoo
        zoo = parser.parserZoo();
        // Creation of the paddocks
        ArrayList<IPaddock> padList = parser.parserPaddock();
        for (IPaddock pad : padList) {
            zoo.addPaddock(pad);
        }
        // Creation of the animals
        ArrayList<Animal> animalList = parser.parserAnimal(zoo.getSpecies(), zoo.getPaddocks());
        IPaddock pad;
        for (Animal animal : animalList) {
            pad = zoo.findPaddockByName(animal.getPaddock().getName());
            pad.addAnimal(animal);
        }
        return zoo;
    }
}
