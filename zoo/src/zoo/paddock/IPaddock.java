package zoo.paddock;

import exception.IncorrectDataException;
import exception.name.AlreadyUsedNameException;
import exception.name.UnknownNameException;
import java.util.ArrayList;
import java.util.HashMap;
import backup.save.SaveImpl;
import zoo.animal.Animal;
import zoo.animal.specie.Specie;

/**
 *
 * @author doyenm
 */
public interface IPaddock {

    public void instanciatePaddock();

    public void setBiome(String biomeName) throws UnknownNameException;

    public void addAnimal(Animal animal) throws AlreadyUsedNameException;

    public ArrayList<String> info();

    public Animal findAnimalByName(String animalName) throws UnknownNameException;

    public ArrayList<String> listAnimal();

    public void birth() throws IncorrectDataException;

    public void ageing(int monthsPerEvaluation);

    public void death();

    public ArrayList<Animal> animalsOfTheSameSpecie(Specie specie);

    public int countAnimalsOfTheSameSpecie(Specie specie);

    public int wellBeing();

    public int computeSize();

    public boolean isNotCompetingForSpace(PaddockCoordinates coordinates);

    public PaddockCoordinates getCoordinates();

    public int countNonMatureAnimals();

    public ArrayList<String> countSpecies(ArrayList<String> presentedSpecies);

    public String getName();

    /**
     * Friend pattern : give access to each of the fields of Zoo only to the
     * save methods
     */
    
    public String getName(SaveImpl.FriendSave friend);

    public HashMap<String, Animal> getAnimals(SaveImpl.FriendSave friend);

}
