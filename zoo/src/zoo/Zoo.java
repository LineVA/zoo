package zoo;

import exception.IncorrectDataException;
import zoo.paddock.PaddockCoordinates;
import exception.IncorrectDimensionsException;
import exception.name.AlreadyUsedNameException;
import exception.name.EmptyNameException;
import exception.name.UnknownNameException;
import java.io.IOException;
import java.util.ArrayList;
import backup.save.SaveImpl;
import exception.IncorrectLoadException;
import exception.name.NameException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeMap;
import launch.options.Option;
import lombok.Getter;
import lombok.Setter;
import launch.play.tutorials.TutorialPlayImpl_1;
import zoo.animal.Animal;
import zoo.animal.LightAnimal;
import zoo.animal.specie.LightSpecie;
import zoo.animal.specie.Specie;
import zoo.animalKeeper.AnimalKeeper;
import zoo.animalKeeper.AnimalKeeperBuilder;
import zoo.paddock.IPaddock;
import zoo.paddock.LightPaddock;
import zoo.paddock.PaddockBuilder;

/**
 *
 * @author doyenm
 */
public class Zoo implements IZoo {

    private final int initAge = 0;
    private final int initHorizon = 5;
    private final int initMonthsPerEvaluation = 6;

    @Getter
    @Setter
    private Option option;

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
     * The hashmap of the animal keepers it contains
     */
    private Map<String, AnimalKeeper> keepers;
    /**
     * The HashMap of the existing species
     */
    @Setter
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
    private double grade;
    private int horizon;

    public Zoo() {
    }

    /**
     * Constructor of the object 'Zoo'
     *
     * @param name the name of the zoo
     * @param width the width of the zoo
     * @param height the height of the zoo
     * @param species the map of the species
     * @param age the age of the zoo
     * @param monthsPerEvaluation the number of months per evaluation
     * @param horizon the value of the horizon
     * @throws IncorrectDimensionsException throws if the width and/or the
     * height is/are smaller than 1
     * @throws exception.name.EmptyNameException
     */
    @Override
    public void initiateZoo(String name, int width, int height,
            Map<String, Specie> species, int age, int monthsPerEvaluation, int horizon)
            throws IncorrectDimensionsException, EmptyNameException, IOException {
        if (name.trim().equals("")) {
            throw new EmptyNameException(
                    this.option.getZooBundle().getString("EMPTY_NAME"));
        } else {
            this.name = name;
        }
        if (width < 1 || height < 1) {
            throw new IncorrectDimensionsException(
                    this.option.getZooBundle().getString("INCORRECT_DIMENSIONS"));
        } else {
            this.width = width;
            this.height = height;
        }
        paddocks = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        keepers = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        this.species = species;
        this.age = age;
        this.monthsPerEvaluation = monthsPerEvaluation;
        this.horizon = horizon;
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
            Map<String, Specie> species)
            throws IncorrectDimensionsException, EmptyNameException, IOException {
        if (name.trim().equals("")) {
            throw new EmptyNameException(
                    this.option.getZooBundle().getString("EMPTY_NAME"));
        } else {
            this.name = name;
        }
        if (width < 1 || height < 1) {
            throw new IncorrectDimensionsException(
                    this.option.getZooBundle().getString("INCORRECT_DIMENSIONS"));
        } else {
            this.width = width;
            this.height = height;
        }
        paddocks = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        keepers = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        this.species = species;
        this.age = this.initAge;
        this.monthsPerEvaluation = initMonthsPerEvaluation;
        this.horizon = initHorizon;
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
     * @throws exception.name.EmptyNameException
     */
    @Override
    public void addPaddock(String paddockName, int x, int y, int width, int height)
            throws AlreadyUsedNameException, IncorrectDimensionsException,
            EmptyNameException, NameException {
        PaddockCoordinates coor = new PaddockCoordinates(x, y, width, height);
        checkEmplacement(coor);
        List<IPaddock> neightbourhood = new ArrayList<>();
        PaddockCoordinates coorNeightbourhood = coor.getNeightbourhoodCoordinates(this.horizon);
        IPaddock tmp;
        for (Map.Entry<String, IPaddock> entry : paddocks.entrySet()) {
            tmp = checkNeightbourhood(entry, coor, coorNeightbourhood);
            if (tmp != null) {
                neightbourhood.add(tmp);
            }
        }
        IPaddock paddock = new PaddockBuilder().name(paddockName)
                .coordinates(coor)
                .option(option)
                .buildPaddock();
        IPaddock success = this.paddocks.putIfAbsent(paddockName, paddock);
        if (success == null) {
            reactualizeNeightbourhoods(paddock, neightbourhood);
        } else {
            throw new AlreadyUsedNameException(
                    this.option.getPaddockBundle()
                    .getString("ALREADY_USED_NAME"));
        }
    }

    private IPaddock checkNeightbourhood(Entry<String, IPaddock> entry,
            PaddockCoordinates coor, PaddockCoordinates coorNeightbourhood)
            throws IncorrectDimensionsException {
        IPaddock neightbour = null;
        if (!entry.getValue().isNotCompetingForSpace(coor)) {
            throw new IncorrectDimensionsException(
                    this.option.getPaddockBundle().getString("ALREADY_PADDOCK_HERE"));
        }
        if (entry.getValue().isNotCompetingForSpace(coorNeightbourhood)) {
            neightbour = entry.getValue();
        }
        return neightbour;
    }

    private boolean checkEmplacement(PaddockCoordinates coor) throws IncorrectDimensionsException {
        if (this.tooSmallforThisPaddock(coor)) {
            throw new IncorrectDimensionsException(
                    this.option.getZooBundle().getString("ZOO_TOO_SMALL"));
        }
        return true;
    }

    @Override
    public void addPaddock(IPaddock paddock)
            throws AlreadyUsedNameException, IncorrectDimensionsException {
        PaddockCoordinates coor = paddock.getCoordinates();
        checkEmplacement(coor);
        List<IPaddock> neightbourhood = new ArrayList<>();
        PaddockCoordinates coorNeightbourhood = coor.getNeightbourhoodCoordinates(this.horizon);
        IPaddock tmp;
        for (Map.Entry<String, IPaddock> entry : paddocks.entrySet()) {
            tmp = checkNeightbourhood(entry, coor, coorNeightbourhood);
            if (tmp != null) {
                neightbourhood.add(tmp);
            }
        }
        IPaddock success = this.paddocks.putIfAbsent(paddock.getName(), paddock);
        if (success == null) {
            paddock.addAllInNeightbourhood(neightbourhood);
            reactualizeNeightbourhoods(paddock, neightbourhood);
        } else {
            throw new AlreadyUsedNameException(
                    this.option.getPaddockBundle().getString("ALREADY_USED_NAME_PADDOCK"));
        }
    }

    private void reactualizeNeightbourhoods(IPaddock paddock, List<IPaddock> neightbourhoods) {
        neightbourhoods.stream().forEach((neightbour) -> {
            neightbour.addInNeightbourhood(paddock);
        });
    }

    private List<IPaddock> listPaddock(LightPaddock lightPaddock) {
        List<IPaddock> padList = new ArrayList<>();
        for (Map.Entry<String, IPaddock> paddock : paddocks.entrySet()) {
            if (paddock.getValue().compareTo(lightPaddock)) {
                padList.add(paddock.getValue());
            }
        }
        return padList;
    }

    @Override
    public List<String> listPaddock(LightPaddock lightPaddock, Set<Specie> species) {
        List<IPaddock> padList = this.listPaddock(lightPaddock);
        List<String> list = new ArrayList<>();
//        for (Map.Entry<String, IPaddock> paddock : paddocks.entrySet()) {
//            if (paddock.getValue().compareTo(lightPaddock)) {
//                padList.add(paddock.getValue());
//            }
//        }
        if (species.size() != 0) {
            boolean containsSpecie;
            for (IPaddock pad : padList) {
                containsSpecie = true;
                for (Specie spec : species) {
                    if (pad.countAnimalsOfTheSameSpecie(spec) > 0) {
                        containsSpecie &= true;
                    } else {
                        containsSpecie &= false;
                    }
                }
                if (containsSpecie) {
                    list.add(pad.getName());
                }
            }
        } else {
            for (IPaddock pad : padList) {
                list.add(pad.getName());
            }
        }
        return list;
    }

    /**
     * Method used to find a paddock by its name
     *
     * @param name the name to search
     * @return the paddock if it exists
     * @throws UnknownNameException if the p
     * @throws EmptyNameException
     */
    @Override
    public IPaddock findPaddockByName(String name) throws UnknownNameException,
            EmptyNameException {
        if (name.trim().equals("")) {
            throw new EmptyNameException(
                    this.option.getPaddockBundle().getString("EMPTY_NAME_PADDOCK"));
        }
        if (paddocks.containsKey(name)) {
            return paddocks.get(name);
        }
        throw new UnknownNameException(
                this.option.getPaddockBundle().getString("UNKNOWN_PADDOCK"));
    }

    @Override
    public AnimalKeeper findAnimalKeeperByName(String name) throws UnknownNameException,
            EmptyNameException {
        if (name.trim().equals("")) {
            throw new EmptyNameException(
                    this.option.getKeeperBundle().getString("EMPTY_NAME_KEEPER"));
        }
        if (keepers.containsKey(name)) {
            return keepers.get(name);
        }
        throw new UnknownNameException(
                this.option.getKeeperBundle().getString("UNKNOWN_KEEPER"));
    }

    private List<AnimalKeeper> findAnimalKeeperByPaddock(IPaddock paddock) throws UnknownNameException,
            EmptyNameException {
        List<AnimalKeeper> list = new ArrayList<>();
        for (Map.Entry<String, AnimalKeeper> keeper : this.keepers.entrySet()) {
            if (keeper.getValue().workInGivenPaddock(paddock)) {
                list.add(keeper.getValue());
            }
        }
        return list;
    }

    @Override
    public List<String> listAnimalKeeper(IPaddock paddock)
            throws UnknownNameException, EmptyNameException {
        List<String> list = new ArrayList<>();
        if (paddock == null) {
            for (Map.Entry<String, AnimalKeeper> entry : this.keepers.entrySet()) {
                list.add(entry.getKey());
            }
        } else {
            this.findAnimalKeeperByPaddock(paddock).forEach(item -> {
                list.add(item.getName());
            });
        }
        return list;
    }

    private void removePaddockFromKeepers(IPaddock paddock) {
        List<IPaddock> padList = new ArrayList<>();
        padList.add(paddock);
        for (Map.Entry<String, AnimalKeeper> keeper : this.keepers.entrySet()) {
            keeper.getValue().removeTimedPaddocks(padList);
        }
    }

    @Override
    public void removePaddock(IPaddock paddock) {
        this.paddocks.remove(paddock.getName());
        paddock.removeFromNeightbourhood();
        this.removePaddockFromKeepers(paddock);
    }

    @Override
    public void addKeeper(String name)
            throws AlreadyUsedNameException, EmptyNameException, NameException {
        NameVerifications.verify(name, this.option.getKeeperBundle());
        if (this.keepers.containsKey(name)) {
            throw new AlreadyUsedNameException(
                    this.option.getKeeperBundle().getString("ALREADY_USED_NAME_KEEPER"));
        }
        this.keepers.put(name, new AnimalKeeperBuilder()
                .name(name).option(this.option).buildAnimalKeeper());
    }

    @Override
    public void evolveAnimalKeepers() {
        for (Map.Entry<String, AnimalKeeper> entry : this.keepers.entrySet()) {
            entry.getValue().evolve(this.monthsPerEvaluation);
        }
    }

    @Override
    public void addKeeper(AnimalKeeper keeper) throws AlreadyUsedNameException {
        if (this.keepers.containsKey(name)) {
            throw new AlreadyUsedNameException(
                    this.option.getKeeperBundle().getString("ALREADY_USED_NAME_KEEPER"));
        }
        keepers.put(keeper.getName(), keeper);
    }

    @Override
    public void removeKeeper(AnimalKeeper keeper) {
        this.keepers.remove(keeper.getName());
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
    public double evaluate() {
        return new Evaluation().evaluate(this.paddocks);
    }

    @Override
    public List<String> ageing()
            throws IncorrectDataException, NameException, EmptyNameException, IncorrectLoadException {
        this.age += this.monthsPerEvaluation;
        return new Evaluation().ageing(this.paddocks, this.monthsPerEvaluation);
    }

    @Override
    public List<PaddockCoordinates> map() throws IncorrectDimensionsException {
        List<PaddockCoordinates> map = new ArrayList<>();
        map.add(new PaddockCoordinates(0, 0, width, height));
        paddocks.entrySet().stream().forEach((padEntry) -> {
            map.add(padEntry.getValue().getCoordinates());
        });
        return map;
    }

//    @Override
//    public Specie findSpecieByName(String specieName) throws EmptyNameException, UnknownNameException {
//        if (specieName.trim().equals("")) {
//            throw new EmptyNameException(
//                    this.option.getSpecieBundle().getString("EMPTY_NAME"));
//        }
//        if (species.containsKey(specieName)) {
//            return species.get(specieName);
//        } else {
//            return this.findSpecieByScientificName(specieName);
//        }
////        throw new UnknownNameException(
////                this.option.getSpecieBundle().getString("UNKNOWN_NAME"));
//    }
    @Override
    public Specie findSpecieByName(String specieName)
            throws EmptyNameException, UnknownNameException {
        if (specieName.trim().equals("")) {
            throw new EmptyNameException(
                    this.option.getSpecieBundle().getString("EMPTY_NAME"));
        }
        for (Entry<String, Specie> specie : this.species.entrySet()) {
            if (specie.getValue().getNames().containsName(specieName, option)) {
                return specie.getValue();
            }
        }
        throw new UnknownNameException(
                this.option.getSpecieBundle().getString("UNKNOWN_NAME"));
    }

    @Override
    public Specie findSpecieByScientificName(String specieName)
            throws EmptyNameException, UnknownNameException {
        if (specieName.trim().equals("")) {
            throw new EmptyNameException(
                    this.option.getSpecieBundle().getString("EMPTY_NAME"));
        }
        for (Entry<String, Specie> specie : this.species.entrySet()) {
            if (specie.getValue().getNames().getScientificName().equalsIgnoreCase(specieName)) {
                return specie.getValue();
            }
        }
        throw new UnknownNameException(
                this.option.getSpecieBundle().getString("UNKNOWN_NAME"));
    }

    @Override
    public Animal findAnimalByName(String animalName)
            throws UnknownNameException, EmptyNameException {
        if (animalName.trim().equals("")) {
            throw new EmptyNameException(
                    this.option.getAnimalBundle().getString("EMPTY_NAME"));
        }
        for (Map.Entry<String, IPaddock> padEntry : this.paddocks.entrySet()) {
            try {
                return padEntry.getValue().findAnimalByName(animalName);
            } catch (UnknownNameException ex) {
                // If there is no animal of this name in this paddock, we test the next paddock
            }
        }
        throw new UnknownNameException(
                this.option.getAnimalBundle().getString("UNKNOWN_NAME"));
    }

    @Override
    public List<Animal> listAnimal(LightPaddock paddock, LightSpecie specie, LightAnimal animal)
            throws UnknownNameException {
        List<Animal> list = new ArrayList<>();
        for (IPaddock pad : this.listPaddock(paddock)) {
            list.addAll(pad.listAnimal(specie, animal));
        }
        return list;
    }

    @Override
    public List<String> listSpecie(LightSpecie lightSpecie, Set<IPaddock> paddock) {
        List<Specie> list = new ArrayList<>();
        if (paddock == null) {
            species.entrySet().stream().forEach((entry) -> {
                list.add(entry.getValue());
            });
        } else {
            for (IPaddock pad : paddock) {
                list.addAll(pad.listSpecies());
            }
        }
        Iterator it = list.iterator();
        Specie next;
        while (it.hasNext()) {
            next = (Specie) it.next();
            if (!next.compare(lightSpecie, this.option)) {
                it.remove();
            }
        }
        List<String> strList = new ArrayList<>();
        for (Specie spec : list) {
            strList.add(spec.getNameAccordingToLanguage(this.option));
        }
        return strList;
    }

    @Override
    public double grade() throws UnknownNameException, EmptyNameException {
        this.grade = 0.0;
        for (Map.Entry<String, IPaddock> entry : paddocks.entrySet()) {
            this.grade += entry.getValue().wellBeing(findAnimalKeeperByPaddock(entry.getValue()));
        }
        return this.grade;
    }

    @Override
    public void changeSpeed(int newSpeed) throws IncorrectDataException {
        if (newSpeed > 0) {
            this.monthsPerEvaluation = newSpeed;
        } else {
            throw new IncorrectDataException(
                    this.option.getZooBundle().getString("MONTHS_GREATER_THAN_ZERO"));
        }
    }

    @Override
    public void changeHorizon(int newHorizon) throws IncorrectDataException {
        if (newHorizon > 0) {
            this.horizon = newHorizon;
        } else {
            throw new IncorrectDataException(
                    this.option.getZooBundle().getString("HORIZON_GREATER_THAN_ZERO"));
        }
    }

    @Override
    public List<String> info() {
        List<String> info = new ArrayList<>();
        ResourceBundle bundle = this.option.getZooBundle();
        info.add(bundle.getString("NAME") + this.name);
        info.add(bundle.getString("AGE") + this.age);
        info.add(bundle.getString("DIMENSIONS_WIDTH") + this.width
                + bundle.getString("COMMA_HEIGHT") + this.height);
        info.add(bundle.getString("HORIZON") + this.horizon);
        info.add(bundle.getString("MONTHS") + this.monthsPerEvaluation);
        info.add(bundle.getString("GRADE") + this.grade);
        return info;
    }

    // Access to the fields only the the friend class
    @Override
    public String getName(TutorialPlayImpl_1.FriendScenario friend) {
        friend.hashCode();
        return this.name;
    }

    @Override
    public Map<String, IPaddock> getPaddocks(TutorialPlayImpl_1.FriendScenario friend) {
        friend.hashCode();
        return this.paddocks;
    }

    @Override
    public List<Animal> getAnimals(TutorialPlayImpl_1.FriendScenario friend) {
        try {
            friend.hashCode();
            return this.listAnimal(null, null, null);
        } catch (Exception ex) {
            System.out.println("ERROR  !!!!!!!!!!!!!!!!!");
            return null;
        }
    }

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
    public Map<String, AnimalKeeper> getAnimalKeepers(SaveImpl.FriendSave friend) {
        friend.hashCode();
        return this.keepers;
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
    public int getHorizon(SaveImpl.FriendSave friend) {
        friend.hashCode();
        return this.horizon;
    }

    @Override
    public Option getOption(SaveImpl.FriendSave friend) {
        friend.hashCode();
        return this.option;
    }
}
