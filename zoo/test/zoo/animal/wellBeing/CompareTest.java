package zoo.animal.wellBeing;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import zoo.wellBeing.Compare;

/**
 *
 * @author doyenm
 */
public class CompareTest {
    
    double i;
    double j;
    double result;
    double expected;
    
    @Before
    public void setUpClass() {
    }

    @After
    public void tearDownClass() {

    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldReturn10WhenValuesAreEqual() {
        // Given
        i = 10.0;
        j = 10.0;
        // When
        result = Compare.compare(i, j);
        // Then
        expected = 10.0;
        assertEquals(expected, result, 0.0);
    }

    @Test
    public void shouldReturn10WhenActualIsInTheFirstDecileDown() {
        // Given
        i = 10.0;
        j = 9.0;
        // When
        result = Compare.compare(i, j);
        // Then
        expected = 10.0;
        assertEquals(expected, result, 0.0);
    }
    
    @Test
    public void shouldReturn9WhenActualIsInTheSecondDecileDown() {
        // Given
        i = 10.0;
        j = 8.0;
        // When
        result = Compare.compare(i,j);
        // Then
        expected = 9.0;
        assertEquals(expected, result, 0.0);
    }
    
    @Test
    public void shouldReturn8WhenActualIsInTheThirdDecileDown() {
        // Given
        i = 10.0;
        j = 7.0;
        // When
        result = Compare.compare(i,j);
        // Then
        expected = 8.0;
        assertEquals(expected, result, 0.0);
    }
    
    @Test
    public void shouldReturn7WhenActualIsInTheFourthDecileDown() {
        // Given
        i = 10.0;
        j = 6.0;
        // When
        result = Compare.compare(i,j);
        // Then
        expected = 7.0;
        assertEquals(expected, result, 0.0);
    }
    
    @Test
    public void shouldReturn6WhenActualIsInTheFifthDecileDown() {
        // Given
        i = 10.0;
        j = 5.0;
        // When
        result = Compare.compare(i,j);
        // Then
        expected = 6.0;
        assertEquals(expected, result, 0.0);
    }
    
    @Test
    public void shouldReturn5WhenActualIsInTheSixthDecileDown() {
        // Given
        i = 10.0;
        j = 4.0;
        // When
        result = Compare.compare(i,j);
        // Then
        expected = 5.0;
        assertEquals(expected, result, 0.0);
    }
    
    @Test
    public void shouldReturn4WhenActualIsInTheSeventhDecileDown() {
        // Given
        i = 10.0;
        j = 3.0;
        // When
        result = Compare.compare(i,j);
        // Then
        expected = 4.0;
        assertEquals(expected, result, 0.0);
    }
    
    @Test
    public void shouldReturn3WhenActualIsInTheHeighthDecileDown() {
        // Given
        i = 10.0;
        j = 2.0;
        // When
        result = Compare.compare(i,j);
        // Then
        expected = 3.0;
        assertEquals(expected, result, 0.0);
    }
    
    @Test
    public void shouldReturn2WhenActualIsInTheNinthDecileDown() {
        // Given
        i = 10.0;
        j = 1.0;
        // When
        result = Compare.compare(i,j);
        // Then
        expected = 2.0;
        assertEquals(expected, result, 0.0);
    }
    
    @Test
    public void shouldReturn1WhenActualIsInTheTenthDecileDown() {
        // Given
        i = 10.0;
        j = 0.0;
        // When
        result = Compare.compare(i,j);
        // Then
        expected = 1.0;
        assertEquals(expected, result, 0.0);
    }
    
     @Test
    public void shouldReturn10WhenActualIsInTheFirstDecileUp() {
        // Given
        i = 10.0;
        j = 11.0;
        // When
        result = Compare.compare(i, j);
        // Then
        expected = 10.0;
        assertEquals(expected, result, 0.0);
    }
    
    @Test
    public void shouldReturn9WhenActualIsInTheSecondDecileUp() {
        // Given
        i = 10.0;
        j = 12.0;
        // When
        result = Compare.compare(i,j);
        // Then
        expected = 9.0;
        assertEquals(expected, result, 0.0);
    }
    
    @Test
    public void shouldReturn8WhenActualIsInTheThirdDecileUp() {
        // Given
        i = 10.0;
        j = 13.0;
        // When
        result = Compare.compare(i,j);
        // Then
        expected = 8.0;
        assertEquals(expected, result, 0.0);
    }
    
    @Test
    public void shouldReturn7WhenActualIsInTheFourthDecileUp() {
        // Given
        i = 10.0;
        j = 14.0;
        // When
        result = Compare.compare(i,j);
        // Then
        expected = 7.0;
        assertEquals(expected, result, 0.0);
    }
    
    @Test
    public void shouldReturn6WhenActualIsInTheFifthDecileUp() {
        // Given
        i = 10.0;
        j = 15.0;
        // When
        result = Compare.compare(i,j);
        // Then
        expected = 6.0;
        assertEquals(expected, result, 0.0);
    }
    
    @Test
    public void shouldReturn5WhenActualIsInTheSixthDecileUp() {
        // Given
        i = 10.0;
        j = 16.0;
        // When
        result = Compare.compare(i,j);
        // Then
        expected = 5.0;
        assertEquals(expected, result, 0.0);
    }
    
    @Test
    public void shouldReturn4WhenActualIsInTheSeventhDecileUp() {
        // Given
        i = 10.0;
        j = 17.0;
        // When
        result = Compare.compare(i,j);
        // Then
        expected = 4.0;
        assertEquals(expected, result, 0.0);
    }
    
    @Test
    public void shouldReturn3WhenActualIsInTheHeighthDecileUp() {
        // Given
        i = 10.0;
        j = 18.0;
        // When
        result = Compare.compare(i,j);
        // Then
        expected = 3.0;
        assertEquals(expected, result, 0.0);
    }
    
    @Test
    public void shouldReturn2WhenActualIsInTheNinthDecileUp() {
        // Given
        i = 10.0;
        j = 19.0;
        // When
        result = Compare.compare(i,j);
        // Then
        expected = 2.0;
        assertEquals(expected, result, 0.0);
    }
    
    @Test
    public void shouldReturn1WhenActualIsInTheTenthDecileUp() {
        // Given
        i = 10.0;
        j =20.0;
        // When
        result = Compare.compare(i,j);
        // Then
        expected = 1.0;
        assertEquals(expected, result, 0.0);
    }
}
