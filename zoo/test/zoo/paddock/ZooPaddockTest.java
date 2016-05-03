package zoo.paddock;

import exception.IncorrectDimensionsException;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 *
 * @author doyenm
 */
public class ZooPaddockTest {

    Paddock pad;
    PaddockCoordinates expectedCoor;
    String expectedName;
    
    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldConstructAPAddockWithTheGivenValues() 
            throws IncorrectDimensionsException {
       // Given
        expectedCoor = new PaddockCoordinates(0, 0, 1, 1);
        expectedName = "foo";
       // When
        pad = new Paddock(expectedName, expectedCoor);
        // Then
        String actualName = pad.getName();
        PaddockCoordinates actualCoor = pad.getCoordinates();
        assertEquals(expectedName, actualName);
        assertSame(expectedCoor, actualCoor);
    }
}
