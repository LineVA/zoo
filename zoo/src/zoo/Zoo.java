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
import backup.save.SaveImpl;
import zoo.animal.Animal;
import zoo.animal.specie.Specie;
import zoo.paddock.IPaddock;

/**
 *
 * @author doyenm
 */
public class Zoo implements IZoo {

    /**
     * The name of the zoo
     */
    private String name;
    /**
     * Its width
     */
    private int width;
    /**
     * Its height
     */
    private int height;
    /**
     * The hashmap of the paddocks it contains
     */
    private HashMap<String, IPaddock> paddocks;
    /**
     * The HashMap of the existing species
     */
    private HashMap<String, Specie> species;
    /**
     * The number of months which flows when we evaluate the zoo
     */
    private final int monthsPerEvaluation;
    /**
     * The age of the zoo
     */
    private int age;

    public Zoo() {
        this.monthsPerEvaluation = 6;
    }

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
    public void initiateZoo(String name, int width, int height, HashMap<String, Specie> species, int age)
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
        this.age = age;
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
            for (HashMap.Entry<String, IPaddock> entry : paddocks.entrySet()) {
                if (!entry.getValue().isNotCompetingForSpace(coor)) {
                    throw new IncorrectDimensionsException("This paddock cannot"
                            + " be set here : there is already another one on this place.");
                }
            }
            IPaddock paddock = new Paddock(paddockName, coor);
            this.paddocks.put(paddockName, paddock);
        }
    }

    public void addPaddock(IPaddock paddock)
            throws AlreadyUsedNameException, IncorrectDimensionsException {
        if (paddocks.containsKey(paddock.getName())) {
            throw new AlreadyUsedNameException("A paddock with this name is"
                    + " already existing. Please choose another one.");
        } else {
            PaddockCoordinates coor = paddock.getCoordinates();
            if (this.tooSmallforThisPaddock(coor)) {
                throw new IncorrectDimensionsException("This paddock cannot be "
                        + "set here : the zoo is too small.");
            }
            for (HashMap.Entry<String, IPaddock> entry : paddocks.entrySet()) {
                if (!entry.getValue().isNotCompetingForSpace(coor)) {
                    throw new IncorrectDimensionsException("This paddock cannot"
                            + " be set here : there is already another one on this place.");
                }
            }
            this.paddocks.put(paddock.getName(), paddock);
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
        for (HashMap.Entry<String, IPaddock> entry : paddocks.entrySet()) {
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
    public IPaddock findPaddockByName(String name) throws UnknownNameException,
            EmptyNameException {
        if (name.trim().equals("")) {
            throw new EmptyNameException("");
        }
        for (HashMap.Entry<String, IPaddock> entry : paddocks.entrySet()) {
            if (entry.getKey().equalsIgnoreCase(name)) {
                return entry.getValue();
            }
        }
        throw new UnknownNameException("This paddock does not exist.");
    }

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

    @Override
    public int evaluate() {
        ArrayList<String> presentedSpecies = new ArrayList<>();
        int kidsNb = 0;
        for (HashMap.Entry<String, IPaddock> padEntry : this.paddocks.entrySet()) {
            kidsNb += padEntry.getValue().countNonMatureAnimals();
            presentedSpecies = padEntry.getValue().countSpecies(presentedSpecies);
        }
        return kidsNb * 5 + presentedSpecies.size();
    }

    @Override
    public void death() {
        for (HashMap.Entry<String, IPaddock> padEntry : this.paddocks.entrySet()) {
            padEntry.getValue().death();
        }
    }

    @Override
    public void birth() throws IncorrectDataException {
        for (HashMap.Entry<String, IPaddock> padEntry : this.paddocks.entrySet()) {
            padEntry.getValue().birth();
        }
    }

    @Override
    public void ageing() {
        this.age += this.monthsPerEvaluation;
        for (HashMap.Entry<String, IPaddock> padEntry : this.paddocks.entrySet()) {
            padEntry.getValue().ageing(this.monthsPerEvaluation);
        }
    }

    @Override
    public ArrayList<PaddockCoordinates> map() throws IncorrectDimensionsException {
        ArrayList<PaddockCoordinates> map = new ArrayList<>();
        map.add(new PaddockCoordinates(0, 0, width, height));
        for (HashMap.Entry<String, IPaddock> padEntry : paddocks.entrySet()) {
            map.add(padEntry.getValue().getCoordinates());
        }
        return map;
    }

    @Override
    public Specie findSpeciebyName(String specieName) throws EmptyNameException, UnknownNameException {
        if (specieName.trim().equals("")) {
            throw new EmptyNameException("This specie is unknown.");
        }
        for (HashMap.Entry<String, Specie> entry : species.entrySet()) {
            if (entry.getKey().equalsIgnoreCase(specieName)) {
                return entry.getValue();
            }
        }
        throw new UnknownNameException("No specie with this name exists.");
    }

    @Override
    public Animal findAnimalByName(String animalName) throws UnknownNameException, EmptyNameException {
        if (animalName.trim().equals("")) {
            throw new EmptyNameException("The name of the animal is empty");
        }
        for (HashMap.Entry<String, IPaddock> padEntry : this.paddocks.entrySet()) {
            try {
                return padEntry.getValue().findAnimalByName(animalName);
            } catch (UnknownNameException ex) {

            }
        }
        throw new UnknownNameException("There is no animal with this name in the zoo.");
    }

    @Override
    public ArrayList<String> listAnimal() {
        ArrayList<String> list = new ArrayList<>();
        for (HashMap.Entry<String, IPaddock> entry : paddocks.entrySet()) {
            list.addAll(entry.getValue().listAnimal());
        }
        return list;
    }

    @Override
    public ArrayList<String> listSpecie() {
        ArrayList<String> list = new ArrayList<>();
        for (HashMap.Entry<String, Specie> entry : species.entrySet()) {
            list.add(entry.getValue().getNames().getEnglishName());
        }
        return list;
    }

    @Override
    public int wellBeing() {
        int wB = 0;
        for (HashMap.Entry<String, IPaddock> entry : paddocks.entrySet()) {
            wB += entry.getValue().wellBeing();
        }
        return wB;
    }

    // Access to the fields only the the friend class
    @Override
    public String getName(SaveImpl.FriendSave friend) {
        return this.name;
    }

    @Override
    public int getWidth(SaveImpl.FriendSave friend) {
        return this.width;
    }

    @Override
    public int getHeight(SaveImpl.FriendSave friend) {
        return this.height;
    }

    @Override
    public HashMap<String, IPaddock> getPaddocks(SaveImpl.FriendSave friend) {
        return this.paddocks;
    }

    @Override
    public HashMap<String, Specie> getSpecies(SaveImpl.FriendSave friend) {
        return this.species;
    }

    @Override
    public int getMonthsPerEvaluation(SaveImpl.FriendSave friend) {
        return this.monthsPerEvaluation;
    }

    @Override
    public int getAge(SaveImpl.FriendSave friend) {
        return this.age;
    }

    @Override
    public HashMap<String, IPaddock> getPaddocks() {
        return this.paddocks;
    }

    @Override
    public HashMap<String, Specie> getSpecies() {
        return this.species;
    }
}
