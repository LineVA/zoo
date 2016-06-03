package zoo.zoo;

import exception.IncorrectDimensionsException;
import exception.name.AlreadyUsedNameException;
import exception.name.EmptyNameException;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import zoo.Zoo;

/**
 *
 * @author doyenm
 */
public class ListPaddockTest {

    Zoo zoo;

    @Before
    public void setUpClass() throws AlreadyUsedNameException,
            IncorrectDimensionsException, EmptyNameException, IOException {
        String name = "foo";
        int width = 6;
        int height = 7;
        int age = 0;
        zoo = new Zoo();
        zoo.initiateZoo(name, width, height, null, 0);
    }

    @After
    public void tearDownClass() {
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldReturnTheNameOfThePaddocksWhenTheHashmapIsNotNull()
            throws AlreadyUsedNameException, IncorrectDimensionsException {
        // Given
        zoo.addPaddock("a", 1, 1, 1, 1);
        zoo.addPaddock("b", 2, 2, 2, 2);
        // When
        ArrayList<String> actualList = zoo.listPaddock();
        // Then
        assertEquals(2, actualList.size());
        assertEquals(true, actualList.contains("a"));
        assertEquals(true, actualList.contains("b"));
    }

    @Test
    public void shouldReturnAnEmptyListWhenThereIsNoPaddock() {
        // Given
        // When
        ArrayList<String> actualList = zoo.listPaddock();
        // Then
        assertEquals(0, actualList.size());
    }
}
