package save;

import exception.IncorrectDimensionsException;
import exception.name.EmptyNameException;
import org.jdom2.Element;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import zoo.Paddock;
import zoo.Zoo;

/**
 *
 * @author doyenm
 */
public class CreateElementZooTest {
private static Save save;

    @BeforeClass
    public static void setUpClass() {
        save = new Save();
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void should_returnElementZooWithAttributeNameSetToFooAndDimensionsElement() 
            throws EmptyNameException, IncorrectDimensionsException {
        //Given
        Zoo zoo = new Zoo("foo", 1, 1);
        // When
        Element actualEl = save.createElementZoo(zoo);
        // Then 
        String expectedName = "foo";
       
        // It only has one attribute
        assertEquals(1, actualEl.getAttributes().size());
        // This attribute is "name" and its value is "foo"
        assertEquals(expectedName, actualEl.getAttributeValue("name"));
        
         // It has one child
        assertEquals(1, actualEl.getChildren().size());
        // It is "dimensions".
        Element dimensions = actualEl.getChild("dimensions");
    }

     @Test
    public void should_ThrownANEmptyNmeExceptionIfTheNameIsEmpty()
            throws EmptyNameException, IncorrectDimensionsException {
        //Given
        Zoo zoo = new Zoo("", 1, 1);
        thrown.expect(EmptyNameException.class);
        save.createElementZoo(zoo);
        // When
        // Then 
    }
}
