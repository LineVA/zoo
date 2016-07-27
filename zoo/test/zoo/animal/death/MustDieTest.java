package zoo.animal.death;

import exception.IncorrectDataException;
import org.junit.Test;
import org.mockito.Mockito;
import zoo.animal.conservation.ConservationStatus;
import zoo.animal.feeding.FeedingAttributes;
import zoo.animal.AnimalImpl;
import zoo.animal.Names;
import zoo.animal.reproduction.ReproductionAttributes;
import zoo.animal.social.SocialAttributes;
import zoo.animal.specie.Specie;
import zoo.paddock.TerritoryAttributes;
import zoo.paddock.biome.BiomeAttributes;

/**
 *
 * @author doyenm
 */
public class MustDieTest {

    BiomeAttributes biome = new BiomeAttributes(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
    FeedingAttributes feed = new FeedingAttributes(0.0);
    ReproductionAttributes repro = new ReproductionAttributes(0, 0, 0.0, 0);
    SocialAttributes social = new SocialAttributes(0);
    TerritoryAttributes terri = new TerritoryAttributes(0);
    ConservationStatus conservation = ConservationStatus.UNKNOWN;
    LifeSpanAttributes fullLife = new LifeSpanAttributes(0, 0);
    Names names = new Names("", "", "");
    Specie spec;
    int diet = 0;

    public MustDieTest() {
        this.spec = new Specie(names, biome, feed, diet, repro, fullLife, ConservationStatus.UNKNOWN, social, terri, 0);
    }

    @Test
    public void shouldNotDieWhenTooYoung() throws IncorrectDataException {
        // Given
        DieImpl die = new DieImpl();
        int age = 0;
        LifeSpanLightAttributes life = new LifeSpanLightAttributes(10);
        AnimalImpl animal = new AnimalImpl(spec, "", null, null,
                age, biome, feed, feed, diet, repro, life, social, terri);
        animal = Mockito.spy(animal);
        // When
        die.mustDie(animal);
        // Then
        Mockito.verify(animal).isTooOld();
    }
}
