package backup.load;

import exception.IncorrectDataException;
import exception.IncorrectDimensionsException;
import exception.name.AlreadyUsedNameException;
import exception.name.EmptyNameException;
import exception.name.NameException;
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
 * The concrete class unsed to load a zoo
 * @author doyenm
 */
public class LoadImpl implements Load {

    /**
     * The main method
     * @param fileName
     * @return
     * @throws IOException
     * @throws JDOMException 
     */
    @Override
    public IZoo loadZoo(String fileName) throws IOException, JDOMException {
        File file = new File(fileName);
        ParserBackUp parser = new ParserBackUp(file);
        // Creation of the zoo
        Option option = new Option(parser.parserLanguage());
        IZoo zoo = parser.parserZoo().convertToZoo(option);
        // Creation of the paddocks
        ArrayList<FakePaddock> padList = parser.parserPaddocks();
        for (FakePaddock pad : padList) {
            addFakePaddockToZoo(zoo, pad, option);
        }
        // Creation of the animals
        ArrayList<FakeAnimal> animalList = parser.parserAnimals();
        IPaddock pad;
        for (FakeAnimal animal : animalList) {
            addFakeAnimalToZoo(zoo, animal, option);
        }
        return zoo;
    }

    private void addFakeAnimalToZoo(IZoo zoo, FakeAnimal animal, Option option)
            throws EmptyNameException, UnknownNameException, IncorrectDataException,
            AlreadyUsedNameException, NameException {
        Specie spec = zoo.findSpecieByScientificName(animal.getSpecie());
        IPaddock pad = zoo.findPaddockByName(animal.getPaddock());
        Sex sex = Sex.UNKNOWN.findById(animal.getSex());
        pad.addAnimal(animal.convertToAnimal(spec, pad, sex, option));
    }

    private void addFakePaddockToZoo(IZoo zoo, FakePaddock paddock, Option option)
            throws IncorrectDimensionsException,
            AlreadyUsedNameException, EmptyNameException, NameException {
        zoo.addPaddock(paddock.convertToPaddock(option));
    }
}
