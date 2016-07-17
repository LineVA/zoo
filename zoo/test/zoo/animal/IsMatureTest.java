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
public class IsMatureTest {
String expected;
    
    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    
    @Test
    public void shouldReturnFalseWhenTheFemaleAgeIsLowerThanItsMaturityAge() throws IncorrectDataException{
        // Given
        int age = 20;
        Sex sex = Sex.FEMALE;
        int femaleMat = 30;
        int maleMat =10;
        ReproductionAttributes repro = new ReproductionAttributes(femaleMat, maleMat, 0.0, 0);
        AnimalImpl female = new AnimalImpl(null, "", null, sex, age, null,
                null, null, 0, repro, null, null, null);
        // When
        boolean expectedResult = female.isMature();
        // Then
        assertFalse(expectedResult);
    } 
    
    @Test
    public void shouldReturnTrueWhenTheFemaleAgeEqualsToItsMaturityAge() throws IncorrectDataException{
        // Given
        int age = 30;
        Sex sex = Sex.FEMALE;
        int femaleMat = 30;
        int maleMat =10;
        ReproductionAttributes repro = new ReproductionAttributes(femaleMat, maleMat, 0.0, 0);
        AnimalImpl female = new AnimalImpl(null, "", null, sex, age, null,
                null, null, 0, repro, null, null, null);
        // When
        boolean expectedResult = female.isMature();
        // Then
        assertTrue(expectedResult);
    } 
    
    @Test
    public void shouldReturnTrueWhenTheFemaleAgeIsGreaterThanItsMaturityAge() throws IncorrectDataException{
        // Given
        int age = 40;
        Sex sex = Sex.FEMALE;
        int femaleMat = 30;
        int maleMat =10;
        ReproductionAttributes repro = new ReproductionAttributes(femaleMat, maleMat, 0.0, 0);
        AnimalImpl female = new AnimalImpl(null, "", null, sex, age, null,
                null, null, 0, repro, null, null, null);
        // When
        boolean expectedResult = female.isMature();
        // Then
        assertTrue(expectedResult);
    } 
    
    @Test
    public void shouldReturnFalseWhenTheMaleAgeIsLowerThanItsMaturityAge() throws IncorrectDataException{
        // Given
        int age = 20;
        Sex sex = Sex.MALE;
        int femaleMat = 10;
        int maleMat =30;
        ReproductionAttributes repro = new ReproductionAttributes(femaleMat, maleMat, 0.0, 0);
        AnimalImpl male = new AnimalImpl(null, "", null, sex, age, null,
                null, null, 0, repro, null, null, null);
        // When
        boolean expectedResult = male.isMature();
        // Then
        assertFalse(expectedResult);
    } 
    
    @Test
    public void shouldReturnTrueWhenTheMaleAgeIsEqualsToItsMaturityAge() throws IncorrectDataException{
        // Given
        int age = 30;
        Sex sex = Sex.MALE;
        int femaleMat = 10;
        int maleMat =30;
        ReproductionAttributes repro = new ReproductionAttributes(femaleMat, maleMat, 0.0, 0);
        AnimalImpl male = new AnimalImpl(null, "", null, sex, age, null,
                null, null, 0, repro, null, null, null);
        // When
        boolean expectedResult = male.isMature();
        // Then
        assertTrue(expectedResult);
    } 
    
    @Test
    public void shouldReturnTrueWhenTheMaleAgeIsGreaterThanItsMaturityAge() throws IncorrectDataException{
        // Given
        int age = 40;
        Sex sex = Sex.MALE;
        int femaleMat = 10;
        int maleMat =30;
        ReproductionAttributes repro = new ReproductionAttributes(femaleMat, maleMat, 0.0, 0);
        AnimalImpl male = new AnimalImpl(null, "", null, sex, age, null,
                null, null, 0, repro, null, null, null);
        // When
        boolean expectedResult = male.isMature();
        // Then
        assertTrue(expectedResult);
    } 

}
