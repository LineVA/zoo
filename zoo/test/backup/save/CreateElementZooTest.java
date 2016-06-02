package backup.save;

import backup.save.SaveImpl;
import zoo.Zoo;
import exception.IncorrectDimensionsException;
import exception.name.EmptyNameException;
import java.io.IOException;
import org.jdom2.Element;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import zoo.IZoo;

/**
 *
 * @author doyenm
 */
public class CreateElementZooTest {

    private static SaveImpl save;

    @Before
    public void setUpClass() {
        save = new SaveImpl();
    }

    @After
    public void tearDownClass() {
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void should_returnElementZooWithAttributeNameSetToFooAndDimensionsElement()
            throws EmptyNameException, IncorrectDimensionsException, IOException {
        //Given
        IZoo zoo = new Zoo();
        zoo.initiateZoo("foo", 1, 2, null);
        // When
        Element actualEl = save.createElementZoo(zoo);
        // Then 
        String expectedName = "foo";

        // It only has one attribute
        assertEquals(1, actualEl.getAttributes().size());
        // This attribute is "name" and its value is "foo"
        assertEquals(expectedName, actualEl.getAttributeValue("name"));

        // It has only one child
        assertEquals(1, actualEl.getChildren().size());
        // It is "dimensions"
        Element dimensions = actualEl.getChild("dimensions");
        assertEquals("dimensions", actualEl.getChildren().get(0).getName());
    }
}
