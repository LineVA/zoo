package zoo.paddockCoordinates;

import exception.IncorrectDimensionsException;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;
import zoo.PaddockCoordinates;

/**
 * All these tests should return true; The only change between them is the
 * location of the second Paddock comparing to the first one.
 *
 * @author doyenm
 */
public class IsNotCompetingTest {

    PaddockCoordinates first;
    PaddockCoordinates second;

    public IsNotCompetingTest() {
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
        assertEquals(false, second.isNotCompeting(first));
    }

    @Test
    public void case2() throws IncorrectDimensionsException {
        first = new PaddockCoordinates(0, 1, 3, 2);
        second = new PaddockCoordinates(2, 0, 2, 2);
        assertEquals(false, second.isNotCompeting(first));
    }

    @Test
    public void case3() throws IncorrectDimensionsException {
        first = new PaddockCoordinates(1, 0, 3, 3);
        second = new PaddockCoordinates(0, 0, 5, 1);
        assertEquals(false, second.isNotCompeting(first));
    }

    @Test
    public void case4() throws IncorrectDimensionsException {
        first = new PaddockCoordinates(0, 0, 5, 1);
        second = new PaddockCoordinates(1, 0, 3, 2);
        assertEquals(false, second.isNotCompeting(first));
    }

    @Test
    public void case5() throws IncorrectDimensionsException {
        first = new PaddockCoordinates(0, 0, 3, 3);
        second = new PaddockCoordinates(1, 1, 1, 1);
        assertEquals(false, second.isNotCompeting(first));
    }

    @Test
    public void case6() throws IncorrectDimensionsException {
        first = new PaddockCoordinates(0, 0, 2, 1);
        second = new PaddockCoordinates(0, 1, 2, 1);
        assertEquals(true, second.isNotCompeting(first));
    }
    
    @Test
    public void case7() throws IncorrectDimensionsException {
        first = new PaddockCoordinates(1, 1, 3, 2);
        second = new PaddockCoordinates(5, 0, 1, 1);
        assertEquals(true, second.isNotCompeting(first));
    }
    
    @Test
    public void case8() throws IncorrectDimensionsException {
        first = new PaddockCoordinates(1, 1, 3, 2);
        second = new PaddockCoordinates(4, 0, 1, 1);
        assertEquals(true, second.isNotCompeting(first));
    }
    
    @Test
    public void case9() throws IncorrectDimensionsException {
        first = new PaddockCoordinates(1, 1, 3, 2);
        second = new PaddockCoordinates(3, 0, 1, 1);
        assertEquals(true, second.isNotCompeting(first));
    }
    
    @Test
    public void case10() throws IncorrectDimensionsException {
        first = new PaddockCoordinates(1, 1, 3, 2);
        second = new PaddockCoordinates(3, 0, 1, 2);
        assertEquals(false, second.isNotCompeting(first));
    }
    
    @Test
    public void case11() throws IncorrectDimensionsException {
        first = new PaddockCoordinates(1, 1, 3, 2);
        second = new PaddockCoordinates(2, 0, 1, 2);
        assertEquals(false, second.isNotCompeting(first));
    }
    
    @Test
    public void case12() throws IncorrectDimensionsException {
        first = new PaddockCoordinates(1, 1, 3, 2);
        second = new PaddockCoordinates(0, 2, 2, 1);
        assertEquals(false, second.isNotCompeting(first));
    }
    
     @Test
    public void case13() throws IncorrectDimensionsException {
        first = new PaddockCoordinates(1, 1, 3, 2);
        second = new PaddockCoordinates(2, 2, 1, 1);
        assertEquals(false, second.isNotCompeting(first));
    }
    
     @Test
    public void case14() throws IncorrectDimensionsException {
        first = new PaddockCoordinates(1, 1, 3, 2);
        second = new PaddockCoordinates(2, 4, 1, 1);
        assertEquals(true, second.isNotCompeting(first));
    }
    
     @Test
    public void case15() throws IncorrectDimensionsException {
        first = new PaddockCoordinates(1, 1, 3, 2);
        second = new PaddockCoordinates(2, 3, 1, 1);
        assertEquals(true, second.isNotCompeting(first));
    }
    
    
}
