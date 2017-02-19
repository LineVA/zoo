package zoo.paddock;

import exception.IncorrectDataException;
import zoo.paddock.biome.BiomeAttributes;
import zoo.paddock.biome.Biome;
import exception.name.AlreadyUsedNameException;
import exception.name.UnknownNameException;
import java.util.ArrayList;
import java.util.Iterator;
import lombok.Getter;
import backup.save.SaveImpl;
import exception.IncorrectLoadException;
import exception.name.EmptyNameException;
import exception.name.NameException;
import exception.name.UnauthorizedNameException;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import launch.options.Option;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import zoo.BirthObservable;
import zoo.NameVerifications;
import zoo.animal.Animal;
import zoo.animal.AnimalImpl;
import zoo.animal.LightAnimal;
import zoo.animal.death.DieImpl;
import zoo.animal.death.IDie;
import zoo.animal.feeding.Diet;
import zoo.animal.reproduction.Reproduction;
import zoo.animal.reproduction.ReproductionImpl;
import zoo.animal.reproduction.Sex;
import zoo.animal.specie.LightSpecie;
import zoo.animal.specie.Specie;
import zoo.animalKeeper.AnimalKeeper;

/**
 *
 * @author doyenm
 */
public class Paddock implements IPaddock {

    @Getter
    private Option option;
    /**
     * The name of the paddock
     */
    private String name;
    /**
     * Its coordinates
     */
    private PaddockCoordinates coordinates;
    /**
     * Its biome's id
     */
    @Getter
    int biome;
    @Getter
    BiomeAttributes attributes;
    @Getter
    Map<String, Animal> animals;
    @Getter
    private int paddockType;
    private List<IPaddock> neightbourhood;
    private BirthObservable obs = new BirthObservable();

    /**
     * The main constructor of the class Because no biome is known, the fields
     * take the values of the ones forme Biome.NONE
     *
     * @param name the name of the paddock
     * @param coor the coordinates of the paddock
     */
    public Paddock(String name, PaddockCoordinates coor,
            List<IPaddock> neightbourhood, Map<String, Animal> animals, int biome, int paddockType, Option option)
            throws EmptyNameException, UnknownNameException, UnauthorizedNameException {
        this.option = option;
        NameVerifications.verify(name, this.option.getPaddockBundle());
        this.name = name;
        this.coordinates = coor;
        this.biome = biome;
//        this.attributes = (BiomeAttributes) Biome.NONE.getAttributes().clone();
        this.animals = animals;
        this.neightbourhood = neightbourhood;
        this.paddockType = paddockType;
        obs.addObserver(new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                setNewComerName((String) arg);
            }
        });
    }

    private void setPaddockType(PaddockTypes paddockTypes) {
        if (paddockTypes != null) {
            this.paddockType = paddockTypes.getId();
        } else {
            setPaddockType(paddockTypes.UNKNOWN);
        }
    }

    @Override
    public void setPaddockType(String paddockTypeId) throws UnknownNameException {
        try {
            setPaddockType(PaddockTypes.UNKNOWN.findById(Integer.parseInt(paddockTypeId)));
        } catch (java.lang.NumberFormatException ex) {
            setPaddockType(PaddockTypes.UNKNOWN.findByNameAccordingToLanguage(paddockTypeId));
        }
    }

    private void setBiome(Biome biome) {
        if (biome != null) {
            this.biome = biome.getId();
//            this.attributes = (BiomeAttributes) biome.getAttributes().clone();
        } else {
            setBiome(Biome.NONE);
        }
    }

    @Override
    public void setBiome(String biomeName) throws UnknownNameException {
        try {
            setBiome(Biome.NONE.findById(Integer.parseInt(biomeName)));
        } catch (java.lang.NumberFormatException ex) {
            setBiome(Biome.NONE.findByNameAccordingToLanguage(biomeName));
        }
    }

    @Override
    public void addAnimal(Animal animal) throws AlreadyUsedNameException {
        Animal success = this.animals.putIfAbsent(animal.getName(), animal);
        if (success != null) {
            throw new AlreadyUsedNameException(
                    this.option.getPaddockBundle().getString("ALREADY_USED_NAME"));
        }
    }

    public void removeAnimal(String name) throws UnknownNameException {
        this.animals.remove(name);
    }

    @Override
    public List<String> info() throws UnknownNameException {
        ResourceBundle bundle = this.option.getPaddockBundle();
        List<String> info = new ArrayList<>();
        info.add(bundle.getString("NAME") + this.name);
        info.add(bundle.getString("COORDINATES") + this.coordinates.toStringByLanguage(bundle));
        info.add(bundle.getString("NEIGHTBOURS") + this.listNeightbourhood());
        info.add(bundle.getString("TYPE") + PaddockTypes.UNKNOWN.findById(this.paddockType).toStringByLanguage());
        info.add(bundle.getString("BIOME") + Biome.NONE.findById(this.biome).toStringByLanguage());
//        info.add(bundle.getString("BIOMES_CHARACTERISTICS") + this.attributes.toString());
        return info;
    }

    private String listNeightbourhood() {
        String neightbours = "";
        neightbours = this.neightbourhood.stream().map(
                (pad) -> pad.getName() + ", ").reduce(neightbours, String::concat);
        return neightbours;
    }

    @Override
    public Animal findAnimalByName(String animalName)
            throws UnknownNameException, EmptyNameException {
        if (name.trim().equals("")) {
            throw new EmptyNameException(this.option.getAnimalBundle().getString("EMPTY_NAME"));
        }
        if (animals.containsKey(animalName)) {
            return animals.get(animalName);
        }
        throw new UnknownNameException(this.option.getAnimalBundle().getString("UNKNOWN_NAME"));
    }

    private List<Animal> listAnimalWithSpecie(List<Animal> animals, LightSpecie specie) {
        List<Animal> list = animals;
        Iterator it = list.iterator();
        Animal next;
        while (it.hasNext()) {
            next = (AnimalImpl) it.next();
            if (!next.isFromTheSameSpecie(specie)) {
                it.remove();
            }
        }
        return list;
    }

    public List<Animal> listAnimalWithoutCriteria() {
        List<Animal> list = new ArrayList<>();
        animals.entrySet().stream().forEach((entry) -> {
            list.add(entry.getValue());
        });
        return list;
    }

    private List<Animal> listAnimalWithLightSpecie(List<Animal> animals, LightSpecie lightSpecie) {
        List<Animal> list = animals;
        Iterator it = list.iterator();
        Animal next;
        while (it.hasNext()) {
            next = (AnimalImpl) it.next();
            if (!(next.getSpecie().compare(lightSpecie, this.option))) {
                it.remove();
            }
        }
        return list;
    }
    
     private List<Animal> listAnimalWithLightAnimal(List<Animal> animals, LightAnimal lightAnimal) {
        List<Animal> list = animals;
        Iterator it = list.iterator();
        Animal next;
        while (it.hasNext()) {
            next = (AnimalImpl) it.next();
            if (!(next.compare(lightAnimal))) {
                it.remove();
            }
        }
        return list;
    }

    @Override
    public List<Animal> listAnimal(LightSpecie specie, LightAnimal animal)
            throws UnknownNameException {
        List<Animal> list = listAnimalWithoutCriteria();
        if (specie.getNames() != null) {
            list = listAnimalWithSpecie(list, specie);
        }
        list = listAnimalWithLightSpecie(list, specie);
        list = listAnimalWithLightAnimal(list, animal);
        return list;
    }

    @Override
    public List<String> birth(int monthsPerEvaluation) throws IncorrectDataException, NameException, IncorrectLoadException {
        List<String> info = new ArrayList<>();
        List<Animal> tmpAnimal = new ArrayList<>();
        Reproduction repro = new ReproductionImpl();
        List<Animal> newFamily;
        for (Map.Entry<String, Animal> animalEntry : this.animals.entrySet()) {
            newFamily = repro.reproducte(animalEntry.getValue(), monthsPerEvaluation);
            // If there is reproduction
            if (newFamily != null) {
                // newFamily[0] = mother;
                // new Family[1] = father;
                for (int i = 2; i < newFamily.size(); i++) {
                    tmpAnimal.add(newFamily.get(i));
                    info.add(this.option.getAnimalBundle().getString("NEWCOMER")
                            + newFamily.get(0).getName() + this.option.getAnimalBundle().getString("AND")
                            + newFamily.get(1).getName()
                            + this.option.getAnimalBundle().getString("CALLED") + newFamily.get(i).getName());
                }
            }
        }
        incomingNewBorn(tmpAnimal);
        return info;
    }

    private Animal specifieNameOfTheNewBorn(Animal newBorn, Animal mother, Animal father)
            throws EmptyNameException, UnauthorizedNameException {
        obs.askAndWait(mother.getName(), father.getName(), newBorn.getSex().toString());
        newBorn.setName(newComerName);
        return newBorn;
    }

    String newComerName = "";

    public void setNewComerName(String name) {
        this.newComerName = name;
    }

    private void incomingNewBorn(List<Animal> tmpAnimal) {
        Iterator it = tmpAnimal.iterator();
        Animal animal;
        while (it.hasNext()) {
            animal = (Animal) it.next();
            this.animals.put(animal.getName(), animal);
        }
    }

    @Override
    public void ageing(int monthsPerEvaluation) {
        this.animals.entrySet().stream().forEach((animalEntry) -> {
            animalEntry.getValue().ageing(monthsPerEvaluation);
        });
    }

    @Override
    public List<String> death() {
        List<String> info = new ArrayList<>();
        IDie die = new DieImpl();
        List<Animal> tmpAnimal = new ArrayList<>();
        Animal dying;
        for (Map.Entry<String, Animal> animalEntry : this.animals.entrySet()) {
            // If the animal is dead
            dying = animalEntry.getValue();
            if (die.isDied(dying)) {
                info.add(this.option.getAnimalBundle().getString("DYING_ANIMAL") + dying.getName());
                tmpAnimal.add(dying);
            }
        }
        leavingNewDead(tmpAnimal);
        return info;
    }

    private void leavingNewDead(List<Animal> tmpAnimal) {
        Iterator it = tmpAnimal.iterator();
        Animal next;
        while (it.hasNext()) {
            next = (Animal) it.next();
            this.removeAnimal(next);
        }
    }

    @Override
    public void removeAnimal(Animal animal) {
        this.animals.remove(animal.getName());
    }

    @Override
    public int countAnimalsOfTheSameSpecie(Specie specie) {
        return animalsOfTheSameSpecie(specie).size();
    }

    @Override
    public double wellBeing(List<AnimalKeeper> keepers) throws UnknownNameException {
        double wB = 0;
        for (Map.Entry<String, Animal> entry : animals.entrySet()) {
            wB += entry.getValue().wellBeing(keepers);
        }
        return wB;
    }

    @Override
    public int computeSize() {
        return this.coordinates.computeSize();
    }

    @Override
    public boolean isNotCompetingForSpace(PaddockCoordinates coordinates) {
        return this.coordinates.isNotCompeting(coordinates);
    }

    @Override
    public int countNonMatureAnimals() {
        int count = 0;
        count = this.animals.entrySet().stream().filter(
                (animalEntry)
                -> (!animalEntry.getValue().isMature())).map((_item) -> 1).reduce(count, Integer::sum);
        return count;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public PaddockCoordinates getCoordinates() {
        return this.coordinates;
    }

    @Override
    public List<Animal> animalsOfTheSameSpecie(Specie specie) {
        List<Animal> sameSpecieAnimals = new ArrayList<>();
        this.animals.entrySet().stream().filter(
                (animalEntry)
                -> (animalEntry.getValue().isFromTheSameSpecie(specie))).forEach((animalEntry) -> {
                    sameSpecieAnimals.add(animalEntry.getValue());
                });
        return sameSpecieAnimals;
    }

    @Override
    public List<String> listSpeciesByName() {
        List<String> listSpecie = new ArrayList<>();
        this.animals.entrySet().stream().filter(
                (animalEntry)
                -> (!listSpecie.contains(animalEntry.getValue().getSpecie()))).forEach((animalEntry) -> {
                    listSpecie.add(animalEntry.getValue().getSpecie().getNameAccordingToLanguage(option));
                });
        return listSpecie;
    }

    @Override
    public List<String> listSpeciesByName(List<String> presentedSpecies) {
        this.animals.entrySet().stream().map(
                (animalEntry)
                -> animalEntry.getValue().getSpecie().getNameAccordingToLanguage(option)).filter((name)
                        -> (!presentedSpecies.contains(name))).forEach((name) -> {
                    presentedSpecies.add(name);
                });
        return presentedSpecies;
    }

    @Override
    public List<Specie> listSpecies() {
        List<Specie> listSpecie = new ArrayList<>();
        this.animals.entrySet().stream().filter((animalEntry)
                -> (!listSpecie.contains(animalEntry.getValue().getSpecie()))).forEach((animalEntry) -> {
                    listSpecie.add(animalEntry.getValue().getSpecie());
                });
        return listSpecie;
    }

    @Override
    public List<Specie> listSpecies(List<Specie> presentedSpecies) {
        this.animals.entrySet().stream().filter((animalEntry)
                -> (!presentedSpecies.contains(animalEntry.getValue().getSpecie()))).forEach((animalEntry) -> {
                    presentedSpecies.add(animalEntry.getValue().getSpecie());
                });
        return presentedSpecies;
    }

    @Override
    public List<Integer> listFamiliesById() {
        List<Integer> listFamily = new ArrayList<>();
        for (Specie specie : this.listSpecies()) {
            listFamily.add(specie.getFamily());
        }
        return listFamily;
    }

    @Override
    public void addInNeightbourhood(IPaddock paddock) {
        this.neightbourhood.add(paddock);
    }

    @Override
    public void addAllInNeightbourhood(List<IPaddock> neightbourhood) {
        this.neightbourhood.addAll(neightbourhood);
    }

    @Override
    public void removeFromNeightbourhood() {
        this.neightbourhood.stream().forEach((neightbour) -> {
            neightbour.removeANeightbour(this);
        });
    }

    @Override
    public void removeANeightbour(IPaddock paddock) {
        this.neightbourhood.remove(paddock);
    }

    @Override
    public List<Specie> listSpeciesInNeightbourhood() {
        List<Specie> speciesList = new ArrayList<>();
        List<Specie> localList = new ArrayList<>();
        for (IPaddock pad : this.neightbourhood) {
            localList.addAll(pad.listSpecies(speciesList));
            speciesList = localList;
        }
        return speciesList;
    }

    @Override
    public boolean compareTo(LightPaddock lightPaddock) {
        boolean isCorresponding = true;
        if (null != lightPaddock.getBiomes()) {
            if (lightPaddock.getBiomes().size() > 1) {
                return false;
            }
            isCorresponding &= lightPaddock.getBiomes().contains(this.biome);
        }
        if (null != lightPaddock.getTypes()) {
            if (lightPaddock.getTypes().size() > 1) {
                return false;
            }
            isCorresponding &= lightPaddock.getTypes().contains(this.paddockType);
        }
        return isCorresponding;
    }

    @Override
    public String getName(SaveImpl.FriendSave friend) {
        friend.hashCode();
        return this.name;
    }

    @Override
    public Map<String, Animal> getAnimals(SaveImpl.FriendSave friend) {
        friend.hashCode();
        return this.animals;
    }

    @Override
    public PaddockCoordinates getCoordinates(SaveImpl.FriendSave friend) {
        friend.hashCode();
        return this.coordinates;
    }

    @Override
    public int getBiome(SaveImpl.FriendSave friend) {
        friend.hashCode();
        return this.biome;
    }

    @Override
    public int getPaddockType(SaveImpl.FriendSave friend) {
        friend.hashCode();
        return this.paddockType;
    }

}
