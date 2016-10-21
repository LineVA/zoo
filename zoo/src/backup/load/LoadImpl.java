package backup.load;

import exception.IncorrectDataException;
import exception.IncorrectDimensionsException;
import exception.IncorrectLoadException;
import exception.name.AlreadyUsedNameException;
import exception.name.EmptyNameException;
import exception.name.NameException;
import exception.name.UnknownNameException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import launch.options.Option;
import org.jdom2.JDOMException;
import zoo.IZoo;
import zoo.animal.FakeAnimal;
import zoo.animal.feeding.Diet;
import zoo.animal.reproduction.Sex;
import zoo.animal.specie.Family;
import zoo.animal.specie.Specie;
import zoo.animalKeeper.FakeAnimalKeeper;
import zoo.animalKeeper.FakeTaskPaddock;
import zoo.animalKeeper.Task;
import zoo.animalKeeper.TaskPaddock;
import zoo.paddock.FakePaddock;
import zoo.paddock.IPaddock;
import zoo.paddock.PaddockTypes;
import zoo.paddock.biome.Biome;

/**
 * The concrete class unsed to load a zoo
 *
 * @author doyenm
 */
public class LoadImpl implements Load {

    /**
     * The main method
     *
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
        for (FakeAnimal animal : animalList) {
            addFakeAnimalToZoo(zoo, animal, option);
        }
        // Creation of the animal keepers
        ArrayList<FakeAnimalKeeper> keeperList = parser.parserAnimalKeepers();
        for (FakeAnimalKeeper keeper : keeperList) {
            addFakeKeeperToZoo(zoo, keeper, option);
        }
        return zoo;
    }

    private void addFakeAnimalToZoo(IZoo zoo, FakeAnimal animal, Option option)
            throws EmptyNameException, UnknownNameException, IncorrectDataException,
            AlreadyUsedNameException, NameException {
        Specie spec = zoo.findSpecieByScientificName(animal.getSpecie());
        IPaddock pad = zoo.findPaddockByName(animal.getPaddock());
        Sex sex = Sex.UNKNOWN.findById(animal.getSex());
        Diet diet = Diet.NONE.findDietById(animal.getDiet());
        pad.addAnimal(animal.convertToAnimal(spec, pad, sex, option));
    }

    private void addFakePaddockToZoo(IZoo zoo, FakePaddock paddock, Option option)
            throws IncorrectDimensionsException,
            AlreadyUsedNameException, EmptyNameException, NameException {
       Biome.NONE.findById(paddock.getBiome());
       PaddockTypes.UNKNOWN.findById(paddock.getPaddockType());
        zoo.addPaddock(paddock.convertToPaddock(option));
    }

    private void addFakeKeeperToZoo(IZoo zoo, FakeAnimalKeeper keeper, Option option)
            throws EmptyNameException, UnknownNameException, NameException, IncorrectLoadException {
        this.verifyManagedFamilies(keeper.getManagedFamilies());
        this.verifyManagedTasks(keeper.getManagedTasks());
        zoo.addKeeper(keeper.convertToAnimalKeeper(
                verifyPaddock(zoo, keeper.getTimedPaddocks()),
                verifyTaskPaddock(zoo, keeper.getTimedTasksPerPaddock(), keeper.getTimedPaddocks()), option));
    }

    private Map<IPaddock, Double> verifyPaddock(IZoo zoo, Map<String, Double> timedPaddocks)
            throws EmptyNameException, UnknownNameException {
        HashMap<IPaddock, Double> realMap = new HashMap<>();
        for (HashMap.Entry<String, Double> entry : timedPaddocks.entrySet()) {
            IPaddock pad = zoo.findPaddockByName(entry.getKey());
            realMap.put(pad, entry.getValue());
        }
        return realMap;
    }

    private Map<TaskPaddock, Double> verifyTaskPaddock(
            IZoo zoo, Map<FakeTaskPaddock, Double> timedTasksPerPaddock,
            Map<String, Double> timedPaddocks)
            throws UnknownNameException, EmptyNameException, IncorrectLoadException {
        HashMap<TaskPaddock, Double> realMap = new HashMap<>();
        for (HashMap.Entry<FakeTaskPaddock, Double> entry : timedTasksPerPaddock.entrySet()) {
            IPaddock pad = zoo.findPaddockByName(entry.getKey().getPaddock());
            Task.UNKNOWN.findById(entry.getKey().getTask());
             if(!timedPaddocks.containsKey(pad.getName())){
                throw new IncorrectLoadException("");
            }
            realMap.put(new TaskPaddock(pad, entry.getKey().getTask()), entry.getValue());
        }
        return realMap;
    }
    
    private void verifyManagedFamilies(Map<Integer, Double> managed)
            throws EmptyNameException, UnknownNameException {
         for (HashMap.Entry<Integer, Double> entry : managed.entrySet()) {
             Family.UNKNOWN.findById(entry.getKey());
        }
    }
    
     private void verifyManagedTasks(Map<Integer, Double> managed)
            throws EmptyNameException, UnknownNameException {
         for (HashMap.Entry<Integer, Double> entry : managed.entrySet()) {
             Task.UNKNOWN.findById(entry.getKey());
        }
    }
}
