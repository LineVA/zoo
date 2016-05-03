package zoo.paddockCoordinates;

import exception.IncorrectDimensionsException;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import zoo.paddock.PaddockCoordinates;

/**
 *
 * @author doyenm
 */
public class PaddockCoordinatesTest {

    int expectedX;
    int expectedY;
    int expectedWidth;
    int expectedHeight;
    PaddockCoordinates actualCoor;

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldConstructAPaddockCoordinates()
            throws IncorrectDimensionsException {
        // Given
        expectedX = 0;
        expectedY = 1;
        expectedWidth = 2;
        expectedHeight = 3;
        // When
        actualCoor = new PaddockCoordinates(expectedX, expectedY,
                expectedWidth, expectedHeight);
        // Then
        int actualX = actualCoor.getX();
        int actualY = actualCoor.getY();
        int actualWidth = actualCoor.getWidth();
        int actualHeight = actualCoor.getHeight();
        assertEquals(expectedHeight, actualHeight);
        assertEquals(expectedWidth, actualWidth);
        assertEquals(expectedX, actualX);
        assertEquals(expectedY, actualY);
    }
    
    @Test
    public void shouldThrowAnIncorrectDimensionsExceptionWhenXIsNegativ()
            throws IncorrectDimensionsException {
        // Given
        expectedX = -1;
        expectedY = 1;
        expectedWidth = 2;
        expectedHeight = 3;
        // When
        thrown.expect(IncorrectDimensionsException.class);
        actualCoor = new PaddockCoordinates(expectedX, expectedY,
                expectedWidth, expectedHeight);
        // Then
    }
    
      @Test
    public void shouldThrowAnIncorrectDimensionsExceptionWhenYIsNegativ()
            throws IncorrectDimensionsException {
        // Given
        expectedX = 1;
        expectedY = -1;
        expectedWidth = 2;
        expectedHeight = 3;
        // When
        thrown.expect(IncorrectDimensionsException.class);
        actualCoor = new PaddockCoordinates(expectedX, expectedY,
                expectedWidth, expectedHeight);
        // Then
    }
    
      @Test
    public void shouldThrowAnIncorrectDimensionsExceptionWhenWidthIsZero()
            throws IncorrectDimensionsException {
        // Given
        expectedX = 1;
        expectedY = 1;
        expectedWidth = 0;
        expectedHeight = 3;
        // When
        thrown.expect(IncorrectDimensionsException.class);
        actualCoor = new PaddockCoordinates(expectedX, expectedY,
                expectedWidth, expectedHeight);
        // Then
    }
    
      @Test
    public void shouldThrowAnIncorrectDimensionsExceptionWhenWidthIsNegativ()
            throws IncorrectDimensionsException {
        // Given
        expectedX = 1;
        expectedY = 1;
        expectedWidth = -2;
        expectedHeight = 3;
        // When
        thrown.expect(IncorrectDimensionsException.class);
        actualCoor = new PaddockCoordinates(expectedX, expectedY,
                expectedWidth, expectedHeight);
        // Then
    }
    
      @Test
    public void shouldThrowAnIncorrectDimensionsExceptionWhenHeightIsZero()
            throws IncorrectDimensionsException {
        // Given
        expectedX = 1;
        expectedY = 1;
        expectedWidth = 2;
        expectedHeight = 0;
        // When
        thrown.expect(IncorrectDimensionsException.class);
        actualCoor = new PaddockCoordinates(expectedX, expectedY,
                expectedWidth, expectedHeight);
        // Then
    }
    
      @Test
    public void shouldThrowAnIncorrectDimensionsExceptionWhenHeightIsNegativ()
            throws IncorrectDimensionsException {
        // Given
        expectedX = 1;
        expectedY = 1;
        expectedWidth = 2;
        expectedHeight = -1;
        // When
        thrown.expect(IncorrectDimensionsException.class);
        actualCoor = new PaddockCoordinates(expectedX, expectedY,
                expectedWidth, expectedHeight);
        // Then
    }
}
