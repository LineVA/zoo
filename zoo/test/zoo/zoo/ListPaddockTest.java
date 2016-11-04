package zoo.zoo;

import exception.IncorrectDimensionsException;
import exception.name.AlreadyUsedNameException;
import exception.name.EmptyNameException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import zoo.Zoo;
import zoo.animal.specie.Specie;

/**
 *
 * @author doyenm
 */
public class ListPaddockTest {

    Zoo zoo;

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
    }

    @After
    public void tearDownClass() {
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldReturnTheNameOfThePaddocksWhenTheMapIsNotNull()
            throws AlreadyUsedNameException, IncorrectDimensionsException {
        // Given
        zoo.addPaddock("a", 1, 1, 1, 1);
        zoo.addPaddock("b", 2, 2, 2, 2);
        // When
        List<String> actualList = zoo.listPaddockByName();
        // Then
        assertEquals(2, actualList.size());
        assertEquals(true, actualList.contains("a"));
        assertEquals(true, actualList.contains("b"));
    }

    @Test
    public void shouldReturnAnEmptyListWhenThereIsNoPaddock() {
        // Given
        // When
        List<String> actualList = zoo.listPaddockByName();
        // Then
        assertEquals(0, actualList.size());
    }
}
