package zoo.animal;

import exception.IncorrectDataException;
import exception.name.EmptyNameException;
import exception.name.UnauthorizedNameException;
import exception.name.UnknownNameException;
import launch.options.Option;
import lombok.Getter;
import zoo.animal.death.LifeSpanLightAttributes;
import zoo.animal.feeding.FeedingAttributes;
import zoo.animal.personality.PersonalityAttributes;
import zoo.animal.reproduction.AnimalReproductionAttributes;
import zoo.animal.reproduction.ContraceptionMethods;
import zoo.animal.reproduction.ReproductionAttributes;
import zoo.animal.reproduction.Sex;
import zoo.animal.social.SocialAttributes;
import zoo.animal.specie.Specie;
import zoo.paddock.IPaddock;
import zoo.paddock.TerritoryAttributes;
import zoo.paddock.biome.BiomeAttributes;

/**
 *
 * @author doyenm
 */
public class FakeAnimal {

    @Getter
    String specie;
    String name;
    @Getter
    String paddock;
    @Getter
    int sex;
    int age;
    BiomeAttributes biome;
    FeedingAttributes optFeed;
    FeedingAttributes actualFeed;
    @Getter
    int diet;
    AnimalReproductionAttributes repro;
    LifeSpanLightAttributes life;
    SocialAttributes social;
    TerritoryAttributes territory;
    PersonalityAttributes personality;
    double wellBeing;
    int turnsOfStarvation;
    int turnsOfDrowning;
    int gestationDuration;
    String mother;
    String father;
    @Getter
    int contraceptionMethod;

    public FakeAnimal(String specie, String name, String paddock, int sex, int age,
            BiomeAttributes biome, FeedingAttributes optFeed,
            FeedingAttributes actualFeed, int diet,
            AnimalReproductionAttributes repro, LifeSpanLightAttributes life,
            SocialAttributes social, TerritoryAttributes territory, PersonalityAttributes personality,
            double wellBeing, int turnsOfStarvation, int turnsOfDrowning, int gestationDuration,
            String mother, String father, int contraception) {
        this.specie = specie;
        this.name = name;
        this.paddock = paddock;
        this.sex = sex;
        this.age = age;
        this.biome = biome;
        this.optFeed = optFeed;
        this.actualFeed = actualFeed;
        this.diet = diet;
        this.repro = repro;
        this.life = life;
        this.social = social;
        this.territory = territory;
        this.personality = personality;
        this.wellBeing = wellBeing;
        this.turnsOfStarvation = turnsOfStarvation;
        this.turnsOfDrowning = turnsOfDrowning;
        this.gestationDuration = gestationDuration;
        this.mother = mother;
        this.father = father;
        this.contraceptionMethod = contraception;
    }

    public Animal convertToAnimal(Specie spec, IPaddock pad, Sex sex, Option option)
            throws IncorrectDataException, EmptyNameException, UnauthorizedNameException,
            UnknownNameException {
        this.repro.setContraceptionMethod(ContraceptionMethods.NONE.findById(this.contraceptionMethod));
        return new AnimalImpl(spec, this.name, pad, sex, this.age, this.biome,
                this.optFeed, this.actualFeed, this.diet, this.repro, this.life,
                this.social, this.territory, this.personality, this.wellBeing, 
                this.turnsOfStarvation, this.turnsOfDrowning, this.gestationDuration,
                this.mother, this.father,
                option);
    }

}
