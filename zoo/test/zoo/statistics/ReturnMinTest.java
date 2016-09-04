package zoo.statistics;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author doyenm
 */
public class ReturnMinTest {

    @Test
    public void shouldReturnTheMinimumValue(){
        // Given
        // When
        double actual = Compare.getMin();
        // Then 
        double expected = -5.0;
        assertEquals(expected, actual, 0.0);
    }
    
}
