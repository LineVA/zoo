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
import java.util.Map;
import java.util.TreeMap;
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
    private Map<String, IPaddock> paddocks;
    /**
     * The HashMap of the existing species
     */
    private Map<String, Specie> species;
    /**
     * The number of months which flows when we evaluate the zoo
     */
    private int monthsPerEvaluation;
    /**
     * The age of the zoo
     */
    private int age;
    /**
     * The grade of the zoo Does not have to be save, can be re-calculate each
     * time we need it
     */
    private int grade;

    public Zoo() {
        //  this.monthsPerEvaluation = 6;
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
    public void initiateZoo(String name, int width, int height,
            Map<String, Specie> species, int age, int monthsPerEvaluation)
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
        paddocks = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        this.species = species;
        this.age = age;
        this.monthsPerEvaluation = monthsPerEvaluation;
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
    public ArrayList<String> listPaddock(Specie specie) {
        ArrayList<String> list = new ArrayList<>();
        if (specie == null) {
            for (HashMap.Entry<String, IPaddock> entry : paddocks.entrySet()) {
                list.add(entry.getKey());
            }
        } else {
            for (HashMap.Entry<String, IPaddock> entry : paddocks.entrySet()) {
                if (entry.getValue().countAnimalsOfTheSameSpecie(specie) != 0) {
                    list.add(entry.getKey());
                }
            }
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
//        for (HashMap.Entry<String, IPaddock> entry : paddocks.entrySet()) {
//            if (entry.getKey().equalsIgnoreCase(name)) {
//                return entry.getValue();
//            }
//        }
        if (paddocks.containsKey(name)) {
            return paddocks.get(name);
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
    public ArrayList<String> death() {
        ArrayList<String> info = new ArrayList<>();
        for (HashMap.Entry<String, IPaddock> padEntry : this.paddocks.entrySet()) {
            info.addAll(padEntry.getValue().death());
        }
        return info;
    }

    @Override
    public ArrayList<String> birth() throws IncorrectDataException {
        ArrayList<String> info = new ArrayList<>();
        for (HashMap.Entry<String, IPaddock> padEntry : this.paddocks.entrySet()) {
            info.addAll(padEntry.getValue().birth());
        }
        return info;
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
//        for (HashMap.Entry<String, Specie> entry : species.entrySet()) {
//            if (entry.getKey().equalsIgnoreCase(specieName)) {
//                return entry.getValue();
//            }
//        }
        if (species.containsKey(specieName)) {
            return species.get(specieName);
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
    public ArrayList<String> listAnimal(Specie specie, IPaddock paddock) {
        if (paddock == null) {
            ArrayList<String> list = new ArrayList<>();
            for (HashMap.Entry<String, IPaddock> entry : paddocks.entrySet()) {
                list.addAll(entry.getValue().listAnimal(specie));
            }
            return list;
        } else {
            return paddock.listAnimal(specie);
        }
    }

    @Override
    public ArrayList<String> listSpecie(IPaddock paddock) {
        ArrayList<String> list = new ArrayList<>();
        if (paddock == null) {
            for (HashMap.Entry<String, Specie> entry : species.entrySet()) {
                list.add(entry.getValue().getNames().getEnglishName());
            }
        } else {
            list.addAll(paddock.listSpecie());
        }
        return list;
    }

    @Override
    public int grade() {
        this.grade = 0;
        for (HashMap.Entry<String, IPaddock> entry : paddocks.entrySet()) {
            this.grade += entry.getValue().wellBeing();
        }
        return this.grade;
    }

    @Override
    public ArrayList<String> info() {
        ArrayList<String> info = new ArrayList<>();
        info.add("Name : " + this.name);
        info.add("Age : " + this.age);
        info.add("Months per evaluation : " + this.monthsPerEvaluation);
        info.add("Dimensions : width = " + this.width + ", height = " + this.height);
        info.add("Grade : " + this.grade);
        return info;
    }

    // Access to the fields only the the friend class
    @Override
    public String getName(SaveImpl.FriendSave friend) {
        friend.hashCode();
        return this.name;
    }

    @Override
    public int getWidth(SaveImpl.FriendSave friend) {
        friend.hashCode();
        return this.width;
    }

    @Override
    public int getHeight(SaveImpl.FriendSave friend) {
        friend.hashCode();
        return this.height;
    }

    @Override
    public Map<String, IPaddock> getPaddocks(SaveImpl.FriendSave friend) {
        friend.hashCode();
        return this.paddocks;
    }

    @Override
    public Map<String, Specie> getSpecies(SaveImpl.FriendSave friend) {
        friend.hashCode();
        return this.species;
    }

    @Override
    public int getMonthsPerEvaluation(SaveImpl.FriendSave friend) {
        friend.hashCode();
        return this.monthsPerEvaluation;
    }

    @Override
    public int getAge(SaveImpl.FriendSave friend) {
        friend.hashCode();
        return this.age;
    }

    @Override
    public Map<String, IPaddock> getPaddocks() {
        return this.paddocks;
    }

    @Override
    public Map<String, Specie> getSpecies() {
        return this.species;
    }
}
