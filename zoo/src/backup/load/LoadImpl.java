package backup.load;

import exception.IncorrectDataException;
import exception.IncorrectDimensionsException;
import exception.IncorrectLoadException;
import exception.name.AlreadyUsedNameException;
import exception.name.EmptyNameException;
import exception.name.NameException;
import exception.name.UnauthorizedNameException;
import exception.name.UnknownNameException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
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
     * @see backup.load.Load
     */
    @Override
    public IZoo loadZoo(String fileName) throws IOException, JDOMException {
        File file = new File(fileName);
        ParserBackUp parser = new ParserBackUp(file);
        // Creation of the zoo
        Option option = new Option(parser.parserLanguage());
        IZoo zoo = parser.parserZoo().convertToZoo(option);
        // Creation of the paddocks
        List<FakePaddock> padList = parser.parserPaddocks();
        for (FakePaddock pad : padList) {
            addFakePaddockToZoo(zoo, pad, option);
        }
        // Creation of the animals
        List<FakeAnimal> animalList = parser.parserAnimals();
        for (FakeAnimal animal : animalList) {
            addFakeAnimalToZoo(zoo, animal, option);
        }
        // Creation of the animal keepers
        List<FakeAnimalKeeper> keeperList = parser.parserAnimalKeepers();
        for (FakeAnimalKeeper keeper : keeperList) {
            addFakeKeeperToZoo(zoo, keeper, option);
        }
        return zoo;
    }

    /**
     * Add a real animal to the zoo according to the value of a fake animal
     * @param zoo the zoo in which we add the animal
     * @param animal the fake animal to add
     * @param option the option of the animal
     * @throws EmptyNameException if a fake animal has an empty name
     * @throws UnknownNameException  if a fake animal has an unknown specie or paddock
     * @throws IncorrectDataException if the age is lower than zero
     * @throws AlreadyUsedNameException if an animal is already called by the name of the fake animal
     */
    private void addFakeAnimalToZoo(IZoo zoo, FakeAnimal animal, Option option)
            throws EmptyNameException, UnknownNameException, IncorrectDataException,
            AlreadyUsedNameException, UnauthorizedNameException{
        Specie spec = zoo.findSpecieByScientificName(animal.getSpecie());
        IPaddock pad = zoo.findPaddockByName(animal.getPaddock());
        Sex sex = Sex.UNKNOWN.findById(animal.getSex());
        Diet diet = Diet.NONE.findById(animal.getDiet());
        pad.addAnimal(animal.convertToAnimal(spec, pad, sex, option));
    }
    
    /**
     * Add a real paddock to the zoo according to the value of a fake paddock
     * @param zoo the zoo in which we add the paddock
     * @param paddock the fake paddock to add
     * @param option the option of the paddock
     * @throws IncorrectDimensionsException
     * @throws AlreadyUsedNameException if a paddock is already called by the name of the fake paddock
     * @throws EmptyNameException if the fake paddock has an empty name
     * @throws NameException 
     */
    private void addFakePaddockToZoo(IZoo zoo, FakePaddock paddock, Option option)
            throws IncorrectDimensionsException, UnknownNameException,
            AlreadyUsedNameException, EmptyNameException, UnauthorizedNameException{
       Biome.NONE.findById(paddock.getBiome());
       PaddockTypes.UNKNOWN.findById(paddock.getPaddockType());
        zoo.addPaddock(paddock.convertToPaddock(option));
    }

    /**
     * Add a real animal keeper to the zoo according to the value of a fake animal keeper
     * @param zoo the zoo in which we add the animal keeper
     * @param keeper the fake animal keeper to add
     * @param option the options of the animal keeper
     * @throws EmptyNameException 
     * @throws UnknownNameException
     * @throws NameException
     * @throws IncorrectLoadException 
     */
    private void addFakeKeeperToZoo(IZoo zoo, FakeAnimalKeeper keeper, Option option)
            throws EmptyNameException, UnknownNameException, IncorrectLoadException, 
            AlreadyUsedNameException, UnauthorizedNameException {
        this.verifyManagedFamilies(keeper.getManagedFamilies());
        this.verifyManagedTasks(keeper.getManagedTasks());
        zoo.addKeeper(keeper.convertToAnimalKeeper(
                verifyPaddockForKeeper(zoo, keeper.getTimedPaddocks()),
                verifyTaskPaddockForKeeper(zoo, keeper.getTimedTasksPerPaddock(), keeper.getTimedPaddocks()), option));
    }

    /**
     * Verify the acceptability of the existence of the paddocks according to their names :
     * @param zoo the zoo in chich we work
     * @param timedPaddocks the set of paddocks to check
     * @return a map of real timed paddocks
     * @throws EmptyNameException if a member of the map has an empty name
     * @throws UnknownNameException if a member of the map has an unknown name in the zoo
     */
    private Map<IPaddock, Double> verifyPaddockForKeeper(IZoo zoo, Map<String, Double> timedPaddocks)
            throws EmptyNameException, UnknownNameException {
        Map<IPaddock, Double> realMap = new HashMap<>();
        for (Map.Entry<String, Double> entry : timedPaddocks.entrySet()) {
            IPaddock pad = zoo.findPaddockByName(entry.getKey());
            realMap.put(pad, entry.getValue());
        }
        return realMap;
    }

    /**
     * Verify the acceptability of the timed tasks per paddock for a keeper
     * @param zoo the zoo in which we work
     * @param timedTasksPerPaddock the map of timed tasks per paddock to check
     * @param timedPaddocks the already existing timed paddocks (identified by String)
     * @return a map we the real timed tasks per paddock
     * @throws UnknownNameException if a paddock or a task has an unknown name for this zoo
     * @throws EmptyNameException if a paddock has an empty name
     * @throws IncorrectLoadException if a timed task  refers to an unknown paddock
     */
    private Map<TaskPaddock, Double> verifyTaskPaddockForKeeper(
            IZoo zoo, Map<FakeTaskPaddock, Double> timedTasksPerPaddock,
            Map<String, Double> timedPaddocks)
            throws UnknownNameException, EmptyNameException, IncorrectLoadException {
        Map<TaskPaddock, Double> realMap = new HashMap<>();
        for (Map.Entry<FakeTaskPaddock, Double> entry : timedTasksPerPaddock.entrySet()) {
            IPaddock pad = zoo.findPaddockByName(entry.getKey().getPaddock());
            Task.UNKNOWN.findById(entry.getKey().getTask());
             if(!timedPaddocks.containsKey(pad.getName())){
                throw new IncorrectLoadException("");
            }
            realMap.put(new TaskPaddock(pad, entry.getKey().getTask()), entry.getValue());
        }
        return realMap;
    }
    
    /**
     * Verify the aceptability of the managed families for a keeper
     * @param managed the map of managed families to check 
     * @throws UnknownNameException if a family does not correspond to a real family
     */
    private void verifyManagedFamilies(Map<Integer, Double> managed)
            throws UnknownNameException {
         for (Map.Entry<Integer, Double> entry : managed.entrySet()) {
             Family.UNKNOWN.findById(entry.getKey());
        }
    }
    
        /**
     * Verify the aceptability of the managed tasks for a keeper
     * @param managed the map of managed tasks to check 
     * @throws UnknownNameException if a task does not correspond to a real task
     */
     private void verifyManagedTasks(Map<Integer, Double> managed)
            throws UnknownNameException {
         for (Map.Entry<Integer, Double> entry : managed.entrySet()) {
             Task.UNKNOWN.findById(entry.getKey());
        }
    }
}
