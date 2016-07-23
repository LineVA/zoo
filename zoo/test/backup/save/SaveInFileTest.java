package backup.save;

import exception.name.EmptyNameException;
import java.io.File;
import org.jdom2.Document;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 *
 * @author doyenm
 */
public class SaveInFileTest {
private static SaveImpl save;

    @BeforeClass
    public static void setUpClass() {
        save = new SaveImpl();
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void should_createAFileWithTheCorrectNameInTheCorrectPlace()
            throws EmptyNameException {
        //Given
        org.jdom2.Document doc = new Document();
        // When
        save.saveInFile(doc, "./test/backup/save/saveInFileTest.xml");
        File expectedFile = new File("./test/backup/save/saveInFileTest.xml");
        // Then 
        assertEquals(true, expectedFile.exists());
        // We delete the test file in order to prepare the folder 
        // for the next execution of this test
        expectedFile.delete();
    }
}
