package zoo.paddock;

import exception.IncorrectDataException;
import zoo.paddock.biome.BiomeAttributes;
import zoo.paddock.biome.Biome;
import exception.name.AlreadyUsedNameException;
import exception.name.UnknownNameException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import backup.save.SaveImpl;
import exception.name.EmptyNameException;
import exception.name.NameException;
import java.util.Map;
import java.util.ResourceBundle;
import launch.options.Option;
import java.util.Observable;
import java.util.Observer;
import java.util.TreeMap;
import zoo.BirthObservable;
import zoo.animal.Animal;
import zoo.animal.death.DieImpl;
import zoo.animal.death.IDie;
import zoo.animal.reproduction.Reproduction;
import zoo.animal.reproduction.ReproductionImpl;
import zoo.animal.specie.Specie;

/**
 *
 * @author doyenm
 */
// @EqualsAndHashCode(exclude={"nightTemperature", "dayTemperature", "pluviometry",
// "treeDensity", "treeHeight", "drop", "waterSalinity", "humidity"})
@EqualsAndHashCode()
public class Paddock implements Cloneable, IPaddock {

    @Getter
    private Option option;

    /**
     * The name of the paddock
     */
    private final String name;
    /**
     * Its coordinates
     */
    private final PaddockCoordinates coordinates;

    /**
     * Its biome's name
     */
    @Getter
    private String biome;

    @Getter
    BiomeAttributes attributes;

    @Getter
    Map<String, Animal> animals;

    private ArrayList<IPaddock> neightbourhood;

    private BirthObservable obs = new BirthObservable();

    /**
     * The main constructor of the class Because no biome is known, the fields
     * take the values of the ones forme Biome.NONE
     *
     * @param name the name of the paddock
     * @param coor the coordinates of the paddock
     */
    public Paddock(String name, PaddockCoordinates coor, 
            ArrayList<IPaddock> neightbourhood, Option option)
            throws EmptyNameException {
        this.option = option;
        if (name.trim().equals("")) {
            throw new EmptyNameException(
                    this.option.getPaddockBundle().getString("EMPTY_NAME"));
        } else {
            this.name = name;
        }
        this.coordinates = coor;
        this.biome = Biome.NONE.getName();
        this.attributes = (BiomeAttributes) Biome.NONE.getAttributes().clone();
        this.animals = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        this.neightbourhood = neightbourhood;
        obs.addObserver(new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                setNewComerName((String) arg);
            }
        });
    }

    /**
     * Setter of the fields linked to the biome
     *
     * @param biome the new biome of the paddock
     */
    public void setBiome(Biome biome) {
        if (biome != null) {
            this.biome = biome.getName();
            this.attributes = (BiomeAttributes) biome.getAttributes().clone();
        } else {
            setBiome(Biome.NONE);
        }
    }

    @Override
    public void setBiome(String biomeName) throws UnknownNameException {
        try {
            setBiome(Biome.NONE.findById(Integer.parseInt(biomeName)));
        } catch (java.lang.NumberFormatException ex) {
            setBiome(Biome.NONE.findByName(biomeName));
        }
    }

    public Object clone() {
        Paddock pad = null;
        try {
            pad = (Paddock) super.clone();
        } catch (CloneNotSupportedException cnse) {
            // Ne devrait jamais arriver car nous implémentons 
            // l'interface Cloneable
            cnse.printStackTrace(System.err);
        }

        // On clone l'attribut de type Patronyme qui n'est pas immuable.
        pad.attributes = (BiomeAttributes) attributes.clone();

        // on renvoie le clone
        return pad;
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
    public ArrayList<String> info() {
        ResourceBundle bundle = this.option.getPaddockBundle();
        ArrayList<String> info = new ArrayList<>();
        info.add(bundle.getString("NAME") + this.name);
        info.add(bundle.getString("COORDINATES") + this.coordinates.toString());
        info.add(bundle.getString("NEIGHTBOURS") + this.listNeightbourhood());
        info.add(bundle.getString("BIOME") + this.biome);
        info.add(bundle.getString("BIOMES_CHARACTERISTICS") + this.attributes.toString());
        return info;
    }

    private String listNeightbourhood() {
        String neightbours = "";
        for (IPaddock pad : this.neightbourhood) {
            neightbours += pad.getName() + ", ";
        }
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

    public ArrayList<Animal> listAnimalWithSpecie(Specie specie) {
        ArrayList<Animal> list = new ArrayList<>();
        for (HashMap.Entry<String, Animal> entry : animals.entrySet()) {
            if (entry.getValue().isFromTheSameSpecie(specie)) {
                list.add(entry.getValue());
            }
        }
        return list;
    }

    public ArrayList<Animal> listAnimalWithoutSpecie() {
        ArrayList<Animal> list = new ArrayList<>();
        for (HashMap.Entry<String, Animal> entry : animals.entrySet()) {
            list.add(entry.getValue());
        }
        return list;
    }

    @Override
    public ArrayList<Animal> listAnimal(Specie specie) {
        if (specie != null) {
            return listAnimalWithSpecie(specie);
        } else {
            return listAnimalWithoutSpecie();
        }
    }

    @Override
    public ArrayList<String> birth() throws IncorrectDataException, NameException {
        ArrayList<String> info = new ArrayList<>();
        ArrayList<Animal> tmpAnimal = new ArrayList<>();
        Reproduction repro = new ReproductionImpl();
        ArrayList<Animal> newFamily;
        Animal newComer;
        for (HashMap.Entry<String, Animal> animalEntry : this.animals.entrySet()) {
            newFamily = repro.reproducte(animalEntry.getValue());
            // If there is reproduction
            if (newFamily != null) {
                // newFamily[0] = mother;
                // new Family[1] = father;
                for (int i = 2; i < newFamily.size(); i++) {
                    newComer = specifieNameOfTheNewBorn(newFamily.get(i), newFamily.get(0), newFamily.get(1));
                    tmpAnimal.add(newComer);
                    info.add(this.option.getAnimalBundle().getString("NEWCOMER")
                            + newFamily.get(0).getName() + this.option.getAnimalBundle().getString("AND")
                            + newFamily.get(1).getName()
                            + this.option.getAnimalBundle().getString("CALLED") + newComer.getName());
                }
            }
        }
        incomingNewBorn(tmpAnimal);
        return info;
    }

    private Animal specifieNameOfTheNewBorn(Animal newBorn, Animal mother, Animal father)
            throws EmptyNameException {
        obs.askAndWait(mother.getName(), father.getName(), newBorn.getSex().toString());
        newBorn.setName(newComerName);
        return newBorn;
    }

    String newComerName = "";

    public void setNewComerName(String name) {
        this.newComerName = name;
    }

    private void incomingNewBorn(ArrayList<Animal> tmpAnimal) {
        Iterator it = tmpAnimal.iterator();
        Animal animal;
        while (it.hasNext()) {
            animal = (Animal) it.next();
            this.animals.put(animal.getName(), animal);
        }
    }

    @Override
    public void ageing(int monthsPerEvaluation) {
        for (HashMap.Entry<String, Animal> animalEntry : this.animals.entrySet()) {
            animalEntry.getValue().ageing(monthsPerEvaluation);
        }
    }

    @Override
    public ArrayList<String> death() {
        ArrayList<String> info = new ArrayList<>();
        IDie die = new DieImpl();
        ArrayList<Animal> tmpAnimal = new ArrayList<>();
        Animal dying;
        for (HashMap.Entry<String, Animal> animalEntry : this.animals.entrySet()) {
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

    private void leavingNewDead(ArrayList<Animal> tmpAnimal) {
        Iterator it = tmpAnimal.iterator();
        Animal animal;
        while (it.hasNext()) {
//            animal = (Animal) it.next();
            //     this.animals.remove(animal.getName());
            it.remove();
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
    public double wellBeing() throws UnknownNameException {
        int wB = 0;
        for (HashMap.Entry<String, Animal> entry : animals.entrySet()) {
            wB += entry.getValue().wellBeing();
        }
        return wB;
    }

    @Override
    public int computeSize() {
        return this.coordinates.computeSize();
    }

    @Override
    public void instanciatePaddock() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean isNotCompetingForSpace(PaddockCoordinates coordinates) {
        return this.coordinates.isNotCompeting(coordinates);
    }

    @Override
    public int countNonMatureAnimals() {
        int count = 0;
        for (HashMap.Entry<String, Animal> animalEntry : this.animals.entrySet()) {
            if (!animalEntry.getValue().isMature()) {
                count += 1;
            }
        }
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
    public ArrayList<Animal> animalsOfTheSameSpecie(Specie specie) {
        ArrayList<Animal> sameSpecieAnimals = new ArrayList<>();
        for (HashMap.Entry<String, Animal> animalEntry : this.animals.entrySet()) {
            if (animalEntry.getValue().isFromTheSameSpecie(specie)) {
                sameSpecieAnimals.add(animalEntry.getValue());
            }
        }
        return sameSpecieAnimals;
    }

    @Override
    public ArrayList<String> listSpeciesByName() {
        ArrayList<String> listSpecie = new ArrayList<>();
        for (HashMap.Entry<String, Animal> animalEntry : this.animals.entrySet()) {
            if (!listSpecie.contains(animalEntry.getValue().getSpecie())) {
                listSpecie.add(animalEntry.getValue().getSpecie().getNameAccordingLanguage(option));
            }
        }
        return listSpecie;
    }

    @Override
    public ArrayList<String> listSpeciesByName(ArrayList<String> presentedSpecies) {
        for (HashMap.Entry<String, Animal> animalEntry : this.animals.entrySet()) {
            String name = animalEntry.getValue().getSpecie().getNameAccordingLanguage(option);
            if (!presentedSpecies.contains(name)) {
                presentedSpecies.add(name);
            }
        }
        return presentedSpecies;
    }

    @Override
    public ArrayList<Specie> listSpecies() {
        ArrayList<Specie> listSpecie = new ArrayList<>();
        for (HashMap.Entry<String, Animal> animalEntry : this.animals.entrySet()) {
            if (!listSpecie.contains(animalEntry.getValue().getSpecie())) {
                listSpecie.add(animalEntry.getValue().getSpecie());
            }
        }
        return listSpecie;
    }

    public ArrayList<Specie> listSpecies(ArrayList<Specie> presentedSpecies) {
        for (HashMap.Entry<String, Animal> animalEntry : this.animals.entrySet()) {
            if (!presentedSpecies.contains(animalEntry.getValue().getSpecie())) {
                presentedSpecies.add(animalEntry.getValue().getSpecie());
            }
        }
        return presentedSpecies;
    }

    /**
     *
     * @param paddock
     */
    public void addInNeightbourhood(IPaddock paddock) {
        this.neightbourhood.add(paddock);
    }

    @Override
    public void addAllInNeightbourhood(ArrayList<IPaddock> neightbourhood) {
        this.neightbourhood.addAll(neightbourhood);
    }

    @Override
    public void removeFromNeightbourhood() {
        for (IPaddock neightbour : this.neightbourhood) {
            neightbour.removeANeightbour(this);
        }
    }

    @Override
    public void removeANeightbour(IPaddock paddock) {
        this.neightbourhood.remove(paddock);
    }

    @Override
    public ArrayList<Specie> listSpeciesInNeightbourhood() {
        ArrayList<Specie> speciesList = new ArrayList<>();
        ArrayList<Specie> localList = new ArrayList<>();
        for (IPaddock pad : this.neightbourhood) {
            localList.addAll(pad.listSpecies(speciesList));
            speciesList = localList;
        }
        return speciesList;
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
}
