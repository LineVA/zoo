package zoo;

import exception.IncorrectDataException;
import zoo.paddock.PaddockCoordinates;
import zoo.paddock.Paddock;
import exception.IncorrectDimensionsException;
import exception.name.AlreadyUsedNameException;
import exception.name.EmptyNameException;
import exception.name.UnknownNameException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import lombok.Getter;
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
public class Zoo implements IZoo {

    /**
     * The name of the zoo
     */
    @Getter
    private String name;
    /**
     * Its width
     */
    @Getter
    private int width;
    /**
     * Its height
     */
    @Getter
    private int height;
    /**
     * The hashmap of the paddocks it contains
     */
    @Getter
    private HashMap<String, Paddock> paddocks;
    /**
     * The HashMap of the existing species
     */
    @Getter
    private HashMap<String, Specie> species;

    /**
     * Constructor of the object 'Zoo'
     *
     * @param name the name of the zoo
     * @param width the width of the zoo
     * @param height the height of the zoo
     * @param species
     * @throws IncorrectDimensionsException throws if the width and/or the
     * height is/are smaller than 1
     * @throws exception.name.EmptyNameException
     */
    @Override
    public void initiateZoo(String name, int width, int height, HashMap<String, Specie> species)
            throws IncorrectDimensionsException, EmptyNameException, IOException {
        if (name.trim().equals("")) {
            throw new EmptyNameException("Please enter a no-empty name for the zoo.");
        } else {
            this.name = name;
        }
        if (width < 1 || height < 1) {
            throw new IncorrectDimensionsException("A zoo must have a width and "
                    + "an height greater or equals to 1 each.");
        } else {
            this.width = width;
            this.height = height;
        }
        paddocks = new HashMap<>();
        this.species = species;
    }

    /**
     * Method used to add a paddock to the zoo
     *
     * @param paddockName the name of the new paddock
     * @param x the origin x of the new paddock
     * @param y the origin y of the new paddock
     * @param width the width of the new paddock
     * @param height the height of the new paddock
     * @throws AlreadyUsedNameException if there is already a paddock in this
     * zoo with the same name
     * @throws IncorrectDimensionsException if the paddock is to big for the zoo
     * or its width and/or are smaller than 1 or its origins are sammler than 0
     */
    @Override
    public void addPaddock(String paddockName, int x, int y, int width, int height)
            throws AlreadyUsedNameException, IncorrectDimensionsException {
        if (paddocks.containsKey(paddockName)) {
            throw new AlreadyUsedNameException("A paddock with this name is"
                    + " already existing. Please choose another one.");
        } else {
            PaddockCoordinates coor = new PaddockCoordinates(x, y, width, height);
            if (this.tooSmallforThisPaddock(coor)) {
                throw new IncorrectDimensionsException("This paddock cannot be "
                        + "set here : the zoo is too small.");
            }
            for (HashMap.Entry<String, Paddock> entry : paddocks.entrySet()) {
                if (!coor.isNotCompeting(entry.getValue().getCoordinates())) {
                    throw new IncorrectDimensionsException("This paddock cannot"
                            + " be set here : there is already another one on this place.");
                }
            }
            Paddock paddock = new Paddock(paddockName, coor);
            this.paddocks.put(paddockName, paddock);
        }
    }

    /**
     * Method used to list the paddocks of the zoo
     *
     * @return ArrayList of their names
     */
    @Override
    public ArrayList<String> listPaddock() {
        ArrayList<String> list = new ArrayList<>();
        for (HashMap.Entry<String, Paddock> entry : paddocks.entrySet()) {
            list.add(entry.getKey());
        }
        return list;
    }

    /**
     * Method used to find a paddock by its name
     *
     * @param name the name to search
     * @return the paddock if it exists
     * @throws UnknownNameException if the paddock does not exist
     */
    @Override
    public Paddock findPaddockByName(String name) throws UnknownNameException,
            EmptyNameException {
        if (name.trim().equals("")) {
            throw new EmptyNameException("");
        }
        for (HashMap.Entry<String, Paddock> entry : paddocks.entrySet()) {
            if (entry.getKey().equals(name)) {
                return entry.getValue();
            }
        }
        throw new UnknownNameException("This paddock does not exist.");
    }

    /**
     * method used to obtain detailed information about a paddock
     *
     * @param name the name of the paddock we search
     * @return these information without mef
     * @throws UnknownNameException if the searched paddock does not exist
     * @throws exception.name.EmptyNameException
     */
//    public ArrayList<String> detailledPaddock(String name) 
//            throws UnknownNameException,
//            EmptyNameException {
//        Paddock pad = findPaddockByName(name);
//        return pad.info();
//    }
//    
//    public void setBiome(String paddockName, String biomeName)
//            throws UnknownNameException, EmptyNameException{
//        Paddock pad = findPaddockByName(paddockName);
//        pad.setBiome(biomeName);
//    }
    

    /**
     * Method used to know if a paddock can be placed into the zoo without
     * looking for the others paddocks
     *
     * @param coor the coordinates of the new paddock
     * @return true if we can set it, false if it is too big for the zoo.
     */
    private boolean tooSmallforThisPaddock(PaddockCoordinates coor) {
        return ((coor.getX() + coor.getWidth() > this.width)
                || (coor.getY() + coor.getHeight() > this.height));
    }

//    public int kidsNbEvaluation() {
//        int kidsNb = 0;
//        for (HashMap.Entry<String, Paddock> padEntry : this.paddocks.entrySet()) {
//            for (HashMap.Entry<String, Animal> animalEntry : padEntry.getValue().getAnimals().entrySet()) {
//                if (animalEntry.getValue().isMature()) {
//                    kidsNb += 1;
//                }
//            }
//        }
//        return kidsNb;
//    }
//  
//    public int presentedSpeciesNbEvaluation() {
//        ArrayList<String> presentedSpecies = new ArrayList<>();
//          for (HashMap.Entry<String, Paddock> padEntry : this.paddocks.entrySet()) {
//            if()
//        }
//    }
    @Override
    public int evaluate() {
        ArrayList<String> presentedSpecies = new ArrayList<>();
        int kidsNb = 0;
        for (HashMap.Entry<String, Paddock> padEntry : this.paddocks.entrySet()) {
            for (HashMap.Entry<String, Animal> animalEntry : padEntry.getValue().getAnimals().entrySet()) {
                if (!animalEntry.getValue().isMature()) {
                    kidsNb += 1;
                }
                String name = animalEntry.getValue().getSpecie().getNames().getEnglishName();
                if (!presentedSpecies.contains(name)) {
                    presentedSpecies.add(name);
                }
            }
        }
        return kidsNb * 5 + presentedSpecies.size();
    }

    public void death() {
        IDie die = new DieImpl();
        for (HashMap.Entry<String, Paddock> padEntry : this.paddocks.entrySet()) {
            for (HashMap.Entry<String, Animal> animalEntry : padEntry.getValue().getAnimals().entrySet()) {
                // If the animal is dead
                if (die.isDied(animalEntry.getValue())) {
                    try {
                        padEntry.getValue().removeAnimal(animalEntry.getValue().getName());
                    } catch (UnknownNameException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
    }

    public void birth() throws IncorrectDataException{
        Reproduction repro = new ReproductionImpl();
        ArrayList<Animal> newFamily;
        for (HashMap.Entry<String, Paddock> padEntry : this.paddocks.entrySet()) {
            for (HashMap.Entry<String, Animal> animalEntry : padEntry.getValue().getAnimals().entrySet()) {
                newFamily = repro.reproducte(animalEntry.getValue());
                for (int i = 2; i < newFamily.size(); i++) {
                    try {
                        padEntry.getValue().addAnimal(newFamily.get(i));
                    } catch (AlreadyUsedNameException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public ArrayList<PaddockCoordinates> map() throws IncorrectDimensionsException {
        ArrayList<PaddockCoordinates> map = new ArrayList<>();
        map.add(new PaddockCoordinates(0, 0, width, height));
        for (HashMap.Entry<String, Paddock> padEntry : paddocks.entrySet()) {
            map.add(padEntry.getValue().getCoordinates());
        }
        return map;
    }

    @Override
    public Specie findSpeciebyName(String specieName) throws EmptyNameException, UnknownNameException {
          if (specieName.trim().equals("")) {
            throw new EmptyNameException("");
        }
        for (HashMap.Entry<String, Specie> entry : species.entrySet()) {
            if (entry.getKey().equals(specieName)) {
                return entry.getValue();
            }
        }
        throw new UnknownNameException("No specie with this name exists.");
    }

}
