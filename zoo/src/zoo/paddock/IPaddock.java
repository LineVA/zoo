package zoo.paddock;

import exception.IncorrectDataException;
import exception.name.AlreadyUsedNameException;
import exception.name.UnknownNameException;
import backup.save.SaveImpl;
import exception.IncorrectLoadException;
import exception.name.EmptyNameException;
import exception.name.NameException;
import java.util.List;
import java.util.Map;
import launch.options.Option;
import zoo.animal.Animal;
import zoo.animal.LightAnimal;
import zoo.animal.specie.LightSpecie;
import zoo.animal.specie.Specie;
import zoo.animalKeeper.AnimalKeeper;

/**
 *
 * @author doyenm
 */
public interface IPaddock {
    
    public boolean compareTo(LightPaddock lightPaddock);

    public void setBiome(String biomeName) throws UnknownNameException;

    public void setPaddockType(String paddockTypeId) throws UnknownNameException;

    public void addAnimal(Animal animal) throws AlreadyUsedNameException;

    public void removeAnimal(Animal animal);

    public List<String> info() throws UnknownNameException;

    public Animal findAnimalByName(String animalName)
            throws UnknownNameException, EmptyNameException;

    public List<Animal> listAnimal(LightSpecie specie, LightAnimal animal)
            throws UnknownNameException;

    public List<String> birth(int monthsPerEvaluation)
            throws IncorrectDataException, NameException, IncorrectLoadException;

    public void ageing(int monthsPerEvaluation);

    public List<String> death();

    public List<Animal> animalsOfTheSameSpecie(Specie specie);

    public int countAnimalsOfTheSameSpecie(Specie specie);

    public double wellBeing(List<AnimalKeeper> keepers) throws UnknownNameException;

    public int computeSize();

    public boolean isNotCompetingForSpace(PaddockCoordinates coordinates);

    public PaddockCoordinates getCoordinates();

    public int countNonMatureAnimals();

    public List<String> listSpeciesByName(List<String> presentedSpecies);

    public List<String> listSpeciesByName();

    public List<Specie> listSpecies(List<Specie> presentedSpecies);

    public List<Specie> listSpecies();
    
    /**
     * Can contain doubles if two or more species of the same family
     * are in this paddock
     * @return 
     */
    public List<Integer> listFamiliesById();

    public List<Specie> listSpeciesInNeightbourhood();

    public String getName();

    public void addInNeightbourhood(IPaddock paddock);

    public void addAllInNeightbourhood(List<IPaddock> neightbourhood);

    public void removeFromNeightbourhood();

    public void removeANeightbour(IPaddock paddock);

    public Option getOption();

    public int getBiome();
    
    public int getPaddockType();

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
