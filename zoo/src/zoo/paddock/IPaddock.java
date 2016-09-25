package zoo.paddock;

import exception.IncorrectDataException;
import exception.name.AlreadyUsedNameException;
import exception.name.UnknownNameException;
import java.util.ArrayList;
import backup.save.SaveImpl;
import exception.name.EmptyNameException;
import exception.name.NameException;
import java.util.Map;
import launch.options.Option;
import zoo.animal.Animal;
import zoo.animal.feeding.Diet;
import zoo.animal.reproduction.Sex;
import zoo.animal.specie.LightSpecie;
import zoo.animal.specie.Specie;
import zoo.paddock.biome.Biome;

/**
 *
 * @author doyenm
 */
public interface IPaddock {

    public void setBiome(String biomeName) throws UnknownNameException;

    public void setPaddockType(String paddockTypeId) throws UnknownNameException;

    public void addAnimal(Animal animal) throws AlreadyUsedNameException;

    public void removeAnimal(Animal animal);

    public ArrayList<String> info() throws UnknownNameException;

    public Animal findAnimalByName(String animalName)
            throws UnknownNameException, EmptyNameException;

    public ArrayList<Animal> listAnimal(LightSpecie specie, Sex sex, Diet diet, Biome biome)
            throws UnknownNameException;

    public ArrayList<String> birth()
            throws IncorrectDataException, NameException;

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

    public Option getOption();

    public int getBiome();

    /**
     * Friend pattern : give access to each of the fields of Zoo only to the
     * save methods
     */
    public String getName(SaveImpl.FriendSave friend);

    public Map<String, Animal> getAnimals(SaveImpl.FriendSave friend);

    public PaddockCoordinates getCoordinates(SaveImpl.FriendSave friend);

    public int getBiome(SaveImpl.FriendSave friend);

    public int getPaddockType(SaveImpl.FriendSave friend);

}
