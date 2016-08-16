package backup.load;

import exception.IncorrectDataException;
import exception.IncorrectDimensionsException;
import exception.name.AlreadyUsedNameException;
import exception.name.EmptyNameException;
import exception.name.UnknownNameException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import launch.options.Option;
import org.jdom2.JDOMException;
import zoo.IZoo;
import zoo.animal.FakeAnimal;
import zoo.animal.reproduction.Sex;
import zoo.animal.specie.Specie;
import zoo.paddock.FakePaddock;
import zoo.paddock.IPaddock;

/**
 *
 * @author doyenm
 */
public class LoadImpl implements Load {

    @Override
    public IZoo loadZoo(String fileName) throws IOException, JDOMException {
        File file = new File(fileName);
        ParserBackUp parser = new ParserBackUp(file);
        // Creation of the zoo
        IZoo zoo = parser.parserZoo().convertToZoo();
        zoo.setOption(new Option(parser.parserLanguage()));
        // Creation of the paddocks
        ArrayList<FakePaddock> padList = parser.parserPaddocks();
        for (FakePaddock pad : padList) {
            addFakePaddockToZoo(zoo, pad);
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

    public void addFakePaddockToZoo(IZoo zoo, FakePaddock paddock)
            throws IncorrectDimensionsException, AlreadyUsedNameException, EmptyNameException{
        zoo.addPaddock(paddock.convertToPaddock());
    }
}


