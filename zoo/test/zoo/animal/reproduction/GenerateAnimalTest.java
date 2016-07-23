package zoo.animal.reproduction;

import exception.IncorrectDataException;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import zoo.animal.Animal;
import zoo.animal.conservation.ConservationStatus;
import zoo.animal.death.LifeSpanAttributes;
import zoo.animal.feeding.FeedingAttributes;
import zoo.animal.social.SocialAttributes;
import zoo.animal.specie.Specie;
import zoo.paddock.IPaddock;
import zoo.paddock.Paddock;
import zoo.paddock.TerritoryAttributes;
import zoo.paddock.biome.BiomeAttributes;

/**
 *
 * @author doyenm
 */
public class GenerateAnimalTest {

    @Before
    public void setUpClass() {
    }

    @After
    public void tearDownClass() {

    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldReturnAnAnimalWithTheExpectedValues() throws IncorrectDataException {
        // Given
        BiomeAttributes biome = new BiomeAttributes(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0);
        FeedingAttributes feeding = new FeedingAttributes(1.0);
        SocialAttributes social = new SocialAttributes(1);
        LifeSpanAttributes lifeSpan = new LifeSpanAttributes(1, 1);
        TerritoryAttributes territory = new TerritoryAttributes(1.0);
        ReproductionAttributes repro = new ReproductionAttributes(1, 1, 1, 1);
        int ecoregion = 0;
        int diet = 0;
        Specie expectedSpec = new Specie(null, biome, feeding, diet, repro, lifeSpan,
                ConservationStatus.UNKNOWN, social, territory, ecoregion);
        String expectedName = "foo";
        Paddock expectedPad = new Paddock("paddock", null, null);
        ReproductionImpl reproduction = new ReproductionImpl();
        // When
        Animal actualAnimal = reproduction.generateAnimal(expectedSpec, expectedName, expectedPad);
        // Then
        String actualName = actualAnimal.getName();
        IPaddock actualPad = actualAnimal.getPaddock();
        Specie actualSpec = actualAnimal.getSpecie();
        assertEquals(actualName, expectedName);
        assertEquals(expectedPad, actualPad);
        assertEquals(expectedSpec, actualSpec);
    }

}
