package zoo.paddock;

import exception.IncorrectDataException;
import exception.name.AlreadyUsedNameException;
import exception.name.UnknownNameException;
import java.util.ArrayList;
import backup.save.SaveImpl;
import exception.name.EmptyNameException;
import java.util.Map;
import launch.options.Option;
import zoo.animal.Animal;
import zoo.animal.specie.Specie;

/**
 *
 * @author doyenm
 */
public interface IPaddock {

    public void setOption(Option option);

    public void instanciatePaddock();

    public void setBiome(String biomeName) throws UnknownNameException;

    public void addAnimal(Animal animal) throws AlreadyUsedNameException;

    public void removeAnimal(Animal animal);

    public ArrayList<String> info();

    public Animal findAnimalByName(String animalName)
            throws UnknownNameException, EmptyNameException;

    public ArrayList<String> listAnimal(Specie specie);

    public ArrayList<String> birth()
            throws IncorrectDataException, EmptyNameException;

    public void ageing(int monthsPerEvaluation);

    public ArrayList<String> death();

    public ArrayList<Animal> animalsOfTheSameSpecie(Specie specie);

    public int countAnimalsOfTheSameSpecie(Specie specie);

    public double wellBeing() throws UnknownNameException;

    public int computeSize();

    public boolean isNotCompetingForSpace(PaddockCoordinates coordinates);

    public PaddockCoordinates getCoordinates();

    public int countNonMatureAnimals();

    public ArrayList<String> listSpeciesByName(ArrayList<String> presentedSpecies);

    public ArrayList<String> listSpeciesByName();

    public ArrayList<Specie> listSpecies(ArrayList<Specie> presentedSpecies);

    public ArrayList<Specie> listSpecies();

    public ArrayList<Specie> listSpeciesInNeightbourhood();

    public String getName();

    public void addInNeightbourhood(IPaddock paddock);

    public void addAllInNeightbourhood(ArrayList<IPaddock> neightbourhood);

    public void removeFromNeightbourhood();

    public void removeANeightbour(IPaddock paddock);

    /**
     * Friend pattern : give access to each of the fields of Zoo only to the
     * save methods
     */
    public String getName(SaveImpl.FriendSave friend);

    public Map<String, Animal> getAnimals(SaveImpl.FriendSave friend);

    public PaddockCoordinates getCoordinates(SaveImpl.FriendSave friend);

}
