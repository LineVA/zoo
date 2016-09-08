package zoo;

import exception.IncorrectDataException;
import exception.IncorrectDimensionsException;
import exception.name.AlreadyUsedNameException;
import exception.name.EmptyNameException;
import exception.name.UnknownNameException;
import java.io.IOException;
import java.util.ArrayList;
import backup.save.SaveImpl;
import exception.name.NameException;
import java.util.Map;
import launch.options.Option;
import launch.play.tutorials.TutorialPlayImpl_1;
import zoo.animal.Animal;
import zoo.animal.specie.Specie;
import zoo.paddock.IPaddock;
import zoo.paddock.PaddockCoordinates;

/**
 *
 * @author doyenm
 */
public interface IZoo {

    public void setSpecies(Map<String, Specie> species);

    public void initiateZoo(String name, int width, int height,
            Map<String, Specie> species, int age, int monthsPerEvaluation, int horizon)
            throws IncorrectDimensionsException, EmptyNameException, IOException;

    public void setOption(Option option);

    public Option getOption();

    public void addPaddock(String paddockName, int x, int y, int width, int height)
            throws AlreadyUsedNameException, IncorrectDimensionsException,
            EmptyNameException, NameException;

    public void addPaddock(IPaddock paddock)
            throws AlreadyUsedNameException, IncorrectDimensionsException;

    public void removePaddock(IPaddock paddock);

    public ArrayList<String> listPaddock(Specie specie);

    public ArrayList<PaddockCoordinates> map() throws IncorrectDimensionsException;

    public int evaluate();

//    public ArrayList<String> death() throws UnknownNameException;
    public ArrayList<String> birth() throws AlreadyUsedNameException, IncorrectDataException, NameException;

    public IPaddock findPaddockByName(String paddockName) throws EmptyNameException, UnknownNameException;

    public Specie findSpeciebyName(String specieName) throws EmptyNameException, UnknownNameException;
//    public void setBiome(String paddockName, String biomeName)
//            throws UnknownNameException, EmptyNameException;

    public Animal findAnimalByName(String animalName) throws UnknownNameException, EmptyNameException;

    public ArrayList<Animal> listAnimal(Specie specie, IPaddock paddock);

    public ArrayList<String> listSpecie(IPaddock paddock);

    public ArrayList<String> ageing() throws IncorrectDataException, NameException;

    public double grade() throws UnknownNameException;

    public ArrayList<String> info();

    public void changeSpeed(int newSpeed) throws IncorrectDataException;

    public void changeHorizon(int newHorizon) throws IncorrectDataException;

    /**
     * Friend pattern : give access to each of the fields of Zoo only to the
     * tutorial methods
     */
    public String getName(TutorialPlayImpl_1.FriendScenario friend);

    public Map<String, IPaddock> getPaddocks(TutorialPlayImpl_1.FriendScenario friend);

    public ArrayList<Animal> getAnimals(TutorialPlayImpl_1.FriendScenario friend);

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

    public Option getOption(SaveImpl.FriendSave friend);

}
