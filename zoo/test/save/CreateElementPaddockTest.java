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

/**
 *
 * @author doyenm
 */
public class CreateElementPaddockTest {

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
    public void should_returnElementPaddockWithElementNameSetToFoo() throws EmptyNameException {
        //Given
        Paddock pad = new Paddock("foo");
        // When
        Element actualEl = save.createElementPaddock(pad);
        // Then 
        String expectedName = "foo";
        /* Because of the value of thrown, we are sure that getChild("name") 
         * does not throw an exception ; 
         * so, actualEl has at least one child called "name".
         */
        // It does not have any attribute.
        assertEquals(0, actualEl.getAttributes().size());
        // It only has one child.
        assertEquals(1, actualEl.getChildren().size());
        // This child is "name" with the text "foo".
        assertEquals(expectedName, actualEl.getChild("name").getText());

    }

    @Test
    public void should_ThrownANEmptyNmeExceptionIfTheNameIsEmpty() throws EmptyNameException {
        //Given
        Paddock pad = new Paddock("");
        thrown.expect(EmptyNameException.class);
        save.createElementPaddock(pad);
        // When
        // Then 
    }

}
