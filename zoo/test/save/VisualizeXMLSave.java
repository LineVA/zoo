package save;

import exception.name.AlreadyUsedNameException;
import exception.name.EmptyNameException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import zoo.Zoo;

/**
 *
 * @author doyenm
 */
public class VisualizeXMLSave {

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

    /**
     * Not really an unit test It is more like an integration tests except the
     * assert must be made by hand
     * @throws exception.name.EmptyNameException
     */
    @Test
    public void should_CreateAFileWithTheCorectNameAndContent()
            throws EmptyNameException {
        Zoo zoo = new Zoo("myZoo2");
        try {
            zoo.addPaddock("padName1");
        } catch (AlreadyUsedNameException ex) {
            Logger.getLogger(VisualizeXMLSave.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        save.save(zoo);
    }

}
