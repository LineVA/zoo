package zoo.animal;

import backup.save.SaveImpl;
import exception.IncorrectDataException;
import exception.name.UnknownNameException;
import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;
import zoo.animal.death.LifeSpanLightAttributes;
import zoo.animal.feeding.Diet;
import zoo.animal.feeding.FeedingAttributes;
import zoo.animal.reproduction.ReproductionAttributes;
import zoo.animal.reproduction.Sex;
import zoo.animal.social.SocialAttributes;
import zoo.animal.specie.Specie;
import zoo.animal.wellbeing.WellBeing;
import zoo.animal.wellbeing.WellBeingImpl;
import zoo.paddock.IPaddock;
import zoo.paddock.TerritoryAttributes;
import zoo.paddock.biome.BiomeAttributes;

/**
 *
 * @author doyenm
 */
public class AnimalImpl implements Animal {

    private final Specie specie;
    private final String name;
    private final IPaddock paddock;
    private final Sex sex;
    @Getter
    @Setter
    private int wellBeing;
    private int age;
    private final WellBeing wB;
// There is both optimal and actual biome attributes :
    // the first are determined when the animal is created,
    // the second are the ones of its paddock.
    private BiomeAttributes optimalBiome;
    // There is both optimal and actual feeding attributes : 
    // the first are computed when the animal is created,
    // the second are determined by the player.
    @Getter
    @Setter
    private int diet;
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

    public AnimalImpl(Specie spec, String name, IPaddock paddock, Sex sex, int age) throws IncorrectDataException {
        this.specie = spec;
        this.name = name;
        this.paddock = paddock;
        this.sex = sex;
        if (age >= 0) {
            this.age = age;
        } else {
            throw new IncorrectDataException("An animal cannot be younger than 0 month...");
        }
//        this.optimalBiome = drawOptimalBiome(spec);
        this.optimalFeeding = drawOptimalFeeding(spec);
        this.actualFeeding = drawActualFeeding(spec);
        this.actualReproduction = drawActualReproduction(spec);
        this.actualLifeSpan = drawActualLifeSpan(spec);
        this.optimalBiome = null;
        this.diet = Diet.NONE.getId();
        //   this.optimalFeeding = null;
        // this.actualFeeding = null;
        this.optimalSocial = drawOptimalSocial(spec);
        this.optimalTerritory = drawOptimalTerritory(spec);
        this.wB = new WellBeingImpl(spec.getConservation().getCoefficient(), spec.getConservation().getDiameter());
        this.wellBeing = 0;
    }

    public AnimalImpl(Specie spec, String name, IPaddock paddock, Sex sex)
            throws IncorrectDataException {
        this.specie = spec;
        this.name = name;
        this.paddock = paddock;
        this.sex = sex;
//        this.optimalBiome = drawOptimalBiome(spec);
        this.optimalFeeding = drawOptimalFeeding(spec);
        this.actualFeeding = drawActualFeeding(spec);
        this.actualReproduction = drawActualReproduction(spec);
        this.actualLifeSpan = drawActualLifeSpan(spec);
        this.optimalBiome = null;
        this.diet = Diet.NONE.getId();
        this.optimalSocial = drawOptimalSocial(spec);
        this.optimalTerritory = drawOptimalTerritory(spec);
        this.age = this.sex.isFemale()
                ? this.actualReproduction.getFemaleMaturityAge()
                : this.actualReproduction.getMaleMaturityAge();
        this.wB = new WellBeingImpl(spec.getConservation().getCoefficient(), spec.getConservation().getDiameter());
        this.wellBeing = 0;
    }

    public void drawAttributes() {

    }

    public AnimalImpl(Specie spec, String name, IPaddock paddock, Sex sex, int age,
            BiomeAttributes biome, FeedingAttributes optimalFeeding,
            FeedingAttributes actualFeeding, int diet,
            ReproductionAttributes reproduction,
            LifeSpanLightAttributes life, SocialAttributes social,
            TerritoryAttributes territory)
            throws IncorrectDataException {
        this.specie = spec;
        this.name = name;
        this.paddock = paddock;
        this.sex = sex;
        this.actualReproduction = reproduction;
        this.actualLifeSpan = life;
        this.optimalBiome = biome;
        this.diet = diet;
        this.optimalFeeding = optimalFeeding;
        this.actualFeeding = actualFeeding;
        this.optimalSocial = social;
        this.optimalTerritory = territory;
        this.age = age;
         this.wB = new WellBeingImpl(spec.getConservation().getCoefficient(), spec.getConservation().getDiameter());
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
        return new BiomeAttributes(night, day, pluvio, treeD, treeH, drop, water, humidity);
    }

    /**
     * Compute the optimal values of the feeding attributes for this animal
     * according to its specie
     *
     * @param spec the Specie of the animal
     * @return its optimalFeedingAttributes
     */
    private FeedingAttributes drawOptimalFeeding(Specie spec) {
        double quantity = spec.getGaussianFeeding().getFoodQuantity().nextDouble();
        return new FeedingAttributes(quantity);
    }

    /**
     * Compute the actual values of the biome attributes for this animal
     * according to its specie : there are the ones of the specie
     *
     * @param spec the Specie of the animal
     * @return its actualFeedingAttributes
     */
    private FeedingAttributes drawActualFeeding(Specie spec) {
        return spec.getSpecieFeeding();
    }

    /**
     * Compute the actual values of the reproduction attributes for this animal
     * according to its specie
     *
     * @param spec the Specie of the animal
     * @return its actualReproductionAttributes
     */
    private ReproductionAttributes drawActualReproduction(Specie spec) {
        int female = spec.getGaussianReproduction().getFemaleMaturityAge().gaussianInt();
        int male = spec.getGaussianReproduction().getFemaleMaturityAge().gaussianInt();
        double frequency = spec.getGaussianReproduction().getGestationFrequency().gaussianDouble();
        int litter = spec.getGaussianReproduction().getLitterSize().gaussianInt();
        return new ReproductionAttributes(female, male, frequency, litter);
    }

    private LifeSpanLightAttributes drawActualLifeSpan(Specie spec) {
        int lifeSpan;
        if (this.sex.isFemale()) {
            lifeSpan = spec.getGaussianLifeSpanAttributesSpan().getFemaleLifeSpan().gaussianInt();
        } else {
            lifeSpan = spec.getGaussianLifeSpanAttributesSpan().getMaleLifeSpan().gaussianInt();
        }
        return new LifeSpanLightAttributes(lifeSpan);
    }

    private SocialAttributes drawOptimalSocial(Specie spec) {
        int groupSize = spec.getGaussianSocialAttributes().getGroupSize().gaussianInt();
        return new SocialAttributes(groupSize);
    }

    private TerritoryAttributes drawOptimalTerritory(Specie spec) {
        return new TerritoryAttributes(spec.getGaussianTerritoryAttributes().getTerritorySize().gaussianDouble());
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
        info.add("Name : " + this.name);
        info.add("Paddock : " + this.paddock.getName());
        info.add("Specie : " + this.specie.getNames().getEnglishName());
        info.add("Age : " + this.age);
        info.add("Sex : " + this.sex.toString());
        info.add("Well-being : " + this.wellBeing);
        info.add("Diet : " + Diet.NONE.findDietById(diet).toString());
        info.add("Reproduction attributes : " + this.actualReproduction);
        info.add("Life span attributes : " + this.actualLifeSpan.toString());
        info.add("Optimal group size : " + this.optimalSocial.getGroupSize());
        info.add("Group size : " + this.paddock.countAnimalsOfTheSameSpecie(this.specie));
        info.add("Optimal feeding attributes : " + this.optimalFeeding.toString());
        info.add("Actual feeding attributes : " + this.actualFeeding.toString());
        info.add("Territory size : " + this.paddock.computeSize());
        return info;
    }

    @Override
    public void ageing(int monthsPerEvaluation) {
        this.age += monthsPerEvaluation;
    }

    @Override
    public double wellBeing() throws UnknownNameException {
        AnimalsAttributes attributes = new AnimalsAttributes(this.optimalBiome, this.optimalFeeding, 
                this.actualFeeding, this.diet, this.optimalSocial, this.optimalTerritory);
        return wB.computeWellBeing(attributes, this.paddock, this.specie);
    }

    @Override
    public boolean isFromTheSameSpecie(Specie specie) {
        if (this.specie != null) {
            return this.specie.equals(specie);
        }
        return false;
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
            return isMature() && this.sex.isFemale();
        }
        return false;
    }

    @Override
    public boolean canFecundateAFemale() {
        if (this.sex != null) {
            return isMature() && this.sex.isMale();
        }
        return false;
    }

    /////////////////
    @Override
    public String getName() {
        return this.name;
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
    public String getName(SaveImpl.FriendSave friend) {
        return this.name;
    }

    @Override
    public Specie getSpecie(SaveImpl.FriendSave save) {
        return this.specie;
    }

    @Override
    public Sex getSex(SaveImpl.FriendSave save) {
        return this.sex;
    }

    @Override
    public int getAge(SaveImpl.FriendSave save) {
        return this.age;
    }

    @Override
    public FeedingAttributes getOptimalFeeding(SaveImpl.FriendSave save) {
        return this.optimalFeeding;
    }

    @Override
    public FeedingAttributes getActualFeeding(SaveImpl.FriendSave save) {
        return this.actualFeeding;
    }

    @Override
    public ReproductionAttributes getActualReproduction(SaveImpl.FriendSave save) {
        return this.actualReproduction;
    }

    @Override
    public LifeSpanLightAttributes getActualLifeSpan(SaveImpl.FriendSave save) {
        return this.actualLifeSpan;
    }

    @Override
    public SocialAttributes getOptimalSocial(SaveImpl.FriendSave save) {
        return this.optimalSocial;
    }

    @Override
    public TerritoryAttributes getOptimalTerritory(SaveImpl.FriendSave save) {
        return this.optimalTerritory;
    }

    @Override
    public int getDiet(SaveImpl.FriendSave save) {
        return this.diet;
    }
}
