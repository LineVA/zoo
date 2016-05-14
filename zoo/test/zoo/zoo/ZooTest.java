package zoo.zoo;

import exception.IncorrectDimensionsException;
import exception.name.EmptyNameException;
import java.io.IOException;
import java.util.HashMap;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import zoo.paddock.Paddock;
import zoo.Zoo;

/**
 * Test of the constructor of the class Zoo is zoo package
 *
 * @author doyenm
 */
public class ZooTest {

    public ZooTest() {
    }

    @Before
    public static void setUpClass() {
    }

    @After
    public static void tearDownClass() {
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldConstructAZooWithNameAndDimensions()
            throws IncorrectDimensionsException, EmptyNameException, IOException {
        // Given
        String expectedName = "foo";
        int expectedWidth = 6;
        int expectedHeight = 7;
        HashMap<String, Paddock> expectedPaddocks = new HashMap<>();
        // When
        Zoo zoo = new Zoo(expectedName, expectedWidth, expectedHeight);
        // Then
        String actualName = zoo.getName();
        int actualWidth = zoo.getWidth();
        int actualHeight = zoo.getHeight();
        HashMap<String, Paddock> actualPaddocks = zoo.getPaddocks();
        assertEquals(expectedWidth, actualWidth);
        assertEquals(expectedHeight, actualHeight);
        assertEquals(expectedName, actualName);
        assertEquals(expectedPaddocks, actualPaddocks);
    }

    @Test
    public void shouldThrowAnIncorrectDimensionsExceptionWhenWidthIsNegativ()
            throws IncorrectDimensionsException, EmptyNameException, IOException {
        // Given
        String expectedName = "foo";
        int expectedWidth = -1;
        int expectedHeight = 7;
        HashMap<String, Paddock> expectedPaddocks = new HashMap<>();
        // When
        thrown.expect(IncorrectDimensionsException.class);
        Zoo zoo = new Zoo(expectedName, expectedWidth, expectedHeight);
        // Then
    }

    @Test
    public void shouldThrowAnIncorrectDimensionsExceptionWhenWidthIsZero()
            throws IncorrectDimensionsException, EmptyNameException, IOException {
        // Given
        String expectedName = "foo";
        int expectedWidth = 0;
        int expectedHeight = 7;
        HashMap<String, Paddock> expectedPaddocks = new HashMap<>();
        // When
        thrown.expect(IncorrectDimensionsException.class);
        Zoo zoo = new Zoo(expectedName, expectedWidth, expectedHeight);
        // Then
    }

    @Test
    public void shouldThrowAnIncorrectDimensionsExceptionWhenHeightIsNegativ()
            throws IncorrectDimensionsException, EmptyNameException, IOException {
        // Given
        String expectedName = "foo";
        int expectedWidth = 6;
        int expectedHeight = -1;
        HashMap<String, Paddock> expectedPaddocks = new HashMap<>();
        // When
        thrown.expect(IncorrectDimensionsException.class);
        Zoo zoo = new Zoo(expectedName, expectedWidth, expectedHeight);
        // Then
    }

    @Test
    public void shouldThrowAnIncorrectDimensionsExceptionWhenHeightIsZero()
            throws IncorrectDimensionsException, EmptyNameException, IOException {
        // Given
        String expectedName = "foo";
        int expectedWidth = 6;
        int expectedHeight = -1;
        HashMap<String, Paddock> expectedPaddocks = new HashMap<>();
        // When
        thrown.expect(IncorrectDimensionsException.class);
        Zoo zoo = new Zoo(expectedName, expectedWidth, expectedHeight);
        // Then
    }
    
     @Test
    public void shouldThrowAnEmptyNameExceptionWhenNameIsEmpty() 
            throws IncorrectDimensionsException, EmptyNameException, IOException {
        // Given
        String expectedName = "";
        int expectedWidth = 6;
        int expectedHeight = 9;
        HashMap<String, Paddock> expectedPaddocks = new HashMap<>();
        // When
        thrown.expect(EmptyNameException.class);
        Zoo zoo = new Zoo(expectedName, expectedWidth, expectedHeight);
        // Then
    }
    
      @Test
    public void shouldThrowAnEmptyNameExceptionWhenNameIsSpaceonly() 
            throws IncorrectDimensionsException, EmptyNameException, IOException {
        // Given
        String expectedName = " ";
        int expectedWidth = 6;
        int expectedHeight = 9;
        HashMap<String, Paddock> expectedPaddocks = new HashMap<>();
        // When
        thrown.expect(EmptyNameException.class);
        Zoo zoo = new Zoo(expectedName, expectedWidth, expectedHeight);
        // Then
    }

}
