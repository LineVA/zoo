package save;

import exception.IncorrectDimensionsException;
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
     * @throws exception.IncorrectDimensionsException
     */
    @Test
    public void should_CreateAFileWithTheCorectNameAndContent()
            throws EmptyNameException, IncorrectDimensionsException {
        Zoo zoo = new Zoo("myZoo2", 10, 10);
        try {
            zoo.addPaddock("padName1", 1, 2, 3 ,4);
        } catch (AlreadyUsedNameException ex) {
            Logger.getLogger(VisualizeXMLSave.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        save.save(zoo);
    }

}
