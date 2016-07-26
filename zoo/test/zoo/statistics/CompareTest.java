package zoo.statistics;

import exception.IncorrectDimensionsException;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;
import zoo.paddock.PaddockCoordinates;

/**
 *
 * @author doyenm
 */
public class CompareTest {

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    double optimal = 1.0;
    double width = 0.1;
    
    /**
     * I do not know how to have better names than that...
     *
     * @throws IncorrectDimensionsException
     */
    @Test
    public void shouldReturn5WhenActualIsOptmal() throws IncorrectDimensionsException {
        // Given
        double actual = 1.0;
        double expectedResult = 5;
        // When
        double actualResult = Compare.compare(optimal, actual, width);
        // Then
        assertEquals(expectedResult, actualResult, 0.0);
    }
    
    @Test
    public void case2() throws IncorrectDimensionsException {
        // Given
        double actual = 0.95;
        double expectedResult = 5;
        // When
        double actualResult = Compare.compare(optimal, actual, width);
        // Then
        assertEquals(expectedResult, actualResult, 0.0);
    }
    
    @Test
    public void case3() throws IncorrectDimensionsException {
        // Given
        double actual = 0.9;
        double expectedResult = 5;
        // When
        double actualResult = Compare.compare(optimal, actual, width);
        // Then
        assertEquals(expectedResult, actualResult, 0.0);
    }
    
    @Test
    public void case4() throws IncorrectDimensionsException {
        // Given
        double actual = 0.85;
        double expectedResult = 3;
        // When
        double actualResult = Compare.compare(optimal, actual, width);
        // Then
        assertEquals(expectedResult, actualResult, 0.0);
    }

    @Test
    public void case5() throws IncorrectDimensionsException {
        // Given
        double actual = 0.8;
        double expectedResult = 3;
        // When
        double actualResult = Compare.compare(optimal, actual, width);
        // Then
        assertEquals(expectedResult, actualResult, 0.0);
    }
    
    @Test
    public void case6() throws IncorrectDimensionsException {
        // Given
        double actual = 0.75;
        double expectedResult = 2;
        // When
        double actualResult = Compare.compare(optimal, actual, width);
        // Then
        assertEquals(expectedResult, actualResult, 0.0);
    }
    
    @Test
    public void case7() throws IncorrectDimensionsException {
        // Given
        double actual = 0.7;
        double expectedResult = 2;
        // When
        double actualResult = Compare.compare(optimal, actual, width);
        // Then
        assertEquals(expectedResult, actualResult, 0.0);
    }
    
    @Test
    public void case8() throws IncorrectDimensionsException {
        // Given
        double actual = 0.65;
        double expectedResult = 1;
        // When
        double actualResult = Compare.compare(optimal, actual, width);
        // Then
        assertEquals(expectedResult, actualResult, 0.0);
    }
    
    @Test
    public void case9() throws IncorrectDimensionsException {
        // Given
        double actual = 0.6;
        double expectedResult = 1;
        // When
        double actualResult = Compare.compare(optimal, actual, width);
        // Then
        assertEquals(expectedResult, actualResult, 0.0);
    }

    @Test
    public void case10() throws IncorrectDimensionsException {
        // Given
        double actual = 0.55;
        double expectedResult = 0;
        // When
        double actualResult = Compare.compare(optimal, actual, width);
        // Then
        assertEquals(expectedResult, actualResult, 0.0);
    }
    
    @Test
    public void case11() throws IncorrectDimensionsException {
        // Given
        double actual = 0.5;
        double expectedResult = 0;
        // When
        double actualResult = Compare.compare(optimal, actual, width);
        // Then
        assertEquals(expectedResult, actualResult, 0.0);
    }
    
    @Test
    public void case12() throws IncorrectDimensionsException {
        // Given
        double actual = 0.45;
        double expectedResult = -1;
        // When
        double actualResult = Compare.compare(optimal, actual, width);
        // Then
        assertEquals(expectedResult, actualResult, 0.0);
    }
    
    @Test
    public void case13() throws IncorrectDimensionsException {
        // Given
        double actual = 0.4;
        double expectedResult = -1;
        // When
        double actualResult = Compare.compare(optimal, actual, width);
        // Then
        assertEquals(expectedResult, actualResult, 0.0);
    }
    
    @Test
    public void case14() throws IncorrectDimensionsException {
        // Given
        double actual = 0.35;
        double expectedResult = -2;
        // When
        double actualResult = Compare.compare(optimal, actual, width);
        // Then
        assertEquals(expectedResult, actualResult, 0.0);
    }
    
    @Test
    public void case15() throws IncorrectDimensionsException {
        // Given
        double actual = 0.30;
        double expectedResult = -2;
        // When
        double actualResult = Compare.compare(optimal, actual, width);
        // Then
        assertEquals(expectedResult, actualResult, 0.0);
    }
    
    @Test
    public void case16() throws IncorrectDimensionsException {
        // Given
        double actual = 0.25;
        double expectedResult = -3;
        // When
        double actualResult = Compare.compare(optimal, actual, width);
        // Then
        assertEquals(expectedResult, actualResult, 0.0);
    }
    
    @Test
    public void case17() throws IncorrectDimensionsException {
        // Given
        double actual = 0.2;
        double expectedResult = -3;
        // When
        double actualResult = Compare.compare(optimal, actual, width);
        // Then
        assertEquals(expectedResult, actualResult, 0.0);
    }
    
    @Test
    public void case18() throws IncorrectDimensionsException {
        // Given
        double actual = 0.15;
        double expectedResult = -4;
        // When
        double actualResult = Compare.compare(optimal, actual, width);
        // Then
        assertEquals(expectedResult, actualResult, 0.0);
    }
    
    @Test
    public void case19() throws IncorrectDimensionsException {
        // Given
        double actual = 0.1;
        double expectedResult = -4;
        // When
        double actualResult = Compare.compare(optimal, actual, width);
        // Then
        assertEquals(expectedResult, actualResult, 0.0);
    }
    
    @Test
    public void case20() throws IncorrectDimensionsException {
        // Given
        double actual = 0.05;
        double expectedResult = -5;
        // When
        double actualResult = Compare.compare(optimal, actual, width);
        // Then
        assertEquals(expectedResult, actualResult, 0.0);
    }
    
    
    ///////////// And when actual is greater than optimal ....
    
    @Test
    public void case21() throws IncorrectDimensionsException {
        // Given
        double actual = 1.05;
        double expectedResult = 5;
        // When
        double actualResult = Compare.compare(optimal, actual, width);
        // Then
        assertEquals(expectedResult, actualResult, 0.0);
    }
    
    @Test
    public void case22() throws IncorrectDimensionsException {
        // Given
        double actual = 1.1;
        double expectedResult = 5;
        // When
        double actualResult = Compare.compare(optimal, actual, width);
        // Then
        assertEquals(expectedResult, actualResult, 0.0);
    }
    
    @Test
    public void case23() throws IncorrectDimensionsException {
        // Given
        double actual = 1.15;
        double expectedResult = 3;
        // When
        double actualResult = Compare.compare(optimal, actual, width);
        // Then
        assertEquals(expectedResult, actualResult, 0.0);
    }

    @Test
    public void case24() throws IncorrectDimensionsException {
        // Given
        double actual = 1.20;
        double expectedResult = 3;
        // When
        double actualResult = Compare.compare(optimal, actual, width);
        // Then
        assertEquals(expectedResult, actualResult, 0.0);
    }
    
    @Test
    public void case25() throws IncorrectDimensionsException {
        // Given
        double actual = 1.25;
        double expectedResult = 2;
        // When
        double actualResult = Compare.compare(optimal, actual, width);
        // Then
        assertEquals(expectedResult, actualResult, 0.0);
    }
    
    @Test
    public void case26() throws IncorrectDimensionsException {
        // Given
        double actual = 1.3;
        double expectedResult = 2;
        // When
        double actualResult = Compare.compare(optimal, actual, width);
        // Then
        assertEquals(expectedResult, actualResult, 0.0);
    }
    
    @Test
    public void case27() throws IncorrectDimensionsException {
        // Given
        double actual = 1.35;
        double expectedResult = 1;
        // When
        double actualResult = Compare.compare(optimal, actual, width);
        // Then
        assertEquals(expectedResult, actualResult, 0.0);
    }
    
    @Test
    public void case28() throws IncorrectDimensionsException {
        // Given
        double actual = 1.4;
        double expectedResult = 1;
        // When
        double actualResult = Compare.compare(optimal, actual, width);
        // Then
        assertEquals(expectedResult, actualResult, 0.0);
    }

    @Test
    public void case29() throws IncorrectDimensionsException {
        // Given
        double actual = 1.45;
        double expectedResult = 0;
        // When
        double actualResult = Compare.compare(optimal, actual, width);
        // Then
        assertEquals(expectedResult, actualResult, 0.0);
    }
    
    @Test
    public void case30() throws IncorrectDimensionsException {
        // Given
        double actual = 1.5;
        double expectedResult = 0;
        // When
        double actualResult = Compare.compare(optimal, actual, width);
        // Then
        assertEquals(expectedResult, actualResult, 0.0);
    }
    
    @Test
    public void case31() throws IncorrectDimensionsException {
        // Given
        double actual = 1.55;
        double expectedResult = -1;
        // When
        double actualResult = Compare.compare(optimal, actual, width);
        // Then
        assertEquals(expectedResult, actualResult, 0.0);
    }
    
    @Test
    public void case32() throws IncorrectDimensionsException {
        // Given
        double actual = 1.6;
        double expectedResult = -1;
        // When
        double actualResult = Compare.compare(optimal, actual, width);
        // Then
        assertEquals(expectedResult, actualResult, 0.0);
    }
    
    @Test
    public void case33() throws IncorrectDimensionsException {
        // Given
        double actual = 1.65;
        double expectedResult = -2;
        // When
        double actualResult = Compare.compare(optimal, actual, width);
        // Then
        assertEquals(expectedResult, actualResult, 0.0);
    }
    
    @Test
    public void case34() throws IncorrectDimensionsException {
        // Given
        double actual = 1.7;
        double expectedResult = -2;
        // When
        double actualResult = Compare.compare(optimal, actual, width);
        // Then
        assertEquals(expectedResult, actualResult, 0.0);
    }
    
    @Test
    public void case35() throws IncorrectDimensionsException {
        // Given
        double actual = 1.75;
        double expectedResult = -3;
        // When
        double actualResult = Compare.compare(optimal, actual, width);
        // Then
        assertEquals(expectedResult, actualResult, 0.0);
    }
    
    @Test
    public void case36() throws IncorrectDimensionsException {
        // Given
        double actual = 1.8;
        double expectedResult = -3;
        // When
        double actualResult = Compare.compare(optimal, actual, width);
        // Then
        assertEquals(expectedResult, actualResult, 0.0);
    }
    
    @Test
    public void case37() throws IncorrectDimensionsException {
        // Given
        double actual = 1.85;
        double expectedResult = -4;
        // When
        double actualResult = Compare.compare(optimal, actual, width);
        // Then
        assertEquals(expectedResult, actualResult, 0.0);
    }
    
    @Test
    public void case38() throws IncorrectDimensionsException {
        // Given
        double actual = 1.9;
        double expectedResult = -4;
        // When
        double actualResult = Compare.compare(optimal, actual, width);
        // Then
        assertEquals(expectedResult, actualResult, 0.0);
    }
    
    @Test
    public void case39() throws IncorrectDimensionsException {
        // Given
        double actual = 1.95;
        double expectedResult = -5;
        // When
        double actualResult = Compare.compare(optimal, actual, width);
        // Then
        assertEquals(expectedResult, actualResult, 0.0);
    }
    
    
}
