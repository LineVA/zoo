package zoo.animal;

import exception.IncorrectDataException;
import exception.name.EmptyNameException;
import exception.name.NameException;
import launch.options.Option;
import lombok.Getter;
import zoo.animal.death.LifeSpanLightAttributes;
import zoo.animal.feeding.FeedingAttributes;
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
    String sex;
    int age;
    BiomeAttributes biome;
    FeedingAttributes optFeed;
    FeedingAttributes actualFeed;
    int diet;
    ReproductionAttributes repro;
    LifeSpanLightAttributes life;
    SocialAttributes social;
    TerritoryAttributes territory;

    public FakeAnimal(String specie, String name, String paddock, String sex, int age,
            BiomeAttributes biome, FeedingAttributes optFeed,
            FeedingAttributes actualFeed, int diet,
            ReproductionAttributes repro, LifeSpanLightAttributes life,
            SocialAttributes social, TerritoryAttributes territory) {
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
    }

    public Animal convertToAnimal(Specie spec, IPaddock pad, Sex sex, Option option)
            throws IncorrectDataException, EmptyNameException, NameException {
        return new AnimalImpl(spec, this.name, pad, sex, this.age, this.biome,
                this.optFeed, this.actualFeed, this.diet, this.repro, this.life,
                this.social, this.territory, null, option);
    }

}
