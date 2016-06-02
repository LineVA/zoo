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
    HashMap<String, Animal> animals;

    /**
     * The main constructor of the class Because no biome is known, the fields
     * take the values of the ones forme Biome.NONE
     *
     * @param name the name of the paddock
     * @param coor the coordinates of the paddock
     */
    public Paddock(String name, PaddockCoordinates coor) {
        this.name = name;
        this.coordinates = coor;
        this.biome = Biome.NONE.getName();
        this.attributes = (BiomeAttributes) Biome.NONE.getAttributes().clone();
        this.animals = new HashMap<>();
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
        if (this.animals.containsKey(animal.getName())) {
            throw new AlreadyUsedNameException("There is already an animal with"
                    + " this name is this paddock, please choose another one.");
        } else {
            animals.put(animal.getName(), animal);
        }
    }

    public void removeAnimal(String name) throws UnknownNameException {
        if (this.animals.containsKey(name)) {
            this.animals.remove(name);
        } else {
            throw new UnknownNameException("No animal with this name exists in this paddock.");
        }
    }

    @Override
    public ArrayList<String> info() {
        ArrayList<String> info = new ArrayList<>();
        info.add("Name : " + this.name);
        info.add("Coordinates : " + this.coordinates.toString());
        info.add("Animals : " + "None yet");
        info.add("Biome : " + this.biome);
        info.add("Biome's characteristics : " + this.attributes.toString());
        return info;
    }

    @Override
    public Animal findAnimalByName(String animalName) throws UnknownNameException {
        for (HashMap.Entry<String, Animal> animalEntry : this.animals.entrySet()) {
            if (animalEntry.getKey().equalsIgnoreCase(animalName)) {
                return animalEntry.getValue();
            }
        }
        throw new UnknownNameException("There is no animal in this paddock "
                + "with this name.");
    }

    @Override
    public ArrayList<String> listAnimal() {
        ArrayList<String> list = new ArrayList<>();
        for (HashMap.Entry<String, Animal> entry : animals.entrySet()) {
            list.add(entry.getKey());
        }
        return list;
    }

    @Override
    public void birth() throws IncorrectDataException {
        ArrayList<Animal> tmpAnimal = new ArrayList<>();
        Reproduction repro = new ReproductionImpl();
        ArrayList<Animal> newFamily;
        for (HashMap.Entry<String, Animal> animalEntry : this.animals.entrySet()) {
            newFamily = repro.reproducte(animalEntry.getValue());
            // If there is reproduction
            if (newFamily != null) {
                // newFamily[0] = mother;
                // new Family[1] = father;
                for (int i = 2; i < newFamily.size(); i++) {
                    tmpAnimal.add(newFamily.get(i));
                }
            }
        }
        incomingNewBorn(tmpAnimal);
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
    public void death() {
        IDie die = new DieImpl();
        ArrayList<Animal> tmpAnimal = new ArrayList<>();
        for (HashMap.Entry<String, Animal> animalEntry : this.animals.entrySet()) {
            // If the animal is dead
            if (die.isDied(animalEntry.getValue())) {
                tmpAnimal.add(animalEntry.getValue());
            }
        }
        leavingNewDead(tmpAnimal);
    }

    private void leavingNewDead(ArrayList<Animal> tmpAnimal) {
        Iterator it = tmpAnimal.iterator();
        Animal animal;
        while (it.hasNext()) {
            animal = (Animal) it.next();
            this.animals.remove(animal.getName());
        }
    }

    @Override
    public int countAnimalsOfTheSameSpecie(Specie specie) {
       return animalsOfTheSameSpecie(specie).size();
    }

    @Override
    public int wellBeing() {
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
    public ArrayList<String> countSpecies(ArrayList<String> presentedSpecies) {
        for (HashMap.Entry<String, Animal> animalEntry : this.animals.entrySet()) {
            String name = animalEntry.getValue().getSpecie().getNames().getEnglishName();
            if (!presentedSpecies.contains(name)) {
                presentedSpecies.add(name);
            }
        }
        return presentedSpecies;
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
            if (animalEntry.getValue().getSpecie().equals(specie)) {
                sameSpecieAnimals.add(animalEntry.getValue());
            }
        }
        return sameSpecieAnimals;
    }

    @Override
    public String getName(SaveImpl.FriendSave friend) {
        return this.name;
    }
    
     @Override
    public HashMap<String, Animal> getAnimals(SaveImpl.FriendSave friend) {
        return this.animals;
    }

}
