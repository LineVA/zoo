package zoo.animal.diet;

import exception.IncorrectDataException;
import org.junit.AfterClass;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import zoo.animal.AnimalImpl;
import zoo.animal.Names;
import zoo.animal.conservation.ConservationStatus;
import zoo.animal.death.LifeSpanAttributes;
import zoo.animal.feeding.Diet;
import zoo.animal.feeding.FeedingAttributes;
import zoo.animal.reproduction.ReproductionAttributes;
import zoo.animal.reproduction.Sex;
import zoo.animal.social.SocialAttributes;
import zoo.animal.specie.Specie;
import zoo.paddock.TerritoryAttributes;
import zoo.paddock.biome.BiomeAttributes;

/**
 *
 * @author doyenm
 */
public class CanBeEatenByTest {

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldReturnWhenACarnivorousAndAnAphigohagousTryToEatThemselves() throws IncorrectDataException {
        // Given
        // When
        boolean actualCarnivorousEatsAphidiphagous = Diet.APHIDIPHAGOUS.canBeEatenBy(Diet.CARNIVOROUS.getId());
        boolean actualAphidiphagousEatsCarnivorous = Diet.CARNIVOROUS.canBeEatenBy(Diet.APHIDIPHAGOUS.getId());
        // Then
        assertFalse(actualAphidiphagousEatsCarnivorous);
        assertTrue(actualCarnivorousEatsAphidiphagous);
    }
}
