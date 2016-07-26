package zoo.statistics;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author doyenm
 */
public class ReturnNegativMean {
 @Test
    public void shouldReturnTheNegativMeanValue(){
        // Given
        // When
        double actual = Compare.returnNegativMean();
        // Then 
        double expected = -2.0;
        assertEquals(expected, actual, 0.0);
    }
}
