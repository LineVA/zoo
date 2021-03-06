package zoo.zoo;

import exception.IncorrectDimensionsException;
import exception.name.AlreadyUsedNameException;
import exception.name.EmptyNameException;
import java.io.IOException;
import java.util.Map;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import zoo.paddock.Paddock;
import zoo.paddock.PaddockCoordinates;
import zoo.Zoo;
import zoo.animal.specie.Specie;
import zoo.paddock.IPaddock;

/**
 *
 * @author doyenm
 */
public class AddPaddockTest {

    static Zoo zoo;

    @Before
    public void setUpClass() throws AlreadyUsedNameException,
            IncorrectDimensionsException, EmptyNameException, IOException {
        String expectedName = "foo";
        int expectedWidth = 6;
        int expectedHeight = 7;
        Map<String, Specie> expectedSpecies = null;
        int expectedAge  = 1;
        int expectedMonthsPerEval = 8;
        int expectedHorizon = 9;
        zoo = new Zoo();
        zoo.initiateZoo(expectedName, expectedWidth, expectedHeight, expectedSpecies,
                expectedAge, expectedMonthsPerEval, expectedHorizon);
        PaddockCoordinates coor = mock(PaddockCoordinates.class);
        when(coor.isNotCompeting(any(PaddockCoordinates.class))).thenReturn(true);
        PaddockCoordinates coor2 = mock(PaddockCoordinates.class);
        when(coor2.isNotCompeting(any(PaddockCoordinates.class))).thenReturn(true);
    }

    @After
    public void tearDownClass() {
        zoo = null;
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldAddTwoPaddocksToTheList()
            throws AlreadyUsedNameException, IncorrectDimensionsException {
        // Given
        // When
        zoo.addPaddock("a", 0, 0, 1, 1);
        zoo.addPaddock("b", 1, 1, 1, 1);
        // Then
        Map<String, IPaddock> actualList = zoo.getPaddocks(null);
        assertEquals(2, actualList.size());
        // We do not check if a value has the correct key
        assertTrue(actualList.containsKey("a"));
        assertTrue(actualList.containsKey("b"));
        assertTrue(actualList.containsValue(new Paddock("a", new PaddockCoordinates(0, 0, 1, 1), null)));
        assertTrue(actualList.containsValue(new Paddock("b", new PaddockCoordinates(1, 1, 1, 1), null)));
    }

    @Test
    public void shouldThrowAnAlreadyUsedNameExceptionWhenWeAddTwoPaddocksWithTheSameName()
            throws AlreadyUsedNameException, IncorrectDimensionsException {
        // Given
        // When
        thrown.expect(AlreadyUsedNameException.class);
        zoo.addPaddock("a", 0, 0, 1, 1);
        zoo.addPaddock("a", 0, 0, 1, 1);
        // Then
    }

    @Test
    public void shouldThrowAnIncorrectDimensionsExceptionWhenThePaddockIsTooBig()
            throws AlreadyUsedNameException, IncorrectDimensionsException {
        // Given
        thrown.expect(IncorrectDimensionsException.class);
        // When
        zoo.addPaddock("a", 5, 5, 3, 3);
        // Then
    }

}
