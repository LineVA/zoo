package zoo.zoo;

import exception.IncorrectDimensionsException;
import exception.name.AlreadyUsedNameException;
import exception.name.EmptyNameException;
import exception.name.UnknownNameException;
import java.io.IOException;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import zoo.paddock.Paddock;
import zoo.paddock.PaddockCoordinates;
import zoo.Zoo;

/**
 *
 * @author doyenm
 */
public class PaddockByNameTest {

    public PaddockByNameTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldReturnTheResearchedPaddock()
            throws IncorrectDimensionsException, EmptyNameException,
            AlreadyUsedNameException, UnknownNameException, IOException {
        // Given
        String name = "foo";
        int width = 6;
        int height = 7;
        Zoo zoo = new Zoo(name, width, height);
        zoo.addPaddock("a", 1, 1, 1, 1);
        zoo.addPaddock("b", 2, 2, 2, 2);
        // When
        Paddock actualPaddock = zoo.paddockByName("b");
        // Then
        PaddockCoordinates coor = new PaddockCoordinates(2, 2, 2, 2);
        Paddock expectedPaddock = new Paddock("b", coor);
        assertEquals(true, actualPaddock.getName().equals(expectedPaddock.getName()));
    }
    
      @Test
    public void shouldThrowAnUnknownNameExceptionWhenTheResearchedPaddockDoesNotExist()
            throws IncorrectDimensionsException, EmptyNameException,
            AlreadyUsedNameException, UnknownNameException, IOException {
        // Given
        String name = "foo";
        int width = 6;
        int height = 7;
        Zoo zoo = new Zoo(name, width, height);
        zoo.addPaddock("a", 1, 1, 1, 1);
        zoo.addPaddock("b", 2, 2, 2, 2);
        // When
        thrown.expect(UnknownNameException.class);
        Paddock actualPaddock = zoo.paddockByName("c");
        // Then
    }
    
      @Test
    public void shouldThrowAnEmptyNameExceptionWhenTheResearchedNameIsEmpty()
            throws IncorrectDimensionsException, EmptyNameException,
            AlreadyUsedNameException, UnknownNameException, IOException {
        // Given
        String name = "foo";
        int width = 6;
        int height = 7;
        Zoo zoo = new Zoo(name, width, height);
        zoo.addPaddock("a", 1, 1, 1, 1);
        zoo.addPaddock("b", 2, 2, 2, 2);
        // When
        thrown.expect(EmptyNameException.class);
        Paddock actualPaddock = zoo.paddockByName(" ");
        // Then
    }

}
