package zoo.animal.diet;

import exception.IncorrectDataException;
import org.junit.AfterClass;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import zoo.animal.feeding.Diet;

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
    public void shouldReturnAlwaysReturnTrueWhenWeCheckIfACarnivoreCanEatANOTHERAnimal() throws IncorrectDataException {
        Diet carnivorous = Diet.CARNIVOROUS;
        boolean result;
        for (Diet diet : Diet.values()) {
        // Given
            // When
            result = diet.canBeEatenBy(carnivorous.getId());
            // Then
            assertTrue(result);
        }
    }
}
