package zoo.zoo;

import exception.IncorrectDimensionsException;
import exception.name.AlreadyUsedNameException;
import exception.name.EmptyNameException;
import exception.name.UnknownNameException;
import java.io.IOException;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import zoo.paddock.Paddock;
import zoo.paddock.PaddockCoordinates;
import zoo.Zoo;
import zoo.paddock.IPaddock;

/**
 *
 * @author doyenm
 */
public class PaddockByNameTest {

    Zoo zoo;

    @Before
    public void setUpClass() throws EmptyNameException, IOException {
        String name = "foo";
        int width = 6;
        int height = 7;
        int age = 0;
        int months = 6;
        zoo = new Zoo();
        zoo.initiateZoo(name, width, height, null, age, months);
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
        zoo.addPaddock("a", 1, 1, 1, 1);
        zoo.addPaddock("b", 2, 2, 2, 2);
        // When
        IPaddock actualPaddock = zoo.findPaddockByName("b");
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
        zoo.addPaddock("a", 1, 1, 1, 1);
        zoo.addPaddock("b", 2, 2, 2, 2);
        // When
        thrown.expect(UnknownNameException.class);
        IPaddock actualPaddock = zoo.findPaddockByName("c");
        // Then
    }

    @Test
    public void shouldThrowAnEmptyNameExceptionWhenTheResearchedNameIsEmpty()
            throws IncorrectDimensionsException, EmptyNameException,
            AlreadyUsedNameException, UnknownNameException, IOException {
        // Given
        zoo.addPaddock("a", 1, 1, 1, 1);
        zoo.addPaddock("b", 2, 2, 2, 2);
        // When
        thrown.expect(EmptyNameException.class);
        IPaddock actualPaddock = zoo.findPaddockByName(" ");
        // Then
    }

}
