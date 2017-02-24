package zoo.animal;

import backup.save.SaveImpl;
import exception.IncorrectDataException;
import exception.IncorrectLoadException;
import exception.name.EmptyNameException;
import exception.name.NameException;
import exception.name.UnauthorizedNameException;
import exception.name.UnknownNameException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import launch.options.Option;
import lombok.Getter;
import lombok.Setter;
import utils.Constants;
import utils.Utils;
import zoo.NameVerifications;
import zoo.animal.conservation.ConservationStatus;
import zoo.animal.death.LifeSpanLightAttributes;
import zoo.animal.feeding.Diet;
import zoo.animal.feeding.FeedingAttributes;
import zoo.animal.personality.PersonalityAttributes;
import zoo.animal.reproduction.AnimalReproductionAttributes;
import zoo.animal.reproduction.ContraceptionMethods;
import zoo.animal.reproduction.Sex;
import zoo.animal.social.SocialAttributes;
import zoo.animal.specie.LightSpecie;
import zoo.animal.specie.Specie;
import zoo.animal.wellbeing.WellBeing;
import zoo.animal.wellbeing.WellBeingImpl;
import zoo.animalKeeper.AnimalKeeper;
import zoo.paddock.IPaddock;
import zoo.paddock.TerritoryAttributes;
import zoo.paddock.biome.BiomeAttributes;

/**
 *
 * @author doyenm
 */
public class AnimalImpl implements Animal {

    private final double initWB = 0.0;
    private final double initFoodQuantity = 0.0;
    private final int initStarvation = 0;
    private final int initDrowning = 0;
    private final int initFastDays = 0;
    private final int initCurrentlyGestationDuration = 0;

    private Option option;

    private final Specie specie;
    private String name;
    private final IPaddock paddock;
    @Getter
    @Setter
    private double wellBeing;
    private int age;
    @Getter
    private final WellBeing wB;
// There is both optimal and actual biome attributes :
    // the first are determined when the animal is created,
    // the second are the ones of its paddock.
    private final BiomeAttributes optimalBiome;
    // There is both optimal and actual feeding attributes : 
    // the first are computed when the animal is created,
    // the second are determined by the player.
    @Getter
    @Setter
    private int actualDiet;
    private final FeedingAttributes optimalFeeding;
    @Setter
    private FeedingAttributes actualFeeding;
    // The actual reproduction attributes are computed 
    // when the animal is created ;
    // there is no notion of "optimal reproduction attributes".
    private final AnimalReproductionAttributes actualReproduction;
    // The actual life span is computed when the animal is created ; 
    // the "optimal" lifespan has no sense.
    private final LifeSpanLightAttributes actualLifeSpan;
    // There is only optimal social attributes : 
    // it is computed when the animal is created,
    // actual is given by the number of animal is the paddock.
    private final SocialAttributes optimalSocial;
    // There is only optimal social attributes : 
    // it is computed when the animal is created,
    // actual is given by the number of animal is the paddock.
    private final TerritoryAttributes optimalTerritory;
    private int turnsOfStarvation;
    private int turnsOfDrowning;
    @Getter
    private String mother;
    @Getter
    private String father;

    /**
     * There is no notion of optimal in personality
     */
    private final PersonalityAttributes personality;

    /**
     * Constructor used to create an animal by breeding
     *
     * @param spec the specie of then animal
     * @param name the name of the animal
     * @param paddock the paddock of the animal
     * @param sex the sex of the animal
     * @param age the age of the animal
     * @param option the options of the game
     * @throws IncorrectDataException
     * @throws EmptyNameException
     * @throws NameException
     * @throws IncorrectLoadException
     */
    public AnimalImpl(Specie spec, String name, IPaddock paddock, Sex sex,
            int age, String mother, String father, Option option)
            throws IncorrectDataException, EmptyNameException, NameException, IncorrectLoadException {
        this.option = option;
        this.specie = spec;
        NameVerifications.verify(name, this.option.getAnimalBundle());
        this.name = name;
        this.paddock = paddock;
        this.checkLoadingOfAge(age);
        this.optimalFeeding = drawOptimalFeeding(spec);
        this.actualFeeding = drawActualFeeding(spec);
        this.actualReproduction = drawActualReproduction(spec, sex);
        this.actualLifeSpan = drawActualLifeSpan(spec);
        this.optimalBiome = null;
        this.actualDiet = Diet.NONE.getId();
        this.optimalSocial = drawOptimalSocial(spec);
        this.optimalTerritory = drawOptimalTerritory(spec);
        this.personality = drawPersonality();
        this.wB = new WellBeingImpl(
                ConservationStatus.UNKNOWN.findById(spec.getConservation()).getCoefficient(),
                ConservationStatus.UNKNOWN.findById(spec.getConservation()).getDiameter());
        this.wellBeing = this.initWB;
        this.turnsOfStarvation = this.initStarvation;
        this.turnsOfDrowning = this.initDrowning;
        this.mother = mother;
        this.father = father;
    }

    /**
     * Constructor used to create an animal by the command line
     *
     * @param spec
     * @param name
     * @param paddock
     * @param sex
     * @param option
     * @throws IncorrectLoadException
     * @throws EmptyNameException
     * @throws NameException
     */
    public AnimalImpl(Specie spec, String name,
            IPaddock paddock, Sex sex, Option option)
            throws IncorrectLoadException, EmptyNameException, NameException {
        this.option = option;
        this.specie = spec;
        NameVerifications.verify(name, this.option.getAnimalBundle());
        this.name = name;
        this.paddock = paddock;
        this.optimalFeeding = drawOptimalFeeding(spec);
        this.actualFeeding = drawActualFeeding(spec);
        this.actualReproduction = drawActualReproduction(spec, sex);
        this.actualLifeSpan = drawActualLifeSpan(spec);
        this.optimalBiome = null;
        this.actualDiet = Diet.NONE.getId();
        this.optimalSocial = drawOptimalSocial(spec);
        this.optimalTerritory = drawOptimalTerritory(spec);
        this.personality = drawPersonality();
        this.age = this.actualReproduction.getMaturityAge();
        this.wB = new WellBeingImpl(
                ConservationStatus.UNKNOWN.findById(spec.getConservation()).getCoefficient(),
                ConservationStatus.UNKNOWN.findById(spec.getConservation()).getDiameter());
        this.wellBeing = this.initWB;
        this.turnsOfStarvation = this.initStarvation;
        this.turnsOfDrowning = this.initDrowning;
        this.mother = null;
        this.father = null;
    }

    public void drawAttributes() {

    }

    /**
     * Constructor used to create a animal by loading a game
     *
     * @param spec
     * @param name
     * @param paddock
     * @param age
     * @param biome
     * @param optimalFeeding
     * @param actualFeeding
     * @param diet
     * @param reproduction
     * @param life
     * @param social
     * @param territory
     * @param personality
     * @param wellBeing
     * @param turnsOfStarvation
     * @param turnsOfDrowning
     * @param currentlyGestationDuration
     * @param option
     * @throws IncorrectDataException
     * @throws EmptyNameException
     * @throws UnknownNameException
     * @throws UnauthorizedNameException
     */
    public AnimalImpl(Specie spec, String name, IPaddock paddock,
            int age,
            BiomeAttributes biome, FeedingAttributes optimalFeeding,
            FeedingAttributes actualFeeding, int diet,
            AnimalReproductionAttributes reproduction,
            LifeSpanLightAttributes life, SocialAttributes social,
            TerritoryAttributes territory, PersonalityAttributes personality, double wellBeing,
            int turnsOfStarvation, int turnsOfDrowning,
            String mother, String father,
            Option option)
            throws IncorrectDataException, EmptyNameException,
            UnknownNameException, UnauthorizedNameException {
        this.option = option;
        this.specie = spec;
        NameVerifications.verify(name, this.option.getAnimalBundle());
        this.name = name;
        this.paddock = paddock;
        this.actualReproduction = reproduction;
        this.actualLifeSpan = life;
        this.optimalBiome = biome;
        this.actualDiet = diet;
        this.optimalFeeding = optimalFeeding;
        this.actualFeeding = actualFeeding;
        this.optimalSocial = social;
        this.optimalTerritory = territory;
        this.checkLoadingOfAge(age);
        this.personality = personality;
        this.wB = new WellBeingImpl(
                ConservationStatus.UNKNOWN.findById(spec.getConservation()).getCoefficient(),
                ConservationStatus.UNKNOWN.findById(spec.getConservation()).getDiameter());
        this.wellBeing = wellBeing;
        this.checkLoadingOfDrowning(turnsOfDrowning);
        this.checkLoadingOfStarvation(turnsOfStarvation);
        this.mother = mother;
        this.father = father;
    }

    private void checkLoadingOfAge(int age) throws IncorrectDataException {
        if (age >= 0) {
            this.age = age;
        } else {
            throw new IncorrectDataException(
                    this.option.getAnimalBundle().getString("TOO_YOUNG"));
        }
    }

    private void checkLoadingOfDrowning(int drowning) throws IncorrectDataException {
        if (drowning >= 0 && drowning < Constants.MAX_DROWNING) {
            this.turnsOfDrowning = drowning;
        } else {
            throw new IncorrectDataException(
                    this.option.getAnimalBundle().getString("TOO_DROWNING"));
        }
    }

    private void checkLoadingOfStarvation(int starvation) throws IncorrectDataException {
        if (starvation >= 0 && starvation < Constants.MAX_STARVING) {
            this.turnsOfStarvation = starvation;
        } else {
            throw new IncorrectDataException(
                    this.option.getAnimalBundle().getString("TOO_STARVATION"));
        }
    }

    private BiomeAttributes drawOptimalBiome(Specie spec) {
        double night = spec.getGaussianBiome().getNightTemperature().nextDouble();
        double day = spec.getGaussianBiome().getDayTemperature().nextDouble();
        double pluvio = spec.getGaussianBiome().getPluviometry().nextDouble();
        double treeH = spec.getGaussianBiome().getTreeHeight().nextDouble();
        double treeD = spec.getGaussianBiome().getTreeDensity().nextDouble();
        double drop = spec.getGaussianBiome().getDrop().nextDouble();
        double water = spec.getGaussianBiome().getWaterSalinity().nextDouble();
        double humidity = spec.getGaussianBiome().getHumidity().nextDouble();
        return new BiomeAttributes(night, day, pluvio, treeD,
                treeH, drop, water, humidity);
    }

    /**
     * Compute the optimal values of the feeding attributes for this animal
     * according to its specie
     *
     * @param spec the Specie of the animal
     * @return its optimalFeedingAttributes
     */
    private FeedingAttributes drawOptimalFeeding(Specie spec) throws IncorrectLoadException {
        double quantity = spec.getGaussianFeeding().getFoodQuantity().nextDouble();
        return new FeedingAttributes(quantity, this.initFastDays);
    }

    /**
     * Compute the actual values of the feedings attributes for this animal they
     * are initially null
     *
     * @param spec the Specie of the animal
     * @return its actualFeedingAttributes
     */
    private FeedingAttributes drawActualFeeding(Specie spec) throws IncorrectLoadException {
        return new FeedingAttributes(this.initFoodQuantity, this.initFastDays);
    }

    /**
     * Compute the actual values of the reproduction attributes for this animal
     * according to its specie
     *
     * @param spec the Specie of the animal
     * @return its actualReproductionAttributes
     */
    private AnimalReproductionAttributes drawActualReproduction(Specie spec, Sex sex) {
        int female = spec.getGaussianReproduction().
                getFemaleMaturityAge().gaussianInt();
        int male = spec.getGaussianReproduction().
                getFemaleMaturityAge().gaussianInt();
        double frequency = spec.getGaussianReproduction().
                getGestationFrequency().gaussianDouble();
        int litter = spec.getGaussianReproduction().
                getLitterSize().gaussianInt();
        int duration = spec.getGaussianReproduction().getGestationDuration().gaussianInt();
        return new AnimalReproductionAttributes(female, male, frequency, litter, duration,
                ContraceptionMethods.NONE, sex, this.initCurrentlyGestationDuration);
    }

    private LifeSpanLightAttributes drawActualLifeSpan(Specie spec) throws IncorrectLoadException {
        int lifeSpan;
        if (this.actualReproduction.isFemale()) {
            lifeSpan = spec.getGaussianLifeSpanAttributesSpan().
                    getFemaleLifeSpan().gaussianInt();
        } else {
            lifeSpan = spec.getGaussianLifeSpanAttributesSpan().
                    getMaleLifeSpan().gaussianInt();
        }
        return new LifeSpanLightAttributes(lifeSpan);
    }

    private SocialAttributes drawOptimalSocial(Specie spec) throws IncorrectLoadException {
        int groupSize = spec.getGaussianSocialAttributes().
                getGroupSize().gaussianInt();
        return new SocialAttributes(groupSize);
    }

    private TerritoryAttributes drawOptimalTerritory(Specie spec) throws IncorrectLoadException {
        return new TerritoryAttributes(spec.getGaussianTerritoryAttributes().
                getTerritorySize().gaussianDouble());
    }

    private PersonalityAttributes drawPersonality() {
        Random rand = new Random();
        return new PersonalityAttributes(rand.nextDouble(), rand.nextDouble(), rand.nextDouble(),
                rand.nextDouble(), rand.nextDouble());
    }

    /**
     * Compute if the animal is mature by comparing its age and the age age of
     * sexual maturity for its sex
     *
     * @return true if it is mature, false else
     */
    @Override
    public boolean isMature() {
        if (this.actualReproduction != null) {
            if (this.actualReproduction.isFemale()) {
                return this.age >= this.actualReproduction.getFemaleMaturityAge();
            } else {
                return this.age >= this.actualReproduction.getMaleMaturityAge();
            }
        }
        return false;
    }

    /**
     * Compute if a animal older than its life span according to its sex
     *
     * @return true if it is older, false else.
     */
    @Override
    public boolean isTooOld() {
        if (this.actualLifeSpan != null) {
            return this.age >= this.actualLifeSpan.getLifeSpan();
        }
        return false;
    }

    /**
     * Compute if an animal is too much starving
     *
     * @return true if it is starving, false else.
     */
    @Override
    public boolean isTooStarving() {
        return this.turnsOfStarvation >= 3;
    }

    @Override
    public boolean isTooDrowning() {
        return this.turnsOfDrowning >= 3;
    }

    @Override
    public List<String> info() throws UnknownNameException, IncorrectDataException {
        List<String> info = new ArrayList<>();
        ResourceBundle bundle = this.option.getAnimalBundle();
        info.add(bundle.getString("NAME") + this.name);
        info.add(bundle.getString("PADDOCK") + this.paddock.getName());
        info.add(bundle.getString("SPECIE") + this.specie.getNameAccordingToLanguage(option));
        info.add(bundle.getString("AGE") + Utils.infoAge(this.age, bundle));
        info.add(bundle.getString("SEX") + this.actualReproduction.getSex().toStringByLanguage());
        info.add(bundle.getString("WB") + Utils.truncate(this.wellBeing));
        info.add(bundle.getString("DIET") + Diet.NONE.findById(actualDiet).toStringByLanguage());
        info.add(bundle.getString("MONTHS_WITHOUT_EATING") + this.turnsOfStarvation);
        info.add(bundle.getString("ACT_FEEDING_ATT") + this.actualFeeding.toStringByLanguage(option));
        info.add(bundle.getString("NB_FAST_DAYS") + this.actualFeeding.getFastDays());
        info.add(bundle.getString("TURNS_DROWNING") + this.turnsOfDrowning);
        info.add(bundle.getString("GENEALOGY") + Utils.infoGenealogy(mother, father, bundle));
        info.add(bundle.getString("CONTRACEPTION_METHOD") + this.actualReproduction.getContraceptionMethod().toStringByLanguage());
        if (this.actualReproduction.isFemale()) {
            info.add(bundle.getString("ACT_GESTATION_DURATION") + this.actualReproduction.getCurrentlyGestationDuration());
            info.add(bundle.getString("REPRODUCTION_ATT") + this.actualReproduction.femaleToStringByLanguage(option));
        } else {
            info.add(bundle.getString("REPRODUCTION_ATT") + this.actualReproduction.maleToStringByLanguage(option));
        }
        info.add(bundle.getString("LIFESPAN_ATT") + this.actualLifeSpan.toStringByLanguage(option));
        info.add(bundle.getString("OPT_SOCIAL_ATT") + this.optimalSocial.toStringByLanguage(option));
        info.add(bundle.getString("ACT_GROUP_SIZE") + this.paddock.countAnimalsOfTheSameSpecie(this.specie));
        info.add(bundle.getString("OPT_FEEDING_ATT") + this.optimalFeeding.toStringByLanguage(option));
        info.add(bundle.getString("OPT_TERRITORY_ATT") + this.optimalTerritory.toStringByLanguage(option));
        info.add(bundle.getString("TERRITORY_SIZE") + this.paddock.computeSize());
        info.add(bundle.getString("PERSONALITY_ATT") + this.personality.toStringByLanguage(option));
        return info;
    }

    @Override
    public void ageing(int monthsPerEvaluation) {
        this.age += monthsPerEvaluation;
    }

    @Override
    public double wellBeing(List<AnimalKeeper> keepers) throws UnknownNameException {
        AnimalsAttributes attributes = new AnimalsAttributes(this.optimalBiome, this.optimalFeeding,
                this.actualFeeding, this.actualDiet, this.optimalSocial, this.optimalTerritory, this.personality);
        this.wellBeing = wB.computeWellBeing(attributes, paddock, specie, keepers);
        // Check if the animal is starving
        if (wB.isStarving(this.actualDiet, this.actualFeeding, paddock, this.specie.getDiets(), keepers)) {
            this.turnsOfStarvation++;
        } else {
            this.turnsOfStarvation = this.initStarvation;
        }
        // Check if the animal is drowning
        if (wB.isDrowning(paddock)) {
            this.turnsOfDrowning++;
        } else {
            this.turnsOfDrowning = this.initDrowning;
        }
        return this.wellBeing;
    }

    @Override
    public boolean isFromTheSameSpecie(Specie specie) {
        if (this.specie != null) {
            return this.specie.equals(specie);
        }
        return false;
    }

    @Override
    public boolean isFromTheSameSpecie(LightSpecie specie) {
        if (this.specie != null) {
            return this.specie.equals(specie);
        }
        return false;
    }

    @Override
    public boolean hasTheSameDiet(Diet diet) {
        return this.actualDiet == diet.getId();
    }

    @Override
    public List<Animal> findRoommatesOfTheSameSpecie() {
        if (this.paddock != null && this.specie != null) {
            return this.paddock.animalsOfTheSameSpecie(this.specie);
        }
        return null;
    }

    @Override
    public boolean canBePregnant() {
        if (this.actualReproduction != null) {
            return isMature() && this.actualReproduction.isFemale() /*&& isEnoughHappy()*/;
        }
        return false;
    }

    @Override
    public boolean isAlreadyPregnant() {
        return this.actualReproduction.isAlreadyPregnant();
    }

    @Override
    public boolean updateGestationDuration(int months) {
        return this.actualReproduction.updateGestationDuration(months);
    }

    @Override
    public boolean canFecundateAFemale() {
        if (this.actualReproduction != null) {
            return isMature() && this.actualReproduction.isMale() /*&& isEnoughHappy()*/;
        }
        return false;
    }

    @Override
    public boolean isRelatedTo(Animal potentialRelation) {
        boolean bool = false;
        bool |= this.getName().equals(potentialRelation.getFather());
        bool |= this.getName().equals(potentialRelation.getMother());
        bool |= potentialRelation.getName().equals(this.father);
        bool |= potentialRelation.getName().equals(this.mother);
        return false;
    }

    @Override
    public boolean isEnoughHappy() {
        return this.wB.isCloseEnoughToMax(this.wellBeing);
    }

    @Override
    public void changeDiet(Object obj) throws UnknownNameException {
        try {
            int tmpDietInt = Integer.parseInt((String) obj);
            Diet.NONE.findById(tmpDietInt);
            this.actualDiet = tmpDietInt;
        } catch (UnknownNameException | NumberFormatException ex) {
            String tmpDietStr = (String) obj;
            int tmpDiet = Diet.NONE.findByNameAccordingToLanguage(tmpDietStr).getId();
            this.actualDiet = tmpDiet;
        }
    }

    @Override
    public void changeFoodQuantity(Double quantity) throws IncorrectLoadException {
        if (quantity > 0.0) {
            this.actualFeeding.setFoodQuantity(quantity);
        } else {
            throw new IncorrectLoadException(
                    this.option.getAnimalBundle().getString("INCORRECT_FOOD_QUANTITY_NUMBER"));
        }
    }

    @Override
    public void changeFastDays(int fastDays) throws IncorrectLoadException {
        if (fastDays >= 0 && fastDays <= 7) {
            this.actualFeeding.setFastDays(fastDays);
        } else {
            throw new IncorrectLoadException(
                    this.option.getAnimalBundle().getString("INCORRECT_FAST_DAYS_NUMBER"));
        }
    }

    @Override
    public void changeContraceptionMethod(Object contraceptionMethod)
            throws UnknownNameException, IncorrectLoadException {
        try {
            int tmpMethodInt = Integer.parseInt((String) contraceptionMethod);
            this.actualReproduction.setContraceptionMethod(
                    ContraceptionMethods.NONE.findById(tmpMethodInt), this.age);
        } catch (UnknownNameException | NumberFormatException ex) {
            String tmpMethodStr = (String) contraceptionMethod;
            try {
                this.actualReproduction.setContraceptionMethod(
                        ContraceptionMethods.NONE.findByNameAccordingToLanguage(tmpMethodStr), this.age);
            } catch (IncorrectLoadException ex1) {
                throw new IncorrectLoadException(
                        this.option.getAnimalBundle().getString("INCORRECT_CONTRACEPTION_METHOD"));
            }
        } catch (IncorrectLoadException ex) {
            throw new IncorrectLoadException(
                    this.option.getAnimalBundle().getString("INCORRECT_CONTRACEPTION_METHOD"));
        }
    }

    @Override
    public boolean compare(LightAnimal lightAnimal) {
        boolean isCorresponding = true;
        if (null != lightAnimal.getDiets() && lightAnimal.getDiets().size() != 0) {
            isCorresponding &= lightAnimal.getDiets().contains(this.actualDiet) && lightAnimal.getDiets().size() == 1;
        }
        if (null != lightAnimal.getDrownings()) {
            isCorresponding &= lightAnimal.getDrownings().contains(this.turnsOfDrowning) && lightAnimal.getDrownings().size() == 1;;
        }
        // fastDays
        if (null != lightAnimal.getFastDays()) {
            isCorresponding &= lightAnimal.getFastDays().contains(this.actualFeeding.getFastDays()) && lightAnimal.getFastDays().size() == 1;
        }
        // starvation
        if (null != lightAnimal.getStarvations()) {
            isCorresponding &= lightAnimal.getStarvations().contains(this.turnsOfStarvation) && lightAnimal.getStarvations().size() == 1;
        }
        // sexes
        if (null != lightAnimal.getSexes()) {
            isCorresponding &= lightAnimal.getSexes().contains(this.actualReproduction.getSex()) && lightAnimal.getSexes().size() == 1;
        }
        return isCorresponding;
    }

    /////////////////
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) throws EmptyNameException, UnauthorizedNameException {
        NameVerifications.verify(name, this.option.getAnimalBundle());
        this.name = name;
    }

    @Override
    public Specie getSpecie() {
        return this.specie;
    }

    @Override
    public int getActualLitterSize() {
        return this.actualReproduction.getLitterSize();
    }

    @Override
    public double getActualGestationFrequency() {
        return this.actualReproduction.getGestationFrequency();
    }

    @Override
    public IPaddock getPaddock() {
        return this.paddock;
    }

    @Override
    public int getActualFastDays() {
        return this.actualFeeding.getFastDays();
    }

    @Override
    public int getActualStarvation() {
        return this.turnsOfStarvation;
    }

    @Override
    public int getActualDrowning() {
        return this.turnsOfDrowning;
    }

    /////////////////////
    @Override
    public String getName(SaveImpl.FriendSave save) {
        save.hashCode();
        return this.name;
    }

    @Override
    public Specie getSpecie(SaveImpl.FriendSave save) {
        save.hashCode();
        return this.specie;
    }

    @Override
    public int getAge(SaveImpl.FriendSave save) {
        save.hashCode();
        return this.age;
    }

    @Override
    public FeedingAttributes getOptimalFeeding(SaveImpl.FriendSave save) {
        save.hashCode();
        return this.optimalFeeding;
    }

    @Override
    public FeedingAttributes getActualFeeding(SaveImpl.FriendSave save) {
        save.hashCode();
        return this.actualFeeding;
    }

    @Override
    public AnimalReproductionAttributes getActualReproduction(SaveImpl.FriendSave save) {
        save.hashCode();
        return this.actualReproduction;
    }

    @Override
    public LifeSpanLightAttributes getActualLifeSpan(SaveImpl.FriendSave save) {
        save.hashCode();
        return this.actualLifeSpan;
    }

    @Override
    public SocialAttributes getOptimalSocial(SaveImpl.FriendSave save) {
        save.hashCode();
        return this.optimalSocial;
    }

    @Override
    public TerritoryAttributes getOptimalTerritory(SaveImpl.FriendSave save) {
        save.hashCode();
        return this.optimalTerritory;
    }

    @Override
    public int getDiet(SaveImpl.FriendSave save) {
        save.hashCode();
        return this.actualDiet;
    }

    @Override
    public PersonalityAttributes getPersonality(SaveImpl.FriendSave save) {
        save.hashCode();
        return this.personality;
    }

    @Override
    public double getWellBeeing(SaveImpl.FriendSave save) {
        save.hashCode();
        return this.wellBeing;
    }

    @Override
    public int getStarvation(SaveImpl.FriendSave save) {
        save.hashCode();
        return this.turnsOfStarvation;
    }

    @Override
    public int getDrowning(SaveImpl.FriendSave save) {
        save.hashCode();
        return this.turnsOfDrowning;
    }

    @Override
    public String getMother(SaveImpl.FriendSave save) {
        save.hashCode();
        return this.mother;
    }

    @Override
    public String getFather(SaveImpl.FriendSave save) {
        save.hashCode();
        return this.father;
    }
}
