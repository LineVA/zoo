package zoo.paddock;

import zoo.paddock.biome.BiomeAttributes;
import zoo.paddock.biome.Biome;
import exception.name.AlreadyUsedNameException;
import exception.name.UnknownNameException;
import java.util.ArrayList;
import java.util.HashMap;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import zoo.animal.Animal;

/**
 *
 * @author doyenm
 */
// @EqualsAndHashCode(exclude={"nightTemperature", "dayTemperature", "pluviometry",
// "treeDensity", "treeHeight", "drop", "waterSalinity", "humidity"})
@EqualsAndHashCode()
public class Paddock implements Cloneable {

    /**
     * The name of the paddock
     */
    @Getter
    private final String name;
    /**
     * Its coordinates
     */
    @Getter
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

    public void setBiome(String biomeName) throws UnknownNameException {
       try{
           setBiome(Biome.NONE.findById(Integer.parseInt(biomeName)));
       } catch(java.lang.NumberFormatException ex){
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

    public ArrayList<String> info() {
        ArrayList<String> info = new ArrayList<>();
        info.add("Name : " + this.name);
        info.add("Coordinates : " + this.coordinates.toString());
        info.add("Animals : " + "None yet");
        info.add("Biome : " + this.biome);
        info.add("Biome's characteristics : " + this.attributes.toString());
        return info;
    }

}
