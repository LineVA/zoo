package zoo.zoo;

import exception.IncorrectDimensionsException;
import exception.name.EmptyNameException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import zoo.paddock.Paddock;
import zoo.Zoo;
import zoo.animal.specie.Specie;
import zoo.paddock.IPaddock;

/**
 * Test of the constructor of the class Zoo is zoo package
 *
 * @author doyenm
 */
public class ZooTest {

    public ZooTest() {
    }

    @Before
    public void setUpClass() {
    }

    @After
    public void tearDownClass() {
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
        int expectedAge = 0;
        int expectedMonths = 9;
        int expectedHorizon = 8;
        Map<String, Paddock> expectedPaddocks = new HashMap<>();
        Map<String, Specie> expectedSpecies = new HashMap<>();
        // When
        Zoo zoo = new Zoo();
        zoo.initiateZoo(expectedName, expectedWidth, expectedHeight,
                null, expectedAge, expectedMonths, expectedHorizon);
        // Then
        String actualName = zoo.getName(null);
        int actualWidth = zoo.getWidth();
        int actualHeight = zoo.getHeight();
        int actualAge = zoo.getAge();
        int actualMonths = zoo.getMonthsPerEvaluation();
        Map<String, IPaddock> actualPaddocks = zoo.getPaddocks();
        Map<String, Specie> actualSpecies = zoo.getSpecies();
        assertEquals(expectedWidth, actualWidth);
        assertEquals(expectedHeight, actualHeight);
        assertEquals(expectedName, actualName);
        assertEquals(expectedPaddocks, actualPaddocks);
        assertEquals(expectedAge, actualAge);
        assertEquals(expectedMonths, actualMonths);
        assertEquals(null, actualSpecies);
    }

    @Test
    public void shouldThrowAnIncorrectDimensionsExceptionWhenWidthIsNegativ()
            throws IncorrectDimensionsException, EmptyNameException, IOException {
        // Given
        String expectedName = "foo";
        int expectedWidth = -1;
        int expectedHeight = 7;
        Map<String, Paddock> expectedPaddocks = new HashMap<>();
        // When
        thrown.expect(IncorrectDimensionsException.class);
        Zoo zoo = new Zoo();
         zoo.initiateZoo(expectedName, expectedWidth, expectedHeight,
                null, expectedAge, expectedMonths, expectedHorizon);
        // Then
    }

    @Test
    public void shouldThrowAnIncorrectDimensionsExceptionWhenWidthIsZero()
            throws IncorrectDimensionsException, EmptyNameException, IOException {
        // Given
        String expectedName = "foo";
        int expectedWidth = 0;
        int expectedHeight = 7;
        Map<String, Paddock> expectedPaddocks = new HashMap<>();
        // When
        thrown.expect(IncorrectDimensionsException.class);
        Zoo zoo = new Zoo();
        zoo.initiateZoo(expectedName, expectedWidth, expectedHeight, null);
        // Then
    }

    @Test
    public void shouldThrowAnIncorrectDimensionsExceptionWhenHeightIsNegativ()
            throws IncorrectDimensionsException, EmptyNameException, IOException {
        // Given
        String expectedName = "foo";
        int expectedWidth = 6;
        int expectedHeight = -1;
        Map<String, Paddock> expectedPaddocks = new HashMap<>();
        // When
        thrown.expect(IncorrectDimensionsException.class);
        Zoo zoo = new Zoo();
        zoo.initiateZoo(expectedName, expectedWidth, expectedHeight, null);
        // Then
    }

    @Test
    public void shouldThrowAnIncorrectDimensionsExceptionWhenHeightIsZero()
            throws IncorrectDimensionsException, EmptyNameException, IOException {
        // Given
        String expectedName = "foo";
        int expectedWidth = 6;
        int expectedHeight = -1;
        Map<String, Paddock> expectedPaddocks = new HashMap<>();
        // When
        thrown.expect(IncorrectDimensionsException.class);
        Zoo zoo = new Zoo();
        zoo.initiateZoo(expectedName, expectedWidth, expectedHeight, null);
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
        Zoo zoo = new Zoo();
        zoo.initiateZoo(expectedName, expectedWidth, expectedHeight, null);
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
        Zoo zoo = new Zoo();
        zoo.initiateZoo(expectedName, expectedWidth, expectedHeight, null);
        // Then
    }

}
