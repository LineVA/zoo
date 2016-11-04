package zoo.zoo.evaluation;

import org.junit.Test;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.testng.PowerMockTestCase;
import zoo.animal.specie.Family;

/**
 *
 * @author doyenm
 */
public class FamiliesArrayFilling extends PowerMockTestCase {

 private Family mockEnumerable;

 @SuppressWarnings("unchecked")
    public void setUp() {
        mockEnumerable = PowerMockito.mock(Family.class);
    }
    
      @Test
    public void shouldReturnAnArrayOfThree(){
        // Given
        
        // When
        // Then
    }
}