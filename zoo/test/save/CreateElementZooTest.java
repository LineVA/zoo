package save;

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
    public void should_returnElementZooWithAttributeNameSetToFoo() throws EmptyNameException {
        //Given
        Zoo zoo = new Zoo("foo");
        // When
        Element actualEl = save.createElementZoo(zoo);
        // Then 
        String expectedName = "foo";
        // It has no child
        assertEquals(0, actualEl.getChildren().size());
        // It only has one attribute
        assertEquals(1, actualEl.getAttributes().size());
        // This attribute is "name" and its value is "foo"
        assertEquals(expectedName, actualEl.getAttributeValue("name"));
    }

     @Test
    public void should_ThrownANEmptyNmeExceptionIfTheNameIsEmpty() throws EmptyNameException {
        //Given
        Zoo zoo = new Zoo("");
        thrown.expect(EmptyNameException.class);
        save.createElementZoo(zoo);
        // When
        // Then 
    }
}
