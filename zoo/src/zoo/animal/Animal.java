package zoo.animal;

import exception.IncorrectDataException;
import java.util.ArrayList;
import zoo.animal.reproduction.Sex;
import zoo.animal.feeding.FeedingAttributes;
import zoo.paddock.Paddock;
import lombok.Getter;
import lombok.Setter;
import zoo.animal.death.LifeSpanAttributes;
import zoo.animal.reproduction.ReproductionAttributes;
import zoo.animal.specie.Specie;
import zoo.paddock.biome.BiomeAttributes;

/**
 *
 * @author doyenm
 */
public class Animal {

    @Getter
    //private final Species specie;
    private final Specie specie;
    @Getter
    private final String name;
    @Getter
    private final Paddock paddock;
    @Getter
    private final Sex sex;
    @Getter @Setter
    private int wellBeeing;
    @Getter
    private int age;
    // There is both optimal and actual biome attributes :
    // the first are determined when the animal is created,
    // the second are the ones of its paddock.
    @Getter
    private BiomeAttributes optimalBiome;
    // There is both optimal and actual feeding attributes : 
    // the first are computed when the animal is created,
    // the second are determined by the player.
    @Getter
    private final FeedingAttributes optimalFeeding;
    @Getter
    @Setter
    private FeedingAttributes actualFeeding;
    // The actual reproduction attributes are computed 
    // when the animal is created ;
    // there is no notion of "optimal reproduction attributes".
    @Getter
    private final ReproductionAttributes actualReproduction;
    // The actual life span is computed when the animal is created ; 
    // the "optimal" lifespan has no sense.
    @Getter
    private final LifeSpanAttributes actualLifeSpan;

    public Animal(Specie spec, String name, Paddock paddock, Sex sex, int age) throws IncorrectDataException {
        this.specie = spec;
        this.name = name;
        this.paddock = paddock;
        this.sex = sex;
        if (age >= 0) {
            this.age = age;
        } else {
            throw new IncorrectDataException("An animal cannot be younger than 0 month...");
        }
        this.wellBeeing = 0;
//        this.optimalBiome = drawOptimalBiome(spec);
//        this.optimalFeeding = drawOptimalFeeding(spec);
//        this.actualFeeding = drawActualFeeding(spec);
        this.actualReproduction = drawActualReproduction(spec);
        this.actualLifeSpan = drawActualLifeSpan(spec);
        this.optimalBiome = null;
        this.optimalFeeding = null;
        this.actualFeeding = null;
        // this.actualReproduction = null;
        // this.actualLifeSpan = null;
    }

    /**
     * Computes the optimal values of the biome attributes for this animal
     * according to its specie
     *
     * @param spec the Specie of the animal
     * @return its optimalBiomeAttributes
     */
    public BiomeAttributes drawOptimalBiome(Specie spec) {
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
    public FeedingAttributes drawOptimalFeeding(Specie spec) {
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
    public FeedingAttributes drawActualFeeding(Specie spec) {
        return spec.getSpecieFeeding();
    }

    /**
     * Compute the actual values of the reproduction attributes for this animal
     * according to its specie
     *
     * @param spec the Specie of the animal
     * @return its actualReproductionAttributes
     */
    public ReproductionAttributes drawActualReproduction(Specie spec) {
        int female = spec.getGaussianReproduction().getFemaleMaturityAge().gaussianInt();
        int male = spec.getGaussianReproduction().getFemaleMaturityAge().gaussianInt();
        double frequency = spec.getGaussianReproduction().getGestationFrequency().gaussianDouble();
        int litter = spec.getGaussianReproduction().getLitterSize().gaussianInt();
        return new ReproductionAttributes(female, male, frequency, litter);
    }

    private LifeSpanAttributes drawActualLifeSpan(Specie spec) {
        int femaleLifeSpan = spec.getGaussianLifeSpanAttributesSpan().getFemaleLifeSpan().gaussianInt();
        int maleLifeSpan = spec.getGaussianLifeSpanAttributesSpan().getMaleLifeSpan().gaussianInt();
        return new LifeSpanAttributes(femaleLifeSpan, maleLifeSpan);
    }

    /**
     * Compute if the animal is mature by comparing its age and the age age of
     * sexual maturity for its sex
     *
     * @return true if it is mature, false else
     */
    public boolean isMature() {
        if (this.sex == Sex.FEMALE) {
            return this.age >= this.actualReproduction.getFemaleMaturityAge();
        } else {
            return this.age >= this.actualReproduction.getMaleMaturityAge();
        }
    }

    /**
     * Compute if a animal older than its life span according to its sex
     *
     * @return true if it is older, false else.
     */
    public boolean isTooOld() {
        if (this.sex == Sex.FEMALE) {
            return this.age >= this.actualLifeSpan.getFemaleLifeSpan();
        } else {
            return this.age >= this.actualLifeSpan.getMaleLifeSpan();
        }
    }

    public ArrayList<String> info() {
        ArrayList<String> info = new ArrayList<>();
        info.add("Name : " + this.name);
        info.add("Paddock : " + this.paddock.getName());
        info.add("Specie : " + this.specie.getNames().getEnglishName());
        info.add("Age : " + this.age);
        info.add("Sex : " + this.sex.toString());
        info.add("Well-beeing : " + this.wellBeeing);
        info.add("Reproduction attributes : " + this.actualReproduction);
        info.add("Life span attributes : " + this.actualLifeSpan.toString());
//        info.add("Optimal feeding attributes : " + this.optimalFeeding.toString());
  //      info.add("Actual feeding attributes : " + this.actualFeeding.toString());
        return info;
    }

}
