package zoo;

import exception.IncorrectDataException;
import exception.IncorrectDimensionsException;
import exception.name.AlreadyUsedNameException;
import exception.name.EmptyNameException;
import exception.name.UnknownNameException;
import java.io.IOException;
import java.util.ArrayList;
import backup.save.SaveImpl;
import java.util.Map;
import launch.ScenarioPlayImpl;
import zoo.animal.Animal;
import zoo.animal.specie.Specie;
import zoo.paddock.IPaddock;
import zoo.paddock.PaddockCoordinates;

/**
 *
 * @author doyenm
 */
public interface IZoo {

    public void initiateZoo(String name, int width, int height, Map<String, Specie> species, int age, int monthsPerEvaluation, int horizon)
            throws IncorrectDimensionsException, EmptyNameException, IOException;

    public void addPaddock(String paddockName, int x, int y, int width, int height)
            throws AlreadyUsedNameException, IncorrectDimensionsException, EmptyNameException;

    public void addPaddock(IPaddock paddock)
            throws AlreadyUsedNameException, IncorrectDimensionsException;

    public void removePaddock(IPaddock paddock);

    public ArrayList<String> listPaddock(Specie specie);

    public ArrayList<PaddockCoordinates> map() throws IncorrectDimensionsException;

    public int evaluate();

//    public ArrayList<String> death() throws UnknownNameException;

//    public ArrayList<String> birth()
//            throws AlreadyUsedNameException, IncorrectDataException, EmptyNameException;

    public IPaddock findPaddockByName(String paddockName) throws EmptyNameException, UnknownNameException;

    public Specie findSpeciebyName(String specieName) throws EmptyNameException, UnknownNameException;
//    public void setBiome(String paddockName, String biomeName)
//            throws UnknownNameException, EmptyNameException;

    public Animal findAnimalByName(String animalName) throws UnknownNameException, EmptyNameException;

    public ArrayList<Animal> listAnimal(Specie specie, IPaddock paddock);

    public ArrayList<String> listSpecie(IPaddock paddock);

    public ArrayList<String> ageing() throws IncorrectDataException, EmptyNameException;

    public double grade() throws UnknownNameException;

    public ArrayList<String> info();
    
  /**
     * Friend pattern : give access to each of the fields of Zoo only to the
     * tutorial methods
     */
    public String getName(ScenarioPlayImpl.FriendScenario friend);

    public Map<String, IPaddock> getPaddocks(ScenarioPlayImpl.FriendScenario friend);
    
     public ArrayList<Animal> getAnimals(ScenarioPlayImpl.FriendScenario friend);

    /**
     * Friend pattern : give access to each of the fields of Zoo only to the
     * save methods
     */
    public String getName(SaveImpl.FriendSave friend);

    public int getWidth(SaveImpl.FriendSave friend);

    public int getHeight(SaveImpl.FriendSave friend);

    public Map<String, IPaddock> getPaddocks(SaveImpl.FriendSave friend);

    public Map<String, Specie> getSpecies(SaveImpl.FriendSave friend);

    public int getMonthsPerEvaluation(SaveImpl.FriendSave friend);

    public int getAge(SaveImpl.FriendSave friend);

    public int getHorizon(SaveImpl.FriendSave friend);
}
