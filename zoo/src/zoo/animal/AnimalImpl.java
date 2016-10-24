package zoo.animal;

import backup.save.SaveImpl;
import exception.IncorrectDataException;
import exception.IncorrectLoadException;
import exception.name.AlreadyUsedNameException;
import exception.name.EmptyNameException;
import exception.name.NameException;
import exception.name.UnauthorizedNameException;
import exception.name.UnknownNameException;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import launch.options.Option;
import lombok.Getter;
import lombok.Setter;
import zoo.NameVerifications;
import zoo.animal.conservation.ConservationStatus;
import zoo.animal.death.LifeSpanLightAttributes;
import zoo.animal.feeding.Diet;
import zoo.animal.feeding.FeedingAttributes;
import zoo.animal.personality.PersonalityAttributes;
import zoo.animal.reproduction.ReproductionAttributes;
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

    private Option option;

    private final Specie specie;
    private String name;
    private final IPaddock paddock;
    private final Sex sex;
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
    private final ReproductionAttributes actualReproduction;
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

    /**
     * There is no notion of optimal in personality
     */
    private final PersonalityAttributes personality;

    public AnimalImpl(Specie spec, String name, IPaddock paddock, Sex sex,
            int age, Option option)
            throws IncorrectDataException, EmptyNameException, NameException, IncorrectLoadException {
        this.option = option;
        this.specie = spec;
        NameVerifications.verify(name, this.option.getAnimalBundle());
        this.name = name;
        this.paddock = paddock;
        this.sex = sex;
        if (age >= 0) {
            this.age = age;
        } else {
            throw new IncorrectDataException(
                    this.option.getAnimalBundle().getString("TOO_YOUNG"));
        }
        this.optimalFeeding = drawOptimalFeeding(spec);
        this.actualFeeding = drawActualFeeding(spec);
        this.actualReproduction = drawActualReproduction(spec);
        this.actualLifeSpan = drawActualLifeSpan(spec);
        this.optimalBiome = null;
        this.actualDiet = Diet.NONE.getId();
        this.optimalSocial = drawOptimalSocial(spec);
        this.optimalTerritory = drawOptimalTerritory(spec);
        this.personality = drawPersonality();
        this.wB = new WellBeingImpl(
                ConservationStatus.UNKNOWN.findById(spec.getConservation()).getCoefficient(),
                ConservationStatus.UNKNOWN.findById(spec.getConservation()).getDiameter());
        this.wellBeing = 0;
    }

    public AnimalImpl(Specie spec, String name,
            IPaddock paddock, Sex sex, Option option)
            throws IncorrectLoadException, EmptyNameException, NameException {
        this.option = option;
        this.specie = spec;
        NameVerifications.verify(name, this.option.getAnimalBundle());
        this.name = name;
        this.paddock = paddock;
        this.sex = sex;
        this.optimalFeeding = drawOptimalFeeding(spec);
        this.actualFeeding = drawActualFeeding(spec);
        this.actualReproduction = drawActualReproduction(spec);
        this.actualLifeSpan = drawActualLifeSpan(spec);
        this.optimalBiome = null;
        this.actualDiet = Diet.NONE.getId();
        this.optimalSocial = drawOptimalSocial(spec);
        this.optimalTerritory = drawOptimalTerritory(spec);
        this.personality = drawPersonality();
        this.age = this.sex.isFemale()
                ? this.actualReproduction.getFemaleMaturityAge()
                : this.actualReproduction.getMaleMaturityAge();
        this.wB = new WellBeingImpl(
                ConservationStatus.UNKNOWN.findById(spec.getConservation()).getCoefficient(),
                ConservationStatus.UNKNOWN.findById(spec.getConservation()).getDiameter());
        this.wellBeing = 0;
    }

    public void drawAttributes() {

    }

    public AnimalImpl(Specie spec, String name, IPaddock paddock, Sex sex,
            int age,
            BiomeAttributes biome, FeedingAttributes optimalFeeding,
            FeedingAttributes actualFeeding, int diet,
            ReproductionAttributes reproduction,
            LifeSpanLightAttributes life, SocialAttributes social,
            TerritoryAttributes territory, PersonalityAttributes personality, Option option)
            throws IncorrectDataException, EmptyNameException,
            UnknownNameException, UnauthorizedNameException {
        this.option = option;
        this.specie = spec;
        NameVerifications.verify(name, this.option.getAnimalBundle());
        this.name = name;
        this.paddock = paddock;
        this.sex = sex;
        this.actualReproduction = reproduction;
        this.actualLifeSpan = life;
        this.optimalBiome = biome;
        this.actualDiet = diet;
        this.optimalFeeding = optimalFeeding;
        this.actualFeeding = actualFeeding;
        this.optimalSocial = social;
        this.optimalTerritory = territory;
        if (age >= 0) {
            this.age = age;
        } else {
            throw new IncorrectDataException(
                    this.option.getAnimalBundle().getString("TOO_YOUNG"));
        }
        this.personality = personality;
        this.wB = new WellBeingImpl(
                ConservationStatus.UNKNOWN.findById(spec.getConservation()).getCoefficient(),
                ConservationStatus.UNKNOWN.findById(spec.getConservation()).getDiameter());
        this.wellBeing = 0;
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
        return new FeedingAttributes(quantity);
    }

    /**
     * Compute the actual values of the feedings attributes for this animal they
     * are initially null
     *
     * @param spec the Specie of the animal
     * @return its actualFeedingAttributes
     */
    private FeedingAttributes drawActualFeeding(Specie spec) throws IncorrectLoadException {
        return new FeedingAttributes(0.0);
    }

    /**
     * Compute the actual values of the reproduction attributes for this animal
     * according to its specie
     *
     * @param spec the Specie of the animal
     * @return its actualReproductionAttributes
     */
    private ReproductionAttributes drawActualReproduction(Specie spec) {
        int female = spec.getGaussianReproduction().
                getFemaleMaturityAge().gaussianInt();
        int male = spec.getGaussianReproduction().
                getFemaleMaturityAge().gaussianInt();
        double frequency = spec.getGaussianReproduction().
                getGestationFrequency().gaussianDouble();
        int litter = spec.getGaussianReproduction().
                getLitterSize().gaussianInt();
        return new ReproductionAttributes(female, male, frequency, litter);
    }

    private LifeSpanLightAttributes drawActualLifeSpan(Specie spec) throws IncorrectLoadException {
        int lifeSpan;
        if (this.sex.isFemale()) {
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
        if (this.actualReproduction != null && this.sex != null) {
            if (this.sex == Sex.FEMALE) {
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

    @Override
    public ArrayList<String> info() throws UnknownNameException {
        ArrayList<String> info = new ArrayList<>();
        ResourceBundle bundle = this.option.getAnimalBundle();
        info.add(bundle.getString("NAME") + this.name);
        info.add(bundle.getString("PADDOCK") + this.paddock.getName());
        info.add(bundle.getString("SPECIE") + this.specie.getNameAccordingLanguage(option));
        info.add(bundle.getString("AGE") + this.age);
        info.add(bundle.getString("SEX") + this.sex.toStringByLanguage());
        info.add(bundle.getString("WB") + this.wellBeing);
        info.add(bundle.getString("DIET") + Diet.NONE.findDietById(actualDiet).toStringByLanguage());
        if (this.sex.isFemale()) {
            info.add(bundle.getString("REPRODUCTION_ATT") + this.actualReproduction.femaleToStringByLanguage(option));
        } else {
            info.add(bundle.getString("REPRODUCTION_ATT") + this.actualReproduction.maleToStringByLanguage(option));
        }
        info.add(bundle.getString("LIFESPAN_ATT") + this.actualLifeSpan.toStringByLanguage(option));
        info.add(bundle.getString("OPT_SOCIAL_ATT") + this.optimalSocial.toStringByLanguage(option));
        info.add(bundle.getString("ACT_GROUP_SIZE") + this.paddock.countAnimalsOfTheSameSpecie(this.specie));
        info.add(bundle.getString("OPT_FEEDING_ATT") + this.optimalFeeding.toStringByLanguage(option));
        info.add(bundle.getString("ACT_FEEDING_ATT") + this.actualFeeding.toStringByLanguage(option));
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
    public double wellBeing(ArrayList<AnimalKeeper> keepers) throws UnknownNameException {
        AnimalsAttributes attributes = new AnimalsAttributes(this.optimalBiome, this.optimalFeeding,
                this.actualFeeding, this.actualDiet, this.optimalSocial, this.optimalTerritory, this.personality);
        this.wellBeing = wB.computeWellBeing(attributes, paddock, specie, keepers);
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
    public ArrayList<Animal> findRoommatesOfTheSameSpecie() {
        if (this.paddock != null && this.specie != null) {
            return this.paddock.animalsOfTheSameSpecie(this.specie);
        }
        return null;
    }

    @Override
    public boolean canBePregnant() {
        if (sex != null) {
            return isMature() && this.sex.isFemale() /*&& isEnoughHappy()*/;
        }
        return false;
    }

    @Override
    public boolean canFecundateAFemale() {
        if (this.sex != null) {
            return isMature() && this.sex.isMale() /*&& isEnoughHappy()*/;
        }
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
            Diet.NONE.findDietById(tmpDietInt);
            this.actualDiet = tmpDietInt;
        } catch (UnknownNameException | NumberFormatException ex) {
            String tmpDietStr = (String) obj;
            int tmpDiet = Diet.NONE.findDietByNameAccordingToLanguage(tmpDietStr).getId();
            this.actualDiet = tmpDiet;
        }
    }

    @Override
    public void changeFoodQuantity(Double quantity) throws IncorrectLoadException {
        if (quantity > 0.0) {
            this.actualFeeding.setFoodQuantity(quantity);
        } else {
            throw new IncorrectLoadException("The food quantity must be greater or equals than zero.");
        }
    }

    /////////////////
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Sex getSex() {
        return this.sex;
    }

    @Override
    public void setName(String name) throws EmptyNameException {
        if (!name.equals("")) {
            this.name = name;
        } else {
            throw new EmptyNameException("The name of this animal is empty.");
        }
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
    public Sex getSex(SaveImpl.FriendSave save) {
        save.hashCode();
        return this.sex;
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
    public ReproductionAttributes getActualReproduction(SaveImpl.FriendSave save) {
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
        return this.personality;
    }
}
