
package zoo.animal.wellBeing;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import zoo.animal.wellbeing.WellBeingImpl;
import zoo.statistics.Compare;

/**
 *
 * @author doyenm
 */
public class IsCloseEnoughToMaxTest {

    double maxCompare = Compare.getMax();
    
    @Test
    public void shouldReturnFalseWhenTheParameterIsLowerThanExpected(){
        // Given
        double diameter = 0.1;
        WellBeingImpl  wB = new WellBeingImpl(0, diameter);
        double compare = 0.79*maxCompare*wB.getCriteriaNumber();
        // When 
        boolean actualResult = wB.isCloseEnoughToMax(compare);
        // Then
        assertFalse(actualResult);
    }
    
     @Test
    public void shouldReturnTrueWhenTheParameterIsJustCloseEnough(){
        // Given
        double diameter = 0.1;
        WellBeingImpl  wB = new WellBeingImpl(0, diameter);
        double compare = 0.8*maxCompare*wB.getCriteriaNumber();
        // When 
        boolean actualResult = wB.isCloseEnoughToMax(compare);
        // Then
        assertTrue(actualResult);
    }
    
     @Test
    public void shouldReturnTrueWhenTheParameterIsEnoughClosed(){
        // Given
        double diameter = 0.1;
        WellBeingImpl  wB = new WellBeingImpl(0, diameter);
        double compare = 0.9*maxCompare*wB.getCriteriaNumber();
        // When 
        boolean actualResult = wB.isCloseEnoughToMax(compare);
        // Then
        assertTrue(actualResult);
    }
    
     @Test
    public void shouldReturnTrueWhenTheParameterIsEqualsToTheMax(){
        // Given
        double diameter = 0.1;
        WellBeingImpl  wB = new WellBeingImpl(0, diameter);
        double compare = 1*maxCompare*wB.getCriteriaNumber();
        // When 
        boolean actualResult = wB.isCloseEnoughToMax(compare);
        // Then
        assertTrue(actualResult);
    }
    
     @Test
    public void shouldReturnTrueWhenTheParameterIsGreaterThanTheMax(){
        // Given
        double diameter = 0.1;
        WellBeingImpl  wB = new WellBeingImpl(0, diameter);
        double compare = 1.1*maxCompare*wB.getCriteriaNumber();
        // When 
        boolean actualResult = wB.isCloseEnoughToMax(compare);
        // Then
        assertTrue(actualResult);
    }
    
    
  
}

