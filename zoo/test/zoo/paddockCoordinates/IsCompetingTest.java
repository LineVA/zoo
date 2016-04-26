package zoo.paddockCoordinates;

import exception.IncorrectDimensionsException;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;
import zoo.PaddockCoordinates;

/**
 * All these tests should return true;
 * The only change between them is the location of the second Paddock 
 * comparing to the first one.
 * @author doyenm
 */
public class IsCompetingTest {
    PaddockCoordinates first;
    PaddockCoordinates second;
    
    public IsCompetingTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    @Test
    public void case1() throws IncorrectDimensionsException {
        first = new PaddockCoordinates(0, 1, 3, 2);
        second = new PaddockCoordinates(1, 0, 1, 2);
        assertEquals(true, second.isCompeting(first));
    }
    
     @Test
    public void case2() throws IncorrectDimensionsException {
        first = new PaddockCoordinates(0, 1, 3, 2);
        second = new PaddockCoordinates(2, 0, 2, 2);
        assertEquals(true, second.isCompeting(first));
    }
    
     @Test
    public void case3() throws IncorrectDimensionsException {
        first = new PaddockCoordinates(1, 0, 3, 3);
        second = new PaddockCoordinates(0, 0, 5, 1);
        assertEquals(true, second.isCompeting(first));
    }
    
     @Test
    public void case4() throws IncorrectDimensionsException {
        first = new PaddockCoordinates(0, 0, 5, 1);
        second = new PaddockCoordinates(1, 0, 3, 2);
        assertEquals(true, second.isCompeting(first));
    }
    
     @Test
    public void case5() throws IncorrectDimensionsException {
        first = new PaddockCoordinates(0, 0, 3, 3);
        second = new PaddockCoordinates(1, 1, 1, 1);
        assertEquals(true, second.isCompeting(first));
    }
}
