package backup.load;

import exception.IncorrectDataException;
import exception.name.AlreadyUsedNameException;
import exception.name.EmptyNameException;
import exception.name.UnknownNameException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import org.jdom2.JDOMException;
import zoo.IZoo;
import zoo.Zoo;
import zoo.animal.FakeAnimal;
import zoo.animal.reproduction.Sex;
import zoo.animal.specie.Specie;
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
        ArrayList<IPaddock> padList = parser.parserPaddocks();
        for (IPaddock pad : padList) {
            zoo.addPaddock(pad);
        }
        // Creation of the animals
        ArrayList<FakeAnimal> animalList = parser.parserAnimals();
        IPaddock pad;
        for (FakeAnimal animal : animalList) {
            addFakeAnimalToZoo(zoo, animal);
        }
        return zoo;
    }
    
    public void addFakeAnimalToZoo(IZoo zoo, FakeAnimal animal)
            throws EmptyNameException, UnknownNameException, IncorrectDataException,
                AlreadyUsedNameException {
            Specie spec = zoo.findSpeciebyName(animal.getSpecie());
            IPaddock pad = zoo.findPaddockByName(animal.getPaddock());
            Sex sex = Sex.FEMALE.findByName(animal.getSex());
            pad.addAnimal(animal.convertToAnimal(spec, pad, sex));
    }

  
}


