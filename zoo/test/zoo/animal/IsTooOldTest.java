package zoo.animal;

import exception.IncorrectDataException;
import org.junit.AfterClass;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import zoo.animal.death.LifeSpanLightAttributes;

/**
 *
 * @author doyenm
 */
public class IsTooOldTest {

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldReturnFalseWhenTheAnimalAgeIsLowerThanItsLifespan() throws IncorrectDataException {
        // Given
        int age = 20;
        int lifespan = 30;
        LifeSpanLightAttributes life = new LifeSpanLightAttributes(lifespan);
        AnimalImpl animal = new AnimalImpl(null, "", null, null, age, null,
                null, null, 0, null, life, null, null);
        // When
        boolean expectedResult = animal.isTooOld();
        // Then
        assertFalse(expectedResult);
    }
    
    @Test
    public void shouldReturnTrueWhenTheAnimalAgeIsEqualsToItsLifespan() throws IncorrectDataException {
        // Given
        int age = 30;
        int lifespan = 30;
        LifeSpanLightAttributes life = new LifeSpanLightAttributes(lifespan);
        AnimalImpl animal = new AnimalImpl(null, "", null, null, age, null,
                null, null, 0, null, life, null, null);
        // When
        boolean expectedResult = animal.isTooOld();
        // Then
        assertTrue(expectedResult);
    }
    
    @Test
    public void shouldReturnTrueWhenTheAnimalAgeIsGreaterThanItsLifespan() throws IncorrectDataException {
        // Given
        int age = 40;
        int lifespan = 30;
        LifeSpanLightAttributes life = new LifeSpanLightAttributes(lifespan);
        AnimalImpl animal = new AnimalImpl(null, "", null, null, age, null,
                null, null, 0, null, life, null, null);
        // When
        boolean expectedResult = animal.isTooOld();
        // Then
        assertTrue(expectedResult);
    }
    
       @Test
    public void shouldReturnFalseWhenLifeSpanAttributesIsNull() throws IncorrectDataException {
        // Given
        int age = 40;
        int lifespan = 30;
        AnimalImpl animal = new AnimalImpl(null, "", null, null, age, null,
                null, null, 0, null, null, null, null);
        // When
        boolean expectedResult = animal.isTooOld();
        // Then
        assertFalse(expectedResult);
    }
}
