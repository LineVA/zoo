package zoo.animal;

import exception.IncorrectDataException;
import org.junit.AfterClass;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import zoo.animal.reproduction.ReproductionAttributes;
import zoo.animal.reproduction.Sex;

/**
 *
 * @author doyenm
 */
public class CanBePregnantTest {

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldReturnFalseWhenReproductionIsNull() throws IncorrectDataException {
        // Given
        int age = 20;
        AnimalImpl animal = new AnimalImpl(null, "",  null, Sex.FEMALE, age, null,
                null, null, 0, null, null, null, null);
        // When
        boolean expectedResult = animal.canBePregnant();
        // Then
        assertFalse(expectedResult);
    }
    
      @Test
    public void shouldReturnFalseWhenSexIsNull() throws IncorrectDataException {
        // Given
        int age = 20;
          ReproductionAttributes repro = new ReproductionAttributes(0, 0, 0, 0);
        AnimalImpl animal = new AnimalImpl(null, "", null, null, age, null,
                null, null, 0, repro, null, null, null);
        // When
        boolean expectedResult = animal.canBePregnant();
        // Then
        assertFalse(expectedResult);
    }
    
    @Test 
    public void shouldReturnFalseWhenTheAnimalIsAMatureMale() throws IncorrectDataException{
          // Given
        int age = 20;
          ReproductionAttributes repro = new ReproductionAttributes(0, 0, 0, 0);
        AnimalImpl animal = new AnimalImpl(null, "", null, Sex.MALE, age, null,
                null, null, 0, repro, null, null, null);
        // When
        boolean expectedResult = animal.canBePregnant();
        // Then
        assertFalse(expectedResult);
    }
    
     @Test 
    public void shouldReturnFalseWhenTheAnimalIsANonMatureFemale() throws IncorrectDataException{
          // Given
        int age = 10;
          ReproductionAttributes repro = new ReproductionAttributes(20, 0, 0, 0);
        AnimalImpl animal = new AnimalImpl(null, "", null, Sex.FEMALE, age, null,
                null, null, 0, repro, null, null, null);
        // When
        boolean expectedResult = animal.canBePregnant();
        // Then
        assertFalse(expectedResult);
    }
    
     @Test 
    public void shouldReturnTrueWhenTheAnimalIsAMatureFemale() throws IncorrectDataException{
          // Given
        int age = 20;
          ReproductionAttributes repro = new ReproductionAttributes(20, 0, 0, 0);
        AnimalImpl animal = new AnimalImpl(null, "", null, Sex.FEMALE, age, null,
                null, null, 0, repro, null, null, null);
        // When
        boolean expectedResult = animal.canBePregnant();
        // Then
        assertTrue(expectedResult);
    }
    
}
