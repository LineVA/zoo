package zoo.animal.reproduction;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import zoo.animal.Animal;
import zoo.animal.Species;
import zoo.paddock.Paddock;

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
    public void shouldReturnAnAnimalWithTheExpectedValues() {
        // Given
        Species expectedSpec = Species.CAT;
        String expectedName = "foo";
        Paddock expectedPad = new Paddock("paddock", null);
        ReproductionImpl reproduction = new ReproductionImpl();
        // When
        Animal actualAnimal = reproduction.generateAnimal(expectedSpec, expectedName, expectedPad);
        // Then
        String actualName = actualAnimal.getName();
        Paddock actualPad = actualAnimal.getPaddock();
        Species actualSpec = actualAnimal.getSpecie();
        assertEquals(actualName, expectedName);
        assertEquals(expectedPad, actualPad);
        assertEquals(expectedSpec, actualSpec);
    }

}
